package jp.co.yamaha_motor.gimac.tools.logic.strategy;

import jp.co.yamaha_motor.gimac.tools.model.ItemnoEditNewItemFormatModel;

public interface ItemnoEditStrategy {
    ItemnoEditNewItemFormatModel editItemno(String oldItemno, String oldItemClass, String oldReferenceKey);

    String addHyphenFormat(String itemno);

    String excludeHyphenItemno(String itemno);
}
