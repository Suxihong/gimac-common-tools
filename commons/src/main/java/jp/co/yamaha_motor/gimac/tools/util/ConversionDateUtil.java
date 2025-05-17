package jp.co.yamaha_motor.gimac.tools.util;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * 日付フォーマット変換のためのユーティリティクラス。
 */
public class ConversionDateUtil {

    private ConversionDateUtil() {
        throw new UnsupportedOperationException("このユーティリティクラスはインスタンス化できません。");
    }

    /**
     * yyyyMMdd形式の日付文字列をロケールに基づいた中間形式に変換します。
     *
     * @param date    yyyyMMdd形式の日付文字列
     * @param locale  フォーマットに使用するロケール
     * @return        中間形式でフォーマットされた日付文字列
     */
    public static String fromYMDToMediumFormat(String date, Locale locale) {
        if (date == null || date.trim().isEmpty()) {
            return null;
        }
        try {
            LocalDate localDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyyMMdd"));
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(getMediumDateFormat(locale), locale);
            return localDate.format(formatter);
        } catch (Exception e) {
            throw new IllegalArgumentException("無効な日付形式です。yyyyMMdd形式を期待しています。", e);
        }
    }

    /**
     * Dateオブジェクトをロケールに基づいた中間形式に変換します。
     *
     * @param date    Dateオブジェクト
     * @param locale  フォーマットに使用するロケール
     * @return        中間形式でフォーマットされた日付文字列
     */
    public static String fromDateToMediumFormat(java.util.Date date, Locale locale) {
        if (date == null) {
            return null;
        }
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(getMediumDateFormat(locale), locale);
        return localDate.format(formatter);
    }

    /**
     * Dateオブジェクトをロケールに基づいたタイムスタンプ形式に変換します。
     *
     * @param date    Dateオブジェクト
     * @param locale  フォーマットに使用するロケール
     * @return        フォーマットされたタイムスタンプ文字列
     */
    public static String fromDateToTimestampFormat(java.util.Date date, Locale locale) {
        if (date == null) {
            return null;
        }
        LocalDateTime localDateTime = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(getTimestampDateFormat(locale), locale);
        return localDateTime.format(formatter);
    }

    /**
     * yyyyMMddHHmmss形式の日付文字列をロケールに基づいたタイムスタンプ形式に変換します。
     *
     * @param date    yyyyMMddHHmmss形式の日付文字列
     * @param locale  フォーマットに使用するロケール
     * @return        フォーマットされたタイムスタンプ文字列
     */
    public static String fromYMDHMSToTimestampFormat(String date, Locale locale) {
        if (date == null || date.trim().isEmpty()) {
            return null;
        }
        try {
            LocalDateTime localDateTime = LocalDateTime.parse(date, DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(getTimestampDateFormat(locale), locale);
            return localDateTime.format(formatter);
        } catch (Exception e) {
            throw new IllegalArgumentException("無効な日付形式です。yyyyMMddHHmmss形式を期待しています。", e);
        }
    }

    /**
     * フォーマットされた日付文字列をyyyyMMddHHmmss形式に変換します。
     *
     * @param date    フォーマットされた日付文字列
     * @param locale  フォーマットに使用するロケール
     * @return        yyyyMMddHHmmss形式の日付文字列
     */
    public static String fromTimestampFormatToYMDHMS(String date, Locale locale) {
        if (date == null || date.trim().isEmpty()) {
            return null;
        }
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(getTimestampDateFormat(locale), locale);
            LocalDateTime localDateTime = LocalDateTime.parse(date, formatter);
            return localDateTime.format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
        } catch (Exception e) {
            throw new IllegalArgumentException("無効な日付形式です。", e);
        }
    }

    /**
     * yyyyMMdd形式の日付文字列をロケールに基づいた月日形式に変換します。
     *
     * @param date    yyyyMMdd形式の日付文字列
     * @param locale  フォーマットに使用するロケール
     * @return        月日形式でフォーマットされた日付文字列
     */
    public static String fromYMDToMonthDayFormat(String date, Locale locale) {
        if (date == null || date.trim().isEmpty()) {
            return null;
        }
        try {
            LocalDate localDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyyMMdd"));
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(getMonthDayFormat(locale), locale);
            return localDate.format(formatter);
        } catch (Exception e) {
            throw new IllegalArgumentException("無効な日付形式です。yyyyMMdd形式を期待しています。", e);
        }
    }

