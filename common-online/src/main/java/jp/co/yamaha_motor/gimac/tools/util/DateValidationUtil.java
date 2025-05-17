package jp.co.yamaha_motor.gimac.tools.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

/**
 * 日付の検証を行うユーティリティクラス。
 */
public class DateValidationUtil {

    private DateValidationUtil() {
        throw new UnsupportedOperationException("このユーティリティクラスはインスタンス化できません。");
    }

    /**
     * 入力文字列が指定された日付形式で有効かを検証します。
     *
     * @param date       入力文字列
     * @param datePattern 日付形式（例: yyyyMMdd、yyyyMM、yyyyMMddHHmmss、yyyy-MM）
     * @return 有効な日付形式の場合は true、それ以外は false
     */
    public static boolean isValidDate(String date, String datePattern) {
        if (date == null || datePattern == null || date.trim().isEmpty()) {
            return false;
        }
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(datePattern);
            if ("yyyy-MM".equals(datePattern) || "yyyyMM".equals(datePattern)) {
                // 年月形式の場合
                YearMonth.parse(date, formatter);
            } else if (datePattern.contains("HH") || datePattern.contains("mm") || datePattern.contains("ss")) {
                // 日時形式の場合
                LocalDateTime.parse(date, formatter);
            } else {
                // 日付形式の場合
                LocalDate.parse(date, formatter);
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}