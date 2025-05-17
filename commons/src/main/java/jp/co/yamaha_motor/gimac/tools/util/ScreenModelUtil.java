package jp.co.yamaha_motor.gimac.tools.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.co.yamaha_motor.gimac.tools.model.BaseScreenDetailModel;

public class ScreenModelUtil {

    /**
     * 明细部に項目の重複チェック
     * @param screenList
     * @param keyFields
     * @return
     */
    public static int checkDuplicateRow(List<BaseScreenDetailModel> screenList, String[] keyFields) {

        List<String> keyList; // save key values of a row
        Map<List<String>, BaseScreenDetailModel> keyMap = new HashMap<>(); // save key list

        for (BaseScreenDetailModel model : screenList) {
            // deleted and new blank row is no need to check
            if (model.isRowStatusInvalid()) {
                continue;
            }

            // add key values to keyList
            keyList = new ArrayList<>();
            for (String propertyName : keyFields) {
                keyList.add(String.valueOf(BeanUtil.getPropertyValue(model, propertyName)));
            }

            // check duplicate
            if (keyMap.containsKey(keyList)) {
                if (keyMap.get(keyList).isRowStatusNewModify()) {
                    return screenList.indexOf(keyMap.get(keyList));
                } else {
                    return (int) model.getSendRowIndex();
                }
            } else {
                keyMap.put(keyList, model);
            }
        }

        return -1;
    }
}
