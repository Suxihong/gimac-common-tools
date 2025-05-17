package jp.co.yamaha_motor.gimac.tools.model;

public class ItemnoEditNewItemFormatModel {

    /**
     * 品目番号
     */
    private String itemno;

    /**
     * 品目クラス
     */
    private String itemClass;

    /**
     * 品目サブクラス
     */
    private String itemClassSub;

    /**
     * 品目分類コード
     */
    private String referenceKey;


    /**
     * @return itemno
     */
    public String getItemno() {
        return itemno;
    }

    /**
     * @param itemno
     */
    public void setItemno(String itemno) {
        this.itemno = itemno;
    }

    /**
     * @return itemClass
     */
    public String getItemClass() {
        return itemClass;
    }

    /**
     * @param itemClass
     */
    public void setItemClass(String itemClass) {
        this.itemClass = itemClass;
    }

    /**
     * @return itemClassSub
     */
    public String getItemClassSub() {
        return itemClassSub;
    }

    /**
     * @param itemClassSub
     */
    public void setItemClassSub(String itemClassSub) {
        this.itemClassSub = itemClassSub;
    }

    /**
     * @return referenceKey
     */
    public String getReferenceKey() {
        return referenceKey;
    }

    /**
     * @param referenceKey
     */
    public void setReferenceKey(String referenceKey) {
        this.referenceKey = referenceKey;
    }

}