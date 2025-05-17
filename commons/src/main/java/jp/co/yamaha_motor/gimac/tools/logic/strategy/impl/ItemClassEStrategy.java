package jp.co.yamaha_motor.gimac.tools.logic.strategy.impl;

import jp.co.yamaha_motor.gimac.tools.logic.strategy.ItemnoEditStrategy;
import jp.co.yamaha_motor.gimac.tools.model.ItemnoEditNewItemFormatModel;
import jp.co.yamaha_motor.gimac.tools.util.StringUtil;


public class ItemClassEStrategy implements ItemnoEditStrategy {
    @Override
    public ItemnoEditNewItemFormatModel editItemno(String oldItemno, String oldItemClass, String oldReferenceKey) {
        // Implement the logic for item class E
        ItemnoEditNewItemFormatModel model = new ItemnoEditNewItemFormatModel();
        model.setItemno(oldItemno.substring(0, 6) + "-" + oldItemno.substring(6, 10));
        model.setItemClass(oldItemClass);
        model.setItemClassSub(" ");
        model.setReferenceKey(" ");
        return model;
    }

    @Override
    public String addHyphenFormat(String itemno) {
        return itemno.length() == 10 ? itemno.substring(0, 6) + "-" + itemno.substring(6, 10) : itemno;
    }

    @Override
    public String excludeHyphenItemno(String itemno) {
        return StringUtil.unformatString(itemno);
    }
}
