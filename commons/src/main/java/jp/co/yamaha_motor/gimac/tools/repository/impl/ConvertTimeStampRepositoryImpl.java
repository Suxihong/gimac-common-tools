package jp.co.yamaha_motor.gimac.tools.repository.impl;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ymsl.solid.jpa.query.JpaNativeQuerySupportRepository;

import jp.co.yamaha_motor.gimac.tools.model.ConvertTimestampModel;
import jp.co.yamaha_motor.gimac.tools.repository.ConvertTimeStampRepository;

/**
 * ConvertTimeStamp リポジトリ 実装する
 * @author VP01365
 * @version 1.0.0
 */
@Repository
public class ConvertTimeStampRepositoryImpl extends JpaNativeQuerySupportRepository implements ConvertTimeStampRepository {

    /**
     * 指定した日本時間を基準にTO指定した拠点の時刻に変換
     * @param timestamp
     * @param siteCode
     * @return
     */
    @Override
    public ConvertTimestampModel getConvertTimeStampYMCtoSite(Timestamp timestamp, String siteCode) {
        StringBuilder sql = new StringBuilder();
        Map<String, Object> param = new HashMap<>();
        sql.append("SELECT DHYS0001(:timestamp, :siteCode) AS convertTimestamp");
        param.put("timestamp", timestamp);
        param.put("siteCode", siteCode);
        return queryForList(sql.toString(), param, ConvertTimestampModel.class).get(0);
    }

    /**
     * FROM指定した拠点の時刻を基準にTO指定した拠点の時刻に変換
     * @param timestamp
     * @param fromSiteCode
     * @param toSiteCode
     * @return
     */
    @Override
    public ConvertTimestampModel getConvertTimeStampSitetoSite(Timestamp timestamp, String fromSiteCode,
            String toSiteCode) {
        StringBuilder sql = new StringBuilder();
        Map<String, Object> param = new HashMap<>();
        sql.append("SELECT DHYS0002(:timestamp, :fromSiteCode, :toSiteCode) AS convertTimestamp");
        param.put("timestamp", timestamp);
        param.put("fromSiteCode", fromSiteCode);
        param.put("toSiteCode", toSiteCode);
        return queryForList(sql.toString(), param, ConvertTimestampModel.class).get(0);
    }

}
