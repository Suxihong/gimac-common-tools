package jp.co.yamaha_motor.gimac.tools.logic.strategy;

import jp.co.yamaha_motor.gimac.tools.model.ItemnoEditNewItemFormatModel;

public class DefaultItemnoEditStrategy implements ItemnoEditStrategy {
    @Override
    public ItemnoEditNewItemFormatModel editItemno(String oldItemno, String oldItemClass, String oldReferenceKey) {
        // Default implementation
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
