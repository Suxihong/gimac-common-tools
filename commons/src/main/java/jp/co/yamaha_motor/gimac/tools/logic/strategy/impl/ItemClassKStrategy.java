package jp.co.yamaha_motor.gimac.tools.logic.strategy.impl;

import jp.co.yamaha_motor.gimac.tools.logic.strategy.ItemnoEditStrategy;
import jp.co.yamaha_motor.gimac.tools.model.ItemnoEditNewItemFormatModel;
import jp.co.yamaha_motor.gimac.tools.util.StringUtil;


public class ItemClassKStrategy implements ItemnoEditStrategy {
    @Override
    public ItemnoEditNewItemFormatModel editItemno(String oldItemno, String oldItemClass, String oldReferenceKey) {
        // Implement the logic for item class K
        ItemnoEditNewItemFormatModel model = new ItemnoEditNewItemFormatModel();
        String newItemno = oldItemno;
        if (oldItemno.length() == 9) {
            newItemno = oldItemno.substring(0, 6) + "-" + oldItemno.substring(6, 9);
        } else if (oldItemno.length() == 10) {
            newItemno = oldItemno.substring(0, 6) + "-" + oldItemno.substring(6, 10);
        }
        model.setItemno(newItemno);
        model.setItemClass(oldItemClass);
        model.setItemClassSub(" ");
        model.setReferenceKey(" ");
        return model;
    }

    @Override
    public String addHyphenFormat(String itemno) {
        switch (itemno.length()) {
            case 8:
                return itemno;
            case 9:
                return "@".equals(itemno.substring(0, 1)) ? itemno : itemno.substring(0, 6) + "-" + itemno.substring(6, 9);
            case 10:
                return itemno;
            default:
                return itemno;
        }
    }

    @Override
    public String excludeHyphenItemno(String itemno) {
        switch (itemno.length()) {
            case 8:
            case 9:
                return itemno;
            case 10:
                return itemno.substring(6, 8).equals("K-") ? itemno : StringUtil.unformatString(itemno);
            default:
                return itemno;
        }
    }
}
