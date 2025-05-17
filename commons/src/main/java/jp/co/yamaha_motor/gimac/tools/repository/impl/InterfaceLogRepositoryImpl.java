package jp.co.yamaha_motor.gimac.tools.repository.impl;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ymsl.solid.jpa.query.JpaNativeQuerySupportRepository;
import com.ymsl.solid.web.usercontext.UserDetailsAccessor;

import jp.co.yamaha_motor.gimac.tools.model.InterfaceLogModel;
import jp.co.yamaha_motor.gimac.tools.repository.InterfaceLogRepository;

@Repository
public class InterfaceLogRepositoryImpl extends JpaNativeQuerySupportRepository implements InterfaceLogRepository {

    @Override
    public String InsertLog(InterfaceLogModel logModel) {
        StringBuilder sql = new StringBuilder();
        Map<String, Object> map = new HashMap<>();
        sql.append("INSERT INTO interface_logs (")
           .append("system_code, ")
           .append("interface_date_time, ")
           .append("processing_program, ")
           .append("interface_system_code, ")
           .append("interface_category, ")
           .append("interface_type, ")
           .append("send_receive_category, ")
           .append("processing_id, ")
           .append("interface_file, ")
           .append("operation_time, ")
           .append("operator) ")
           .append("VALUES (:systemCode, :interfaceDateTime, :processingProgram, :interfaceSystemCode, :interfaceCategory, :interfaceType, :sendReceiveCategory, :processingId, :interfaceFile, :operation_time, :operator)");
        map.put("systemCode", logModel.getSystemCode());
        map.put("interfaceDateTime", logModel.getInterfaceDateTime());
        map.put("processingProgram", logModel.getProcessingProgram());
        map.put("interfaceSystemCode", logModel.getInterfaceSystemCode());
        map.put("interfaceCategory", logModel.getInterfaceCategory());
        map.put("interfaceType", logModel.getInterfaceType());
        map.put("sendReceiveCategory", logModel.getSendReceiveCategory());
        map.put("processingId", logModel.getProcessingId());
        map.put("interfaceFile", logModel.getInterfaceFile());
        Timestamp currentTime = new Timestamp(System.currentTimeMillis());
        map.put("operation_time", currentTime);
        String operator = UserDetailsAccessor.DEFAULT.get() != null ? UserDetailsAccessor.DEFAULT.get().getRealUsername() : "";
        map.put("operator", operator);

        createSqlQuery(sql.toString(), map).executeUpdate();
        return "success";
    }
}