    /**
     * Dateオブジェクトをロケールに基づいた月日形式に変換します。
     *
     * @param date    Dateオブジェクト
     * @param locale  フォーマットに使用するロケール
     * @return        月日形式でフォーマットされた日付文字列
     */
    public static String fromDateToMonthDayFormat(java.util.Date date, Locale locale) {
        if (date == null) {
            return null;
        }
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(getMonthDayFormat(locale), locale);
        return localDate.format(formatter);
    }

    /**
     * DateオブジェクトをyyyyMMdd形式に変換します。
     *
     * @param date    Dateオブジェクト
     * @return        yyyyMMdd形式の日付文字列
     */
    public static String fromDateToYMDFormat(java.util.Date date) {
        if (date == null) {
            return null;
        }
        if (date instanceof java.sql.Date) {
            date = new java.util.Date(date.getTime());
        }
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return localDate.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
    }

    /**
     * TimestampオブジェクトをyyyyMMdd形式に変換します。
     *
     * @param timestamp  Timestampオブジェクト
     * @return           yyyyMMdd形式の日付文字列
     */
    public static String fromTimestampToYMDFormat(Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        }
        return timestamp.toLocalDateTime().toLocalDate().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
    }

    /**
     * DateオブジェクトをyyyyMMddHHmmss形式に変換します。
     *
     * @param date    Dateオブジェクト
     * @return        yyyyMMddHHmmss形式の日付文字列
     */
    public static String fromDateToYMDHMSFormat(java.util.Date date) {
        if (date == null) {
            return null;
        }
        LocalDateTime localDateTime = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        return localDateTime.format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
    }

    /**
     * TimestampオブジェクトをyyyyMMddHHmmss形式に変換します。
     *
     * @param timestamp  Timestampオブジェクト
     * @return           yyyyMMddHHmmss形式の日付文字列
     */
    public static String fromTimestampToYMDHMSFormat(Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        }
        return timestamp.toLocalDateTime().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
    }

    /**
     * ロケールに基づいた中間形式の日付フォーマットパターンを取得します。
     *
     * @param locale 使用するロケール
     * @return 中間形式の日付フォーマットパターン
     */
    private static String getMediumDateFormat(Locale locale) {
        if (locale == null) {
            locale = Locale.getDefault();
        }
        if (locale.equals(Locale.JAPAN)) {
            return "yyyy/MM/dd";
        } else if (locale.equals(Locale.US)) {
            return "MM/dd/yyyy";
        } else if (locale.equals(Locale.UK)) {
            return "dd/MM/yyyy";
        }
        return "yyyy-MM-dd"; // デフォルトフォーマット
    }

    /**
     * ロケールに基づいたタイムスタンプ形式の日付フォーマットパターンを取得します。
     *
     * @param locale 使用するロケール
     * @return タイムスタンプ形式の日付フォーマットパターン
     */
    private static String getTimestampDateFormat(Locale locale) {
        if (locale == null) {
            locale = Locale.getDefault();
        }
        if (locale.equals(Locale.JAPAN)) {
            return "yyyy/MM/dd HH:mm:ss";
        } else if (locale.equals(Locale.US)) {
            return "MM/dd/yyyy HH:mm:ss";
        } else if (locale.equals(Locale.UK)) {
            return "dd/MM/yyyy HH:mm:ss";
        }
        return "yyyy-MM-dd HH:mm:ss"; // デフォルトフォーマット
    }

    /**
     * ロケールに基づいた月日形式の日付フォーマットパターンを取得します。
     *
     * @param locale 使用するロケール
     * @return 月日形式の日付フォーマットパターン
     */
    private static String getMonthDayFormat(Locale locale) {
        if (locale == null) {
            locale = Locale.getDefault();
        }
        if (locale.equals(Locale.JAPAN)) {
            return "MM/dd";
        } else if (locale.equals(Locale.US)) {
            return "MM/dd";
        } else if (locale.equals(Locale.UK)) {
            return "dd/MM";
        }
        return "MM-dd"; // デフォルトフォーマット
    }
}