package jp.co.yamaha_motor.gimac.tools.repository;

import java.sql.Timestamp;

import jp.co.yamaha_motor.gimac.tools.model.ConvertTimestampModel;

/**
 * ConvertTimeStamp リポジトリ クラス
 * @author VP01365
 * @version 1.0.0
 */
public interface ConvertTimeStampRepository {

    /**
     * 指定した日本時間を基準にTO指定した拠点の時刻に変換
     * @param timestamp
     * @param siteCode
     * @return
     */
    public ConvertTimestampModel getConvertTimeStampYMCtoSite(Timestamp timestamp, String siteCode);

    /**
     * FROM指定した拠点の時刻を基準にTO指定した拠点の時刻に変換
     * @param timestamp
     * @param fromSiteCode
     * @param toSiteCode
     * @return
     */
    public ConvertTimestampModel getConvertTimeStampSitetoSite(Timestamp timestamp, String fromSiteCode, String toSiteCode);
}
