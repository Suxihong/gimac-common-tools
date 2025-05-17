package jp.co.yamaha_motor.gimac.tools.service;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.yamaha_motor.gimac.tools.model.ConvertTimestampModel;
import jp.co.yamaha_motor.gimac.tools.repository.ConvertTimeStampRepository;

@Service
public class ConvertTimeStampService {

    @Autowired
    private ConvertTimeStampRepository convertTimeStampRepository;

    /**
     * 指定した日本時間を基準にTO指定した拠点の時刻に変換
     * @param timestamp
     * @param siteCode
     * @return
     */
    public ConvertTimestampModel getConvertTimeStampYMCtoSite(Timestamp timestamp, String siteCode) {
        return convertTimeStampRepository.getConvertTimeStampYMCtoSite(timestamp, siteCode);
    }

    /**
     * FROM指定した拠点の時刻を基準にTO指定した拠点の時刻に変換
     * @param timestamp
     * @param fromSiteCode
     * @param toSiteCode
     * @return
     */
    public ConvertTimestampModel getConvertTimeStampSitetoSite(Timestamp timestamp, String fromSiteCode,
            String toSiteCode) {
        return convertTimeStampRepository.getConvertTimeStampSitetoSite(timestamp, fromSiteCode, toSiteCode);
    }
}
