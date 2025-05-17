package jp.co.yamaha_motor.gimac.tools.logic.strategy.impl;

import jp.co.yamaha_motor.gimac.tools.logic.strategy.ItemnoEditStrategy;
import jp.co.yamaha_motor.gimac.tools.model.ItemnoEditNewItemFormatModel;
import jp.co.yamaha_motor.gimac.tools.util.StringUtil;


public class ItemClass1Strategy implements ItemnoEditStrategy {
    @Override
    public ItemnoEditNewItemFormatModel editItemno(String oldItemno, String oldItemClass, String oldReferenceKey) {
        // Implement the logic for item class 1
        ItemnoEditNewItemFormatModel model = new ItemnoEditNewItemFormatModel();
        String newItemno = oldItemno;
        if (oldItemno.length() == 14) {
            newItemno = oldItemno.substring(0, 5) + "-" + oldItemno.substring(5, 8) + "-" + oldItemno.substring(8, 11) + "-" + oldItemno.substring(11, 14);
        }
        model.setItemno(newItemno);
        model.setItemClass(oldItemClass);
        model.setItemClassSub(" ");
        model.setReferenceKey(oldReferenceKey);
        return model;
    }

    @Override
    public String addHyphenFormat(String itemno) {
        switch (itemno.length()) {
            case 7:
                return itemno;
            case 14:
                return itemno.substring(0, 5) + "-" + itemno.substring(5, 8) + "-" + itemno.substring(8, 11) + "-" + itemno.substring(11, 14);
            default:
                return itemno;
        }
    }

    @Override
    public String excludeHyphenItemno(String itemno) {
        switch (itemno.length()) {
            case 7:
                return itemno;
            case 17:
                return StringUtil.unformatString(itemno);
            default:
                return itemno;
        }
    }
}
