package jp.co.yamaha_motor.gimac.tools.test.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import jp.co.yamaha_motor.gimac.tools.util.NumberValidationUtil;

@DisplayName("NumberValidationUtilのテストクラス")
class NumberValidationUtilTest {

    @Test
    @DisplayName("数値が最小値以上であるかを検証するテスト")
    void testValidateMin() {
        assertTrue(NumberValidationUtil.validateMin("123.45", new BigDecimal("100.00"))); // 最小値以上
        assertFalse(NumberValidationUtil.validateMin("99.99", new BigDecimal("100.00"))); // 最小値未満
    }

    @Test
    @DisplayName("数値が指定された最小値より大きいかを検証するテスト（validateMinGt）")
    void testValidateMinGt() {
        // 正常ケース
        assertTrue(NumberValidationUtil.validateMinGt("100.01", new BigDecimal("100.00"))); // 大きい場合
        assertFalse(NumberValidationUtil.validateMinGt("100.00", new BigDecimal("100.00"))); // 等しい場合
        assertFalse(NumberValidationUtil.validateMinGt("99.99", new BigDecimal("100.00"))); // 小さい場合

        // 異常ケース
        assertFalse(NumberValidationUtil.validateMinGt("abc", new BigDecimal("100.00"))); // 無効な文字列
        assertFalse(NumberValidationUtil.validateMinGt("", new BigDecimal("100.00"))); // 空文字列
        assertFalse(NumberValidationUtil.validateMinGt(null, new BigDecimal("100.00"))); // null
    }

    @Test
    @DisplayName("数値が最大値以下であるかを検証するテスト")
    void testValidateMax() {
        assertTrue(NumberValidationUtil.validateMax("123.45", new BigDecimal("200.00"))); // 最大値以下
        assertFalse(NumberValidationUtil.validateMax("200.01", new BigDecimal("200.00"))); // 最大値を超える
    }

    @Test
    @DisplayName("数値が指定された最大値より小さいかを検証するテスト（validateMaxLt）")
    void testValidateMaxLt() {
        // 正常ケース
        assertTrue(NumberValidationUtil.validateMaxLt("99.99", new BigDecimal("100.00"))); // 小さい場合
        assertFalse(NumberValidationUtil.validateMaxLt("100.00", new BigDecimal("100.00"))); // 等しい場合
        assertFalse(NumberValidationUtil.validateMaxLt("100.01", new BigDecimal("100.00"))); // 大きい場合

        // 異常ケース
        assertFalse(NumberValidationUtil.validateMaxLt("abc", new BigDecimal("100.00"))); // 無効な文字列
        assertFalse(NumberValidationUtil.validateMaxLt("", new BigDecimal("100.00"))); // 空文字列
        assertFalse(NumberValidationUtil.validateMaxLt(null, new BigDecimal("100.00"))); // null
    }

    @Test
    @DisplayName("数値が指定された範囲内であるかを検証するテスト")
    void testValidateRange() {
        assertTrue(NumberValidationUtil.validateRange("123.45", new BigDecimal("100.00"), new BigDecimal("200.00"))); // 範囲内
        assertFalse(NumberValidationUtil.validateRange("99.99", new BigDecimal("100.00"), new BigDecimal("200.00"))); // 範囲外（下限未満）
    }

    @Test
    @DisplayName("入力文字列が有効な数値形式であるかを検証するテスト")
    void testIsNumeric() {
        assertTrue(NumberValidationUtil.isNumeric("123")); // 有効な数値
        assertFalse(NumberValidationUtil.isNumeric("abc")); // 無効な文字列
    }

    @Test
    @DisplayName("入力文字列が半角数字のみで構成されているかを検証するテスト")
    void testIsHalfWidthNumeric() {
        assertTrue(NumberValidationUtil.isHalfWidthNumeric("123456")); // 半角数字のみ
        assertFalse(NumberValidationUtil.isHalfWidthNumeric("１２３４５６")); // 全角数字
    }
}