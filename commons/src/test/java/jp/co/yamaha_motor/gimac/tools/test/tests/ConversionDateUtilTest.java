package jp.co.yamaha_motor.gimac.tools.test.tests;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Locale;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import jp.co.yamaha_motor.gimac.tools.util.ConversionDateUtil;

@DisplayName("ConversionDateUtilのテストクラス")
class ConversionDateUtilTest {

    @Test
    @DisplayName("ConversionDateUtilのメソッドをテストする")
    void testConversionDateUtilMethods() {
        // yyyyMMdd形式の/付文字列を中間形式に変換するテスト
        String ymdMediumFormat = ConversionDateUtil.fromYMDToMediumFormat("20240515", Locale.JAPAN);
        Assertions.assertEquals("2024/05/15", ymdMediumFormat);

        // Dateオブジェクトを中間形式に変換するテスト
        java.util.Date date = java.util.Date.from(LocalDate.of(2024, 5, 15).atStartOfDay(ZoneId.systemDefault()).toInstant());
        String dateMediumFormat = ConversionDateUtil.fromDateToMediumFormat(date, Locale.JAPAN);
        Assertions.assertEquals("2024/05/15", dateMediumFormat);

        // Dateオブジェクトをタイムスタンプ形式に変換するテスト
        String dateTimestampFormat = ConversionDateUtil.fromDateToTimestampFormat(date, Locale.JAPAN);
        Assertions.assertEquals("2024/05/15 00:00:00", dateTimestampFormat);

        // yyyyMMddHHmmss形式の/付文字列をタイムスタンプ形式に変換するテスト
        String ymdhmsTimestampFormat = ConversionDateUtil.fromYMDHMSToTimestampFormat("20240515143045", Locale.JAPAN);
        Assertions.assertEquals("2024/05/15 14:30:45", ymdhmsTimestampFormat);

        // タイムスタンプ形式の/付文字列をyyyyMMddHHmmss形式に変換するテスト
        String timestampToYMDHMS = ConversionDateUtil.fromTimestampFormatToYMDHMS("2024/05/15 14:30:45", Locale.JAPAN);
        Assertions.assertEquals("20240515143045", timestampToYMDHMS);

        // yyyyMMdd形式の/付文字列を//形式に変換するテスト
        String ymdMonthDayFormat = ConversionDateUtil.fromYMDToMonthDayFormat("20240515", Locale.JAPAN);
        Assertions.assertEquals("05/15", ymdMonthDayFormat);

        // Dateオブジェクトを//形式に変換するテスト
        String dateMonthDayFormat = ConversionDateUtil.fromDateToMonthDayFormat(date, Locale.JAPAN);
        Assertions.assertEquals("05/15", dateMonthDayFormat);

        // DateオブジェクトをyyyyMMdd形式に変換するテスト
        String dateYMDFormat = ConversionDateUtil.fromDateToYMDFormat(date);
        Assertions.assertEquals("20240515", dateYMDFormat);

        // TimestampオブジェクトをyyyyMMdd形式に変換するテスト
        java.sql.Timestamp timestamp = new java.sql.Timestamp(date.getTime());
        String timestampYMDFormat = ConversionDateUtil.fromTimestampToYMDFormat(timestamp);
        Assertions.assertEquals("20240515", timestampYMDFormat);

        // DateオブジェクトをyyyyMMddHHmmss形式に変換するテスト
        String dateYMDHMSFormat = ConversionDateUtil.fromDateToYMDHMSFormat(date);
        Assertions.assertEquals("20240515000000", dateYMDHMSFormat);

        // TimestampオブジェクトをyyyyMMddHHmmss形式に変換するテスト
        String timestampYMDHMSFormat = ConversionDateUtil.fromTimestampToYMDHMSFormat(timestamp);
        Assertions.assertEquals("20240515000000", timestampYMDHMSFormat);
    }
}