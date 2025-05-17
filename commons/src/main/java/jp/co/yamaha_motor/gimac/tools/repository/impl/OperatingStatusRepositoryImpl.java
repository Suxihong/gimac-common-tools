package jp.co.yamaha_motor.gimac.tools.repository.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ymsl.solid.jpa.query.JpaNativeQuerySupportRepository;

import jp.co.yamaha_motor.gimac.tools.model.OperatingStatusModel;
import jp.co.yamaha_motor.gimac.tools.repository.OperatingStatusRepository;

@Repository
public class OperatingStatusRepositoryImpl extends JpaNativeQuerySupportRepository implements OperatingStatusRepository {

    @Override
    public List<OperatingStatusModel> findAll() {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT o.system_code")
           .append(", o.operating_state")
           .append(", o.remark")
           .append(", o.contact_us_name")
           .append(", o.contact_us_number")
           .append(", o.online_state")
           .append(", s.system_name")
           .append(" FROM lz_ope_state o, lz_system_code s")
           .append(" WHERE o.system_code = s.system_code");
        return queryForList(sql.toString(), OperatingStatusModel.class);
    }

    @Override
    public String updateOperatingStatus(OperatingStatusModel operatingStatusModel) {
        StringBuilder sql = new StringBuilder();
        Map<String, Object> map = new HashMap<>();
        sql.append("UPDATE lz_ope_state")
           .append(" SET operating_state = :operatingState")
           .append(", online_state = :onlineState")
           .append(" WHERE system_code = :systemCode");
        map.put("operatingState", operatingStatusModel.getOperatingState());
        map.put("onlineState", operatingStatusModel.getOnlineState());
        map.put("systemCode", operatingStatusModel.getSystemCode());
        createSqlQuery(sql.toString(),map).executeUpdate();
        return "success";
    }
}
