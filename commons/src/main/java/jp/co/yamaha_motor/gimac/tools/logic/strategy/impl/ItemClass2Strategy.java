package jp.co.yamaha_motor.gimac.tools.logic.strategy.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import jp.co.yamaha_motor.gimac.tools.logic.strategy.ItemnoEditStrategy;
import jp.co.yamaha_motor.gimac.tools.model.ItemnoEditNewItemFormatModel;
import jp.co.yamaha_motor.gimac.tools.util.StringUtil;

public class ItemClass2Strategy implements ItemnoEditStrategy {
    private static final String REFERENCE_KEY_EG = "19001";
    private static final Map<String, String> ITEM_CLASS_SUB_MAP = new HashMap<>();
    private static final Map<String, String> REFERENCE_KEY_MAP = new HashMap<>();
    private static final Map<String, Function<String, String>> FORMAT_MAP = new HashMap<>();

    static {
        ITEM_CLASS_SUB_MAP.put("9V", "2");
        ITEM_CLASS_SUB_MAP.put("9", "2");
        ITEM_CLASS_SUB_MAP.put("T", "2");
        ITEM_CLASS_SUB_MAP.put(REFERENCE_KEY_EG, "7");

        REFERENCE_KEY_MAP.put(REFERENCE_KEY_EG, REFERENCE_KEY_EG);

        FORMAT_MAP.put("9V", oldItemno -> oldItemno.substring(0, 3) + "-" + oldItemno.substring(3, 14));
        FORMAT_MAP.put("9", oldItemno -> oldItemno.substring(0, 5) + "-" + oldItemno.substring(5, 10) + "-" + oldItemno.substring(10, 12) + "-" + oldItemno.substring(12, 14));
        FORMAT_MAP.put("T", oldItemno -> oldItemno.substring(0, 5) + "-" + oldItemno.substring(5, 10) + "-" + oldItemno.substring(10, 12) + "-" + oldItemno.substring(12, 14));
        FORMAT_MAP.put("DEFAULT", oldItemno -> oldItemno.substring(0, 3) + "-" + oldItemno.substring(3, 8) + "-" + oldItemno.substring(8, 10) + "-" + oldItemno.substring(10, 12) + "-" + oldItemno.substring(12, 14));
    }

    @Override
    public ItemnoEditNewItemFormatModel editItemno(String oldItemno, String oldItemClass, String oldReferenceKey) {
        ItemnoEditNewItemFormatModel model = new ItemnoEditNewItemFormatModel();
        String newItemno = oldItemno;
        String newItemClassSub = "2";
        String newReferenceKey = oldReferenceKey;
        if (oldItemno.length() == 14) {
            newItemno = formatLongItemno(oldItemno);
            newItemClassSub = ITEM_CLASS_SUB_MAP.getOrDefault(oldItemno.substring(3, 8), "2");
            newReferenceKey = REFERENCE_KEY_MAP.getOrDefault(oldItemno.substring(3, 8), " ");
        } else if (oldItemno.length() == 9) {
            newItemClassSub = "S";
            newReferenceKey = " ";
            newItemno = oldItemno.substring(0, 6) + "-" + oldItemno.substring(6, 9);
        } else if (oldItemno.length() == 10) {
            newItemClassSub = "D";
            newReferenceKey = " ";
            newItemno = oldItemno.substring(0, 6) + "-" + oldItemno.substring(6, 10);
        }
        model.setItemno(newItemno);
        model.setItemClass(oldItemClass);
        model.setItemClassSub(newItemClassSub);
        model.setReferenceKey(newReferenceKey);
        return model;
    }

    private String formatLongItemno(String oldItemno) {
        return FORMAT_MAP.getOrDefault(oldItemno.substring(0, 2), FORMAT_MAP.get("DEFAULT")).apply(oldItemno);
    }

    @Override
    public String addHyphenFormat(String itemno) {
        switch (itemno.length()) {
            case 9:
                return itemno.substring(0, 6) + "-" + itemno.substring(6, 9);
            case 10:
                return itemno.substring(0, 6) + "-" + itemno.substring(6, 10);
            case 14:
                if ("9V".equals(itemno.substring(0, 2))) {
                    return itemno.substring(0, 3) + "-" + itemno.substring(3, 14);
                } else if ("9".equals(itemno.substring(0, 1))) {
                    return itemno.substring(0, 5) + "-" + itemno.substring(5, 10) + "-" + itemno.substring(10, 12) + "-" + itemno.substring(12, 14);
                } else if ("T".equals(itemno.substring(11, 12))) {
                    return itemno.substring(0, 5) + "-" + itemno.substring(5, 10) + "-" + itemno.substring(10, 12) + "-" + itemno.substring(12, 14);
                } else {
                    return itemno.substring(0, 3) + "-" + itemno.substring(3, 8) + "-" + itemno.substring(8, 10) + "-" + itemno.substring(10, 12) + "-" + itemno.substring(12, 14);
                }
            default:
                return itemno;
        }
    }

    @Override
    public String excludeHyphenItemno(String itemno) {
        switch (itemno.length()) {
            case 10:
            case 11:
            case 15:
            case 17:
            case 18:
                return StringUtil.unformatString(itemno);
            default:
                return itemno;
        }
    }
}
