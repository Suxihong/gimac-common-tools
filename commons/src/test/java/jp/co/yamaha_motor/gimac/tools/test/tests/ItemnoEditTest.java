package jp.co.yamaha_motor.gimac.tools.test.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import jp.co.yamaha_motor.gimac.tools.logic.impl.ItemnoEditLogicImpl;

public class ItemnoEditTest {
    @Test
    void test() {
        ItemnoEditLogicImpl testItemnoEdit = new ItemnoEditLogicImpl();
        //Item Class 1
        String test1Str = "GY00204";
        String result1Str = testItemnoEdit.fromOldItemToNewItemFormat(test1Str, "1", "test").getItemno();
        Assertions.assertEquals("GY00204", result1Str);

        //Item Class2
        String test2Str = "1B717421000080";
        String result2Str = testItemnoEdit.fromOldItemToNewItemFormat(test2Str, "2", "test").getItemno();
        Assertions.assertEquals("1B7-17421-00-00-80", result2Str);

        //Item Class3
        String test3Str = "BDUC00B01A";
        String result3Str = testItemnoEdit.fromOldItemToNewItemFormat(test3Str, "3", "test").getItemno();
        Assertions.assertEquals("BDUC00-001A-B", result3Str);
    }
}
