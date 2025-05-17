package jp.co.yamaha_motor.gimac.tools.test.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import jp.co.yamaha_motor.gimac.tools.util.DateValidationUtil;

@DisplayName("DateValidationUtilのテストクラス")
class DateValidationUtilTest {

    @Test
    @DisplayName("有効な日付形式（yyyyMMdd）を検証するテスト")
    void testisValidDate_yyyyMMdd() {
        // 正常ケース
        assertTrue(DateValidationUtil.isValidDate("20230101", "yyyyMMdd")); // 正常な日付
        assertTrue(DateValidationUtil.isValidDate("19991231", "yyyyMMdd")); // 過去の日付

        // 異常ケース
        assertFalse(DateValidationUtil.isValidDate("20230132", "yyyyMMdd")); // 存在しない日付
        assertFalse(DateValidationUtil.isValidDate("2023-01-01", "yyyyMMdd")); // フォーマット不一致
        assertFalse(DateValidationUtil.isValidDate("abc", "yyyyMMdd")); // 無効な文字列
        assertFalse(DateValidationUtil.isValidDate("", "yyyyMMdd")); // 空文字列
        assertFalse(DateValidationUtil.isValidDate(null, "yyyyMMdd")); // null
    }

    @Test
    @DisplayName("有効な年月形式（yyyyMM）を検証するテスト")
    void testisValidDate_yyyyMM() {
        // 正常ケース
        assertTrue(DateValidationUtil.isValidDate("202301", "yyyyMM")); // 正常な年月
        assertTrue(DateValidationUtil.isValidDate("199912", "yyyyMM")); // 過去の年月

        // 異常ケース
        assertFalse(DateValidationUtil.isValidDate("202313", "yyyyMM")); // 存在しない月
        assertFalse(DateValidationUtil.isValidDate("2023", "yyyyMM")); // 不完全な形式
        assertFalse(DateValidationUtil.isValidDate("abcd01", "yyyyMM")); // 無効な文字列
        assertFalse(DateValidationUtil.isValidDate("", "yyyyMM")); // 空文字列
        assertFalse(DateValidationUtil.isValidDate(null, "yyyyMM")); // null
    }

    @Test
    @DisplayName("有効な日時形式（yyyyMMddHHmmss）を検証するテスト")
    void testisValidDate_yyyyMMddHHmmss() {
        // 正常ケース
        assertTrue(DateValidationUtil.isValidDate("20230101120000", "yyyyMMddHHmmss")); // 正常な日時
        assertTrue(DateValidationUtil.isValidDate("19991231235959", "yyyyMMddHHmmss")); // 過去の日時

        // 異常ケース
        assertFalse(DateValidationUtil.isValidDate("20230101250000", "yyyyMMddHHmmss")); // 存在しない時間
        assertFalse(DateValidationUtil.isValidDate("20230101", "yyyyMMddHHmmss")); // 不完全な形式
        assertFalse(DateValidationUtil.isValidDate("abc", "yyyyMMddHHmmss")); // 無効な文字列
        assertFalse(DateValidationUtil.isValidDate("", "yyyyMMddHHmmss")); // 空文字列
        assertFalse(DateValidationUtil.isValidDate(null, "yyyyMMddHHmmss")); // null
    }

    @Test
    @DisplayName("異なるフォーマットでの検証テスト")
    void testisValidDate_DifferentFormats() {
        // 正常ケース
        assertTrue(DateValidationUtil.isValidDate("2023-01-01", "yyyy-MM-dd")); // ハイフン区切り
        assertTrue(DateValidationUtil.isValidDate("01/01/2023", "MM/dd/yyyy")); // スラッシュ区切り

        // 異常ケース
        assertFalse(DateValidationUtil.isValidDate("2023/01/01", "yyyy-MM-dd")); // フォーマット不一致
        assertFalse(DateValidationUtil.isValidDate("01-01-2023", "MM/dd/yyyy")); // フォーマット不一致
    }

    @Test
    @DisplayName("部分的なフォーマット（yyyy-MM）を検証するテスト")
    void testisValidDate_PartialFormat() {
        // 正常ケース
        assertTrue(DateValidationUtil.isValidDate("2023-01", "yyyy-MM")); // 年月のみ

        // 異常ケース
        assertFalse(DateValidationUtil.isValidDate("2023-13", "yyyy-MM")); // 存在しない月
        assertFalse(DateValidationUtil.isValidDate("2023", "yyyy-MM")); // 不完全な形式
    }
}