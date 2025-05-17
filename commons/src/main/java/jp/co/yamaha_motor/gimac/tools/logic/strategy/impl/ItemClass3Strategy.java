package jp.co.yamaha_motor.gimac.tools.logic.strategy.impl;

import java.util.HashMap;
import java.util.Map;

import jp.co.yamaha_motor.gimac.tools.logic.strategy.ItemnoEditStrategy;
import jp.co.yamaha_motor.gimac.tools.model.ItemnoEditNewItemFormatModel;
import jp.co.yamaha_motor.gimac.tools.util.StringUtil;

public class ItemClass3Strategy implements ItemnoEditStrategy {
    private static final Map<String, String> ITEM_CLASS_SUB_MAP = new HashMap<>();

    static {
        ITEM_CLASS_SUB_MAP.put("B", "1");
        ITEM_CLASS_SUB_MAP.put("K", "3");
        ITEM_CLASS_SUB_MAP.put("P", "5");
        ITEM_CLASS_SUB_MAP.put("G", "1");
    }

    @Override
    public ItemnoEditNewItemFormatModel editItemno(String oldItemno, String oldItemClass, String oldReferenceKey) {
        ItemnoEditNewItemFormatModel model = new ItemnoEditNewItemFormatModel();
        String newItemClassSub = ITEM_CLASS_SUB_MAP.getOrDefault(oldItemno.substring(6, 7), "1");
        String newItemno = formatItemno(oldItemno);
        model.setItemno(newItemno);
        model.setItemClass(oldItemClass);
        model.setItemClassSub(newItemClassSub);
        model.setReferenceKey(" ");
        return model;
    }

    private String formatItemno(String oldItemno) {
        return oldItemno.substring(0, 6) + "-0" + oldItemno.substring(7, 10) + "-" + oldItemno.substring(6, 7);
    }

    @Override
    public String addHyphenFormat(String itemno) {
        return itemno.length() == 10 ? itemno.substring(0, 6) + "-" + itemno.substring(6, 10) : itemno;
    }

    @Override
    public String excludeHyphenItemno(String itemno) {
        return itemno.length() == 11 ? StringUtil.unformatString(itemno) : itemno;
    }
}
