package jp.co.yamaha_motor.gimac.tools.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

/**
 * 数値フォーマット変換のユーティリティクラス
 */
public class ConversionNumberUtil {

    private ConversionNumberUtil() {
        throw new UnsupportedOperationException("このユーティリティクラスはインスタンス化できません。");
    }
    
    /**
     * BigDecimal型の数値をロケールに合わせた区切り文字でフォーマットした文字列に変換する
     *
     * @param decValue 変換元の数値
     * @param format   フォーマット文字列（'#,##0.00000'など）
     * @return フォーマット変換後数値
     */
    public static String formatBigDecimal(BigDecimal decValue, String format) {
        return formatBigDecimal(decValue, format, Locale.getDefault());
    }

    /**
     * BigDecimal型の数値をロケールに合わせた区切り文字でフォーマットした文字列に変換する
     *
     * @param decValue 変換元の数値
     * @param format   フォーマット文字列（'#,##0.00000'など）
     * @param locale   使用するフォーマットが定義されるロケール
     * @return フォーマット変換後数値
     */
    public static String formatBigDecimal(BigDecimal decValue, String format, Locale locale) {
        if (decValue == null || format == null || format.trim().isEmpty()) {
            return "";
        }

        DecimalFormat df = (DecimalFormat) NumberFormat.getInstance(locale);
        df.applyPattern(format);
        return df.format(decValue);
    }

    /**
     * Integer型の数値をロケールに合わせた区切り文字でフォーマットした文字列に変換する
     *
     * @param intValue 変換元の数値
     * @param format   フォーマット文字列（'#,##0'など）
     * @return フォーマット変換後数値
     */
    public static String formatInteger(Integer intValue, String format) {
        return formatInteger(intValue, format, Locale.getDefault());
    }

    /**
     * Integer型の数値をロケールに合わせた区切り文字でフォーマットした文字列に変換する
     *
     * @param intValue 変換元の数値
     * @param format   フォーマット文字列（'#,##0'など）
     * @param locale   使用するフォーマットが定義されるロケール
     * @return フォーマット変換後数値
     */
    public static String formatInteger(Integer intValue, String format, Locale locale) {
        if (intValue == null || format == null || format.trim().isEmpty()) {
            return "";
        }

        DecimalFormat df = (DecimalFormat) NumberFormat.getInstance(locale);
        df.applyPattern(format);
        return df.format(intValue);
    }

    /**
     * int型の数値をロケールに合わせた区切り文字でフォーマットした文字列に変換する
     *
     * @param intValue 変換元の数値
     * @param format   フォーマット文字列（'#,##0'など）
     * @return フォーマット変換後数値
     */
    public static String formatInt(int intValue, String format) {
        return formatInt(intValue, format, Locale.getDefault());
    }

    /**
     * int型の数値をロケールに合わせた区切り文字でフォーマットした文字列に変換する
     *
     * @param intValue 変換元の数値
     * @param format   フォーマット文字列（'#,##0'など）
     * @param locale   使用するフォーマットが定義されるロケール
     * @return フォーマット変換後数値
     */
    public static String formatInt(int intValue, String format, Locale locale) {
        return formatInteger(intValue, format, locale);
    }

    /**
     * フォーマット編集後の数値をフォーマット編集前の文字列に変換する
     *
     * @param obj フォーマット編集後の数値
     * @return フォーマット編集前の数値
     */
    public static String unformatNumber(String obj) {
        return unformatNumber(obj, Locale.getDefault());
    }

    /**
     * フォーマット編集後の数値をフォーマット編集前の文字列に変換する
     *
     * @param obj    フォーマット編集後の数値
     * @param locale 使用するフォーマットが定義されるロケール
     * @return フォーマット編集前の数値
     */
    public static String unformatNumber(String obj, Locale locale) {
        if (obj == null) {
            return "";
        }

        String formattedNum = obj.replace("&#44;", ",");
        DecimalFormat decimalFormat = (DecimalFormat) NumberFormat.getInstance(locale);
        char groupSeparator = decimalFormat.getDecimalFormatSymbols().getGroupingSeparator();
        char decimalSeparator = decimalFormat.getDecimalFormatSymbols().getDecimalSeparator();
        
        formattedNum = formattedNum.replace(String.valueOf(groupSeparator), "");
        formattedNum = formattedNum.replace(String.valueOf(decimalSeparator), ".");
    
        return formattedNum;
    }
}