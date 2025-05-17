package jp.co.yamaha_motor.gimac.tools.test.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import jp.co.yamaha_motor.gimac.tools.util.ItemnoInputValidationCheckUtil;

class ItemnoInputValidationCheckUtilTest {

    @Test
    void testIsAlphNumHyphenAsterisk_ValidInputs() {
        assertTrue(ItemnoInputValidationCheckUtil.isAlphNumHyphenAsterisk("1B7-17421-00-00-80"));
        assertTrue(ItemnoInputValidationCheckUtil.isAlphNumHyphenAsterisk("5VU100"));
        assertTrue(ItemnoInputValidationCheckUtil.isAlphNumHyphenAsterisk("BMM100-E000"));
    }

    @Test
    void testIsAlphNumHyphenAsterisk_InvalidInputs() {
        assertFalse(ItemnoInputValidationCheckUtil.isAlphNumHyphenAsterisk("abc123"));
        assertFalse(ItemnoInputValidationCheckUtil.isAlphNumHyphenAsterisk("123@!"));
        assertFalse(ItemnoInputValidationCheckUtil.isAlphNumHyphenAsterisk("ABC!@#"));
    }

    @Test
    void testIsAlphNumHyphenAsterisk_NullInput() {
        assertFalse(ItemnoInputValidationCheckUtil.isAlphNumHyphenAsterisk(null));
    }

    @Test
    void testIsMask_ValidInputs() {
        assertTrue(ItemnoInputValidationCheckUtil.isMask("12345", "\\d+"));
        assertTrue(ItemnoInputValidationCheckUtil.isMask("ABC123", "[A-Z0-9]+"));
    }

    @Test
    void testIsMask_InvalidInputs() {
        assertFalse(ItemnoInputValidationCheckUtil.isMask("abc", "\\d+"));
        assertFalse(ItemnoInputValidationCheckUtil.isMask("123@", "[A-Z0-9]+"));
    }

    @Test
    void testIsMask_NullInputs() {
        assertThrows(NullPointerException.class, () -> ItemnoInputValidationCheckUtil.isMask(null, "\\d+"));
        assertThrows(NullPointerException.class, () -> ItemnoInputValidationCheckUtil.isMask("12345", null));
    }
}