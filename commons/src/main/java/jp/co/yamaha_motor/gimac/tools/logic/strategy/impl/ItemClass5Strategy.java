package jp.co.yamaha_motor.gimac.tools.logic.strategy.impl;

import jp.co.yamaha_motor.gimac.tools.logic.strategy.ItemnoEditStrategy;
import jp.co.yamaha_motor.gimac.tools.model.ItemnoEditNewItemFormatModel;
import jp.co.yamaha_motor.gimac.tools.util.StringUtil;


public class ItemClass5Strategy implements ItemnoEditStrategy {
    @Override
    public ItemnoEditNewItemFormatModel editItemno(String oldItemno, String oldItemClass, String oldReferenceKey) {
        ItemnoEditNewItemFormatModel model = new ItemnoEditNewItemFormatModel();
        String newItemno = oldItemno.length() == 14 ? formatLongItemno(oldItemno) : formatShortItemno(oldItemno);
        model.setItemno(newItemno);
        model.setItemClass("3");
        model.setItemClassSub("1");
        model.setReferenceKey(" ");
        return model;
    }

    private String formatLongItemno(String oldItemno) {
        return oldItemno.substring(0, 6) + "-" + oldItemno.substring(6, 10) + "-" + oldItemno.substring(10, 14);
    }

    private String formatShortItemno(String oldItemno) {
        return oldItemno.substring(0, 6) + "-0" + oldItemno.substring(7, 10) + "-" + oldItemno.substring(6, 7);
    }

    @Override
    public String addHyphenFormat(String itemno) {
        switch (itemno.length()) {
            case 10:
                return itemno.substring(0, 6) + "-" + itemno.substring(6, 10);
            case 13:
            case 14:
                return itemno;
            default:
                return itemno;
        }
    }

    @Override
    public String excludeHyphenItemno(String itemno) {
        switch (itemno.length()) {
            case 11:
                return StringUtil.unformatString(itemno);
            case 13:
            case 14:
                return itemno;
            default:
                return itemno;
        }
    }
}
