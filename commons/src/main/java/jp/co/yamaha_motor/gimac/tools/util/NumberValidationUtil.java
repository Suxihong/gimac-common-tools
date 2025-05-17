package jp.co.yamaha_motor.gimac.tools.util;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.regex.Pattern;

/**
 * 数値の検証を行うユーティリティクラス。
 */
public class NumberValidationUtil {

    private NumberValidationUtil() {
        throw new UnsupportedOperationException("このユーティリティクラスはインスタンス化できません。");
    }

    private static final Pattern NUMERIC_PATTERN = Pattern.compile("^[+-]?\\d*(\\.\\d+)?$");
    private static final Pattern HALF_WIDTH_NUMERIC_PATTERN = Pattern.compile("^\\d+$");

    /**
     * 入力文字列が有効な数値であり、指定された最小値以上であるかを検証します。
     *
     * @param source 入力文字列
     * @param min    最小値
     * @return 有効かつ最小値以上の場合は true、それ以外は false
     */
    public static boolean validateMin(String source, BigDecimal min) {
        return parseBigDecimal(source)
                .map(value -> value.compareTo(min) >= 0)
                .orElse(false);
    }

    /**
     * 入力文字列が有効な数値であり、指定された最小値より大きいかを検証します。
     *
     * @param source 入力文字列
     * @param min    最小値
     * @return 有効かつ最小値より大きい場合は true、それ以外は false
     */
    public static boolean validateMinGt(String source, BigDecimal min) {
        return parseBigDecimal(source)
                .map(value -> value.compareTo(min) > 0)
                .orElse(false);
    }

    /**
     * 入力文字列が有効な数値であり、指定された最大値以下であるかを検証します。
     *
     * @param source 入力文字列
     * @param max    最大値
     * @return 有効かつ最大値以下の場合は true、それ以外は false
     */
    public static boolean validateMax(String source, BigDecimal max) {
        return parseBigDecimal(source)
                .map(value -> value.compareTo(max) <= 0)
                .orElse(false);
    }

    /**
     * 入力文字列が有効な数値であり、指定された最大値より小さいかを検証します。
     *
     * @param source 入力文字列
     * @param max    最大値
     * @return 有効かつ最大値より小さい場合は true、それ以外は false
     */
    public static boolean validateMaxLt(String source, BigDecimal max) {
        return parseBigDecimal(source)
                .map(value -> value.compareTo(max) < 0)
                .orElse(false);
    }

    /**
     * 入力文字列が有効な数値であり、指定された範囲内（閉区間）であるかを検証します。
     *
     * @param source 入力文字列
     * @param min    最小値
     * @param max    最大値
     * @return 有効かつ範囲内の場合は true、それ以外は false
     */
    public static boolean validateRange(String source, BigDecimal min, BigDecimal max) {
        return parseBigDecimal(source)
                .map(value -> value.compareTo(min) >= 0 && value.compareTo(max) <= 0)
                .orElse(false);
    }

    /**
     * 入力文字列が有効な数値形式であるかを検証します。
     *
     * @param source 入力文字列
     * @return 有効な数値形式の場合は true、それ以外は false
     */
    public static boolean isNumeric(String source) {
        return Optional.ofNullable(source)
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .map(NUMERIC_PATTERN::matcher)
                .map(matcher -> matcher.matches())
                .orElse(false);
    }

    /**
     * 入力文字列が半角数字のみで構成されているかを検証します。
     *
     * @param source 入力文字列
     * @return 半角数字のみの場合は true、それ以外は false
     */
    public static boolean isHalfWidthNumeric(String source) {
        return Optional.ofNullable(source)
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .map(HALF_WIDTH_NUMERIC_PATTERN::matcher)
                .map(matcher -> matcher.matches())
                .orElse(false);
    }

    /**
     * 入力文字列を BigDecimal に解析します。
     *
     * @param source 入力文字列
     * @return 解析成功の場合は Optional に包まれた BigDecimal、それ以外は Optional.empty()
     */
    private static Optional<BigDecimal> parseBigDecimal(String source) {
        try {
            return Optional.ofNullable(source)
                    .map(String::trim)
                    .filter(s -> !s.isEmpty())
                    .map(BigDecimal::new);
        } catch (NumberFormatException e) {
            return Optional.empty();
        }
    }
}