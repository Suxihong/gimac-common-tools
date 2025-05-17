package jp.co.yamaha_motor.gimac.tools.util;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Locale;

import org.springframework.jdbc.core.JdbcTemplate;

/**
 * データベースサーバーの日付と時刻を取得およびフォーマットするためのユーティリティクラス。
 */
public class ServerDateUtil {

    private ServerDateUtil() {
        throw new UnsupportedOperationException("このユーティリティクラスはインスタンス化できません。");
    }

    private static JdbcTemplate jdbcTemplate;

    /**
     * データベース操作用のJdbcTemplateを設定します。
     *
     * @param jdbcTemplate 設定するJdbcTemplate
     */
    public static void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        ServerDateUtil.jdbcTemplate = jdbcTemplate;
    }

    /**
     * データベースサーバーから現在の日付を取得します。
     *
     * @return サーバーの現在の日付を表すLocalDateオブジェクト
     */
    public static LocalDate getDbServerDate() {
        Timestamp dbServerTime = getDbServerTime();
        if (dbServerTime == null) {
            return null;
        }
        return dbServerTime.toLocalDateTime().toLocalDate();
    }

    /**
     * データベースサーバーから現在のタイムスタンプを取得します。
     *
     * @return サーバーの現在のタイムスタンプを表すTimestampオブジェクト
     */
    public static Timestamp getDbServerTime() {
        String sql = "SELECT statement_timestamp()";
        return jdbcTemplate.queryForObject(sql, Timestamp.class);
    }

    /**
     * データベースサーバーから現在の日時を取得します。
     *
     * @return サーバーの現在の日時を表すLocalDateTimeオブジェクト
     */
    private static LocalDateTime getDbServerDateTime() {
        Timestamp dbServerTime = getDbServerTime();
        if (dbServerTime == null) {
            return null;
        }
        return dbServerTime.toLocalDateTime();
    }

    /**
     * データベースサーバーの日付をロケールに基づいた中間形式の文字列にフォーマットします。
     *
     * @param locale フォーマットに使用するロケール
     * @return 中間形式でフォーマットされた日付文字列
     */
    public static String getDbServerDateMediumFormat(Locale locale) {
        LocalDate date = getDbServerDate();
        if (date == null) {
            return null;
        }
        java.util.Date utilDate = new java.util.Date(java.sql.Date.valueOf(date).getTime());
        return ConversionDateUtil.fromDateToMediumFormat(utilDate, locale);
    }

    /**
     * データベースサーバーのタイムスタンプをロケールに基づいたタイムスタンプ形式の文字列にフォーマットします。
     *
     * @param locale フォーマットに使用するロケール
     * @return フォーマットされたタイムスタンプ文字列
     */
    public static String getDbServerDateTimestampFormat(Locale locale) {
        LocalDateTime dateTime = getDbServerDateTime();
        if (dateTime == null) {
            return null;
        }
        java.util.Date date = java.util.Date.from(dateTime.atZone(ZoneId.systemDefault()).toInstant());
        return ConversionDateUtil.fromDateToTimestampFormat(date, locale);
    }

    /**
     * データベースサーバーの日付をyyyyMMdd形式の文字列にフォーマットします。
     *
     * @return yyyyMMdd形式でフォーマットされた日付文字列
     */
    public static String getDbServerDateYMD() {
        LocalDate date = getDbServerDate();
        return ConversionDateUtil.fromDateToYMDFormat(java.sql.Date.valueOf(date));
    }

    /**
     * データベースサーバーの日時をyyyyMMddHHmmss形式の文字列にフォーマットします。
     *
     * @return yyyyMMddHHmmss形式でフォーマットされた日時文字列
     */
    public static String getDbServerDateYMDHMS() {
        LocalDateTime dateTime = getDbServerDateTime();
        if (dateTime == null) {
            return null;
        }
        java.util.Date date = java.util.Date.from(dateTime.atZone(ZoneId.systemDefault()).toInstant());
        return ConversionDateUtil.fromDateToYMDHMSFormat(date);
    }
}