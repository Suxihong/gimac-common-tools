package jp.co.yamaha_motor.gimac.tools.logic;

import jp.co.yamaha_motor.gimac.tools.model.ItemnoEditNewItemFormatModel;

/**
 * 品目番号変換に関する共通ロジック
 */
public interface ItemnoEditLogic {

    /**
     * 旧品目番号体系を新品目番号体系に変換する（品目番号のハイフン編集、品目クラス取得）
     *
     * @param oldItemno       変換前の品目番号
     * @param oldItemClass    変換前の品目クラス
     * @param oldReferenceKey 変換前の品目分類コード
     * @return model       変換後の品目情報
     */
    public ItemnoEditNewItemFormatModel fromOldItemToNewItemFormat(String oldItemno, String oldItemClass, String oldReferenceKey);

    /**
     * 品目番号ハイフン編集
     *
     * @param itemno    品目番号
     * @param itemClass 品目クラス
     * @return editItemno     ハイフン編集済み品目番号
     */
    public String addHyphenFormat(String itemno, String itemClass);

    /**
     * 品目番号ハイフン除去
     *
     * @param itemno    ハイフン編集済み品目番号
     * @param itemClass 品目クラス
     * @return unEditItemno  品目番号
     */
    public String excludeHyphenItemno(String itemno, String itemClass);
}