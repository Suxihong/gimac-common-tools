package jp.co.yamaha_motor.gimac.tools.logic.impl;

import jp.co.yamaha_motor.gimac.tools.logic.ItemnoEditLogic;
import jp.co.yamaha_motor.gimac.tools.logic.strategy.ItemnoEditStrategy;
import jp.co.yamaha_motor.gimac.tools.logic.strategy.factory.ItemnoEditStrategyFactory;
import jp.co.yamaha_motor.gimac.tools.model.ItemnoEditNewItemFormatModel;


/**
 * 品目番号変換に関する共通ロジック
 *
 * Spring Bean 定義: {@link jp.co.yamaha_motor.gimac.tools.logic.ItemnoEditLogic IItemnoEditLogic}
 */
public class ItemnoEditLogicImpl implements ItemnoEditLogic {

    /******************************************
     *  プログラム内定数定義
     ******************************************/
    /**
     * 品目分類コード (エンジン)
     */
    private static final String REFERENCE_KEY_EG = "19001";

    /**
     * ハイフン ("-")
     */
    private static final String HYPHEN = "-";

    /**
     * スペース (１桁:"△")
     */
    private static final String SPACE_ONE = " ";

    private static final String ITEM_CLASS_M = "M";    //CBU機種		→ 機種
    private static final String ITEM_CLASS_E = "E";    //CBU製品		→ 製品
    private static final String ITEM_CLASS_K = "K";    //CKD機種		→ 輸出CKDセット
    private static final String ITEM_CLASS_F = "F";    //CKD製品		→ 輸出CKDセット明細
    private static final String ITEM_CLASS_3 = "3";    //CBUグループ	→ グループ
    private static final String ITEM_CLASS_5 = "5";    //CKDグループ
    private static final String ITEM_CLASS_7 = "7";    //E/G ASSY		→ E/G
    private static final String ITEM_CLASS_2 = "2";    //部品			→ 部品
    private static final String ITEM_CLASS_1 = "1";    //原材料		→ 原材料


    /**
     * PYMAC-IIIからのデータコンバージョン専用
     * 旧品目番号体系を新品目番号体系に変換する（品目番号のハイフン編集、品目クラス取得）
     * 個別プログラムでの利用はできません。
     * 個別プログラムでの品目番号ハイフン編集は を参照
     *
     * @param oldItemno       変換前の品目番号
     * @param oldItemClass    変換前の品目クラス
     * @param oldReferenceKey 変換前の品目分類コード
     * @return model       変換後の品目情報
     */
    public ItemnoEditNewItemFormatModel fromOldItemToNewItemFormat(String oldItemno, String oldItemClass, String oldReferenceKey) {
        ItemnoEditStrategy strategy = ItemnoEditStrategyFactory.getStrategy(oldItemClass);
        return strategy.editItemno(oldItemno, oldItemClass, oldReferenceKey);
    }

    /**
     * 品目番号ハイフン編集
     *
     * @param itemno    品目番号
     * @param itemClass 品目クラス
     * @return editItemno     編集済み品目番号
     */
    public String addHyphenFormat(String itemno, String itemClass) {
        ItemnoEditStrategy strategy = ItemnoEditStrategyFactory.getStrategy(itemClass);
        return strategy.addHyphenFormat(itemno);
    }

    /**
     * 品目番号ハイフン除去
     *
     * @param itemno    ハイフン編集済み品目番号
     * @param itemClass 品目クラス
     * @return unEditItemno  品目番号
     */
    public String excludeHyphenItemno(String itemno, String itemClass) {
        ItemnoEditStrategy strategy = ItemnoEditStrategyFactory.getStrategy(itemClass);
        return strategy.excludeHyphenItemno(itemno);
    }
}