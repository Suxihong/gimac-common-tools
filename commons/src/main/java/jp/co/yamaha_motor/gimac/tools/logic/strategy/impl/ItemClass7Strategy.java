package jp.co.yamaha_motor.gimac.tools.logic.strategy.impl;

import jp.co.yamaha_motor.gimac.tools.logic.strategy.ItemnoEditStrategy;
import jp.co.yamaha_motor.gimac.tools.model.ItemnoEditNewItemFormatModel;
import jp.co.yamaha_motor.gimac.tools.util.StringUtil;


public class ItemClass7Strategy implements ItemnoEditStrategy {
    private static final String REFERENCE_KEY_EG = "19001";

    @Override
    public ItemnoEditNewItemFormatModel editItemno(String oldItemno, String oldItemClass, String oldReferenceKey) {
        // Implement the logic for item class 7
        ItemnoEditNewItemFormatModel model = new ItemnoEditNewItemFormatModel();
        String newItemno = oldItemno;
        if (oldItemno.length() == 10) {
            newItemno = oldItemno.substring(0, 6) + "-" + oldItemno.substring(6, 10);
        } else if (oldItemno.length() == 11) {
            newItemno = oldItemno.substring(0, 6) + "-" + oldItemno.substring(6, 11);
        }
        model.setItemno(newItemno);
        model.setItemClass(oldItemClass);
        model.setItemClassSub(" ");
        model.setReferenceKey(REFERENCE_KEY_EG);
        return model;
    }

    @Override
    public String addHyphenFormat(String itemno) {
        switch (itemno.length()) {
            case 10:
                return itemno.substring(0, 6) + "-" + itemno.substring(6, 10);
            case 11:
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
                return itemno.substring(6, 8).equals("K-") ? itemno : StringUtil.unformatString(itemno);
            case 18:
                return StringUtil.unformatString(itemno);
            default:
                return itemno;
        }
    }
}
