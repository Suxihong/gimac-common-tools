package jp.co.yamaha_motor.gimac.tools.repository.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ymsl.solid.jpa.query.JpaNativeQuerySupportRepository;

import jp.co.yamaha_motor.gimac.tools.model.DhAntiLiteralElementModel;
import jp.co.yamaha_motor.gimac.tools.repository.HelpPDFGetRepository;

@Repository
public class HelpPDFGetRepositoryImpl extends JpaNativeQuerySupportRepository implements HelpPDFGetRepository {

    @Override
    public DhAntiLiteralElementModel getAttachFileControllParameter(String siteCode, String systemCode,
            String screenIdFrom) {
        StringBuilder sql = new StringBuilder();
        Map<String, Object> params = new HashMap<>();
        sql.append(" SELECT replace_value1  AS replaceValue1  ")
                .append("  ,replace_value2  AS replaceValue2  ")
                .append("  ,replace_value3  AS replaceValue3  ")
                .append("  ,replace_value4  AS replaceValue4  ")
                .append("  ,replace_value5  AS replaceValue5  ")
                .append("  ,replace_value6  AS replaceValue6  ")
                .append("  ,replace_value7  AS replaceValue7  ")
                .append("   FROM dh_anti_literal_element      ")
                .append("  WHERE site_code    = :siteCode     ")
                .append("    AND system_code  = :systemCode   ")
                .append("    AND id_code      = 'LZY0001'     ")
                .append("    AND control_key1 = :screenIdFrom ")
                .append("    AND control_key2 = ' '           ")
                .append("    AND control_key3 = ' '           ")
                .append("    AND control_key4 = ' '           ")
                .append("    AND control_key5 = ' '           ");
        params.put("siteCode", siteCode);
        params.put("systemCode", systemCode);
        params.put("screenIdFrom", screenIdFrom);
        List<DhAntiLiteralElementModel> list = queryForList(sql.toString(), params,
                DhAntiLiteralElementModel.class);
        if (list == null || list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

}
