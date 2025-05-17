package jp.co.yamaha_motor.gimac.tools.model;

import lombok.Data;

@Data
public class BaseScreenDetailModel {

    /**
     * ステータス
     */
    private String rowStatus;

    /**
     * サーバに送った行番号（0始まり）
     */
    private long sendRowIndex;

    /**
     * uid番号（一意な行ID）
     */
    private long uid;

    /**
     * 表示上のINDEX
     */
    private long visibleindex;

    /**
     * 表示用ステータス
     */
    private String rowStatusDisp;

    //--------------------------------------------------------------------

    public boolean isRowStatusNotModify() {
        return "!NOTMODIFY".equals(this.rowStatus);
    }

    public boolean isRowStatusNewModify() {
        return "!NEWMODIFY".equals(this.rowStatus);
    }

    public boolean isRowStatusDataModify() {
        return "!DATAMODIFY".equals(this.rowStatus);
    }

    public boolean isRowStatusDeleted() {
        return "!DELETED".equals(this.rowStatus);
    }

    public boolean isRowStatusInvalid() {
        return ("!NEW".equals(this.rowStatus)) || ("!DELETED".equals(this.rowStatus));
    }

    public boolean isRowStatusValid() {
        return !isRowStatusInvalid();
    }

    public boolean isRowStatusNew() {
        return "!NEW".equals(this.rowStatus);
    }

}
