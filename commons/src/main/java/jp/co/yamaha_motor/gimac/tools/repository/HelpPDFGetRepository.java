package jp.co.yamaha_motor.gimac.tools.repository;

import jp.co.yamaha_motor.gimac.tools.model.DhAntiLiteralElementModel;

public interface HelpPDFGetRepository {

    /**
     * リテラル防止要素のデータを取得
     *
     * @param siteCode    サイトコード
     * @param systemCode  システムコード
     * @param screenIdFrom スクリーンID
     * @return 添付ファイル制御パラメータ
     */
    public DhAntiLiteralElementModel getAttachFileControllParameter(String siteCode, String systemCode, String screenIdFrom);
}
