package jp.co.yamaha_motor.gimac.tools.logic.strategy.impl;

import jp.co.yamaha_motor.gimac.tools.logic.strategy.ItemnoEditStrategy;
import jp.co.yamaha_motor.gimac.tools.model.ItemnoEditNewItemFormatModel;


public class ItemClassMStrategy implements ItemnoEditStrategy {
    @Override
    public ItemnoEditNewItemFormatModel editItemno(String oldItemno, String oldItemClass, String oldReferenceKey) {
        // Implement the logic for item class M
        ItemnoEditNewItemFormatModel model = new ItemnoEditNewItemFormatModel();
        model.setItemno(oldItemno);
        model.setItemClass(oldItemClass);
        model.setItemClassSub(" ");
        model.setReferenceKey(" ");
        return model;
    }

    @Override
    public String addHyphenFormat(String itemno) {
        return itemno;
    }

    @Override
    public String excludeHyphenItemno(String itemno) {
        return itemno;
    }
}
