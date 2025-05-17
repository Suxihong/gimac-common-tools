package jp.co.yamaha_motor.gimac.tools.util;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

/**
 * ビーン用ユーティリティクラス
 *
 * 2025/4/7 commons 移植
 *
 */
@Slf4j
public class BeanUtil {
    /**
     * コンストラクタ
     */
    protected BeanUtil() {
    }

    /**
     * BeanオブジェクトaのプロパティをBeanオブジェクトbのプロパティにコピーする。
     *
     * @param a Beanオブジェクトa（コピー元）
     * @param b Beanオブジェクトb（コピー先）
     */
    public static void copyProperties(Object a, Object b) {
        if (a == null || b == null) {
            return;
        }

        try {
            PropertyDescriptor[] descriptors = Introspector.getBeanInfo(a.getClass()).getPropertyDescriptors();
            for (PropertyDescriptor descriptor : descriptors) {
                String name = descriptor.getName();
                Method readMethod = descriptor.getReadMethod();
                if (readMethod != null) {
                    Object value = readMethod.invoke(a);
                    PropertyDescriptor targetDescriptor = getPropertyDescriptor(b.getClass(), name);
                    if (targetDescriptor != null) {
                        Method writeMethod = targetDescriptor.getWriteMethod();
                        if (writeMethod != null) {
                            writeMethod.invoke(b, value);
                        }
                    }
                }
            }
        } catch (Exception e) {
            log.error("Error copying properties", e);
        }
    }

    /**
     * コレクションをビーンのプロパティで指定された列の配列に変換します。
     *
     * @param c コレクション
     * @param property プロパティ
     * @return プロパティの列の配列
     */
    public static String[] toArrayUseBeanProperty(Collection<?> c, String property) {
        String[] values = new String[c.size()];
        int index = 0;

        for (Object bean : c) {
            try {
                PropertyDescriptor descriptor = getPropertyDescriptor(bean.getClass(), property);
                if (descriptor != null && descriptor.getReadMethod() != null) {
                    values[index++] = (String) descriptor.getReadMethod().invoke(bean);
                } else {
                    values[index++] = "";
                }
            } catch (Exception e) {
                values[index++] = "";
            }
        }

        return values;
    }

    /**
     * コレクションをビーンのプロパティで指定された列の配列に変換します。
     *
     * @param form form
     * @param c    コレクション
     * @param row  row
     * @return プロパティの列の配列
     */
    public static List<Object> copyArrayProperties(Object form, Class<?> c, int row) {
        List<Object> result = new ArrayList<>();

        for (int i = 0; i < row; i++) {
            try {
                Object dest = c.getDeclaredConstructor().newInstance();
                PropertyDescriptor[] descriptors = Introspector.getBeanInfo(form.getClass()).getPropertyDescriptors();
                for (PropertyDescriptor descriptor : descriptors) {
                    String name = descriptor.getName();
                    Method readMethod = descriptor.getReadMethod();
                    if (readMethod != null) {
                        Object value = readMethod.invoke(form);
                        PropertyDescriptor targetDescriptor = getPropertyDescriptor(c, name);
                        if (targetDescriptor != null && targetDescriptor.getWriteMethod() != null) {
                            targetDescriptor.getWriteMethod().invoke(dest, value);
                        }
                    }
                }
                result.add(dest);
            } catch (Exception e) {
                log.error("Error copying array properties", e);
            }
        }

        return result;
    }

    /**
     * Beanオブジェクトでnull値のプロパティを空白にします。
     *
     * @param a Beanオブジェクト
     */
    public static void fillToBlank(Object a) {
        if (a == null) {
            return;
        }

        try {
            PropertyDescriptor[] descriptors = Introspector.getBeanInfo(a.getClass()).getPropertyDescriptors();
            for (PropertyDescriptor descriptor : descriptors) {
                if (descriptor.getPropertyType() == String.class) {
                    Method readMethod = descriptor.getReadMethod();
                    Method writeMethod = descriptor.getWriteMethod();
                    if (readMethod != null && writeMethod != null) {
                        Object value = readMethod.invoke(a);
                        if (value == null) {
                            writeMethod.invoke(a, " ");
                        }
                    }
                }
            }
        } catch (Exception e) {
            log.error("Error filling properties to blank", e);
        }
    }

    /**
     * 指定したオブジェクトに存在する、読み取り可能なプロパティに対するイテレータを返します。
     *
     * @param o オブジェクト
     * @return 読み取り可能なプロパティに対するイテレータ
     */
    public static Iterator<String> getReadablePropertiesIterator(Object o) {
        List<String> properties = new ArrayList<>();
        try {
            PropertyDescriptor[] descriptors = Introspector.getBeanInfo(o.getClass()).getPropertyDescriptors();
            for (PropertyDescriptor descriptor : descriptors) {
                if (descriptor.getReadMethod() != null) {
                    properties.add(descriptor.getName());
                }
            }
        } catch (IntrospectionException e) {
            log.error("Error getting readable properties", e);
        }
        return properties.iterator();
    }

    /**
     * オブジェクトのプロパティの値を取り出します。
     *
     * @param o    取得対象のオブジェクト
     * @param name オブジェクトのプロパティ名
     * @return プロパティから取り出した値
     */
    public static Object getPropertyValue(Object o, String name) {
        try {
            PropertyDescriptor descriptor = getPropertyDescriptor(o.getClass(), name);
            if (descriptor != null && descriptor.getReadMethod() != null) {
                return descriptor.getReadMethod().invoke(o);
            }
        } catch (Exception e) {
            log.error("Error getting property value", e);
        }
        return null;
    }

    /**
     * オブジェクトのプロパティに指定された値をセットします。
     *
     * @param o     対象のオブジェクト
     * @param name  オブジェクトのプロパティ名
     * @param value オブジェクトの値
     */
    public static void setPropertyValue(Object o, String name, Object value) {
        try {
            PropertyDescriptor descriptor = getPropertyDescriptor(o.getClass(), name);
            if (descriptor != null && descriptor.getWriteMethod() != null) {
                descriptor.getWriteMethod().invoke(o, value);
            }
        } catch (Exception e) {
            log.error("Error setting property value", e);
        }
    }

    /**
     * 指定したクラスのプロパティディスクリプタを取得します。
     *
     * @param clazz クラス
     * @param name  プロパティ名
     * @return プロパティディスクリプタ
     */
    private static PropertyDescriptor getPropertyDescriptor(Class<?> clazz, String name) {
        try {
            PropertyDescriptor[] descriptors = Introspector.getBeanInfo(clazz).getPropertyDescriptors();
            for (PropertyDescriptor descriptor : descriptors) {
                if (descriptor.getName().equals(name)) {
                    return descriptor;
                }
            }
        } catch (IntrospectionException e) {
            log.error("Error getting property descriptor", e);
        }
        return null;
    }
}