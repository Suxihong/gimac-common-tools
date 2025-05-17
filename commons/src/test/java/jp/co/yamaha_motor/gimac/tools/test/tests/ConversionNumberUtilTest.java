package jp.co.yamaha_motor.gimac.tools.test.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.Locale;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import jp.co.yamaha_motor.gimac.tools.util.ConversionNumberUtil;

@DisplayName("ConversionNumberUtilのテストクラス")
class ConversionNumberUtilTest {

    @Test
    @DisplayName("BigDecimalをフォーマットするテスト")
    void testFormatBigDecimal() {
        BigDecimal value = new BigDecimal("1234567.89");
        String format = "#,##0.00";
        Locale locale = Locale.JAPAN;
        String result = ConversionNumberUtil.formatBigDecimal(value, format, locale);
        assertEquals("1,234,567.89", result);

        assertEquals("", ConversionNumberUtil.formatBigDecimal(null, format));
        assertEquals("", ConversionNumberUtil.formatBigDecimal(value, null));
        assertEquals("", ConversionNumberUtil.formatBigDecimal(value, ""));
    }

    @Test
    @DisplayName("Integerをフォーマットするテスト")
    void testFormatInteger() {
        Integer value = 1234567;
        String format = "#,##0";
        Locale locale = Locale.JAPAN;
        String result = ConversionNumberUtil.formatInteger(value, format, locale);
        assertEquals("1,234,567", result);

        assertEquals("", ConversionNumberUtil.formatInteger(null, format));
        assertEquals("", ConversionNumberUtil.formatInteger(value, null));
        assertEquals("", ConversionNumberUtil.formatInteger(value, ""));
    }

    @Test
    @DisplayName("intをフォーマットするテスト")
    void testFormatInt() {
        int value = 1234567;
        String format = "#,##0";
        Locale locale = Locale.JAPAN;
        String result = ConversionNumberUtil.formatInt(value, format, locale);
        assertEquals("1,234,567", result);

        assertEquals("", ConversionNumberUtil.formatInt(value, null));
        assertEquals("", ConversionNumberUtil.formatInt(value, ""));
    }

    @Test
    @DisplayName("フォーマットされた数値をアンフォーマットするテスト")
    void testUnformatNumber() {
        String formattedNumber = "1,234,567.89";
        Locale locale = Locale.JAPAN;
        String result = ConversionNumberUtil.unformatNumber(formattedNumber, locale);
        assertEquals("1234567.89", result);

        assertEquals("", ConversionNumberUtil.unformatNumber(null));

        String htmlFormattedNumber = "1&#44;234&#44;567.89";
        assertEquals("1234567.89", ConversionNumberUtil.unformatNumber(htmlFormattedNumber, locale));
    }

    @Test
    @DisplayName("デフォルトロケールでのフォーマットとアンフォーマットのテスト")
    void testDefaultLocale() {
        BigDecimal value = new BigDecimal("1234567.89");
        String format = "#,##0.00";
        String result = ConversionNumberUtil.formatBigDecimal(value, format);
        assertNotNull(result);

        String formattedNumber = "1,234,567.89";
        String unformattedResult = ConversionNumberUtil.unformatNumber(formattedNumber);
        assertEquals("1234567.89", unformattedResult);
    }

    @Test
    @DisplayName("異なるロケールでのフォーマットとアンフォーマットのテスト")
    void testDifferentLocales() {
        BigDecimal value = new BigDecimal("1234567.89");
        String format = "#,##0.00";

        Locale usLocale = Locale.US;
        String usResult = ConversionNumberUtil.formatBigDecimal(value, format, usLocale);
        assertEquals("1,234,567.89", usResult);

        Locale germanLocale = Locale.GERMANY;
        String germanResult = ConversionNumberUtil.formatBigDecimal(value, format, germanLocale);
        assertEquals("1.234.567,89", germanResult);

        String usFormattedNumber = "1,234,567.89";
        String germanFormattedNumber = "1.234.567,89";

        String usUnformattedResult = ConversionNumberUtil.unformatNumber(usFormattedNumber, usLocale);
        String germanUnformattedResult = ConversionNumberUtil.unformatNumber(germanFormattedNumber, germanLocale);

        assertEquals("1234567.89", usUnformattedResult);
        assertEquals("1234567.89", germanUnformattedResult);
    }
}