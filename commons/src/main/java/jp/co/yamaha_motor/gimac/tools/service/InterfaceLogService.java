package jp.co.yamaha_motor.gimac.tools.service;

import org.springframework.stereotype.Service;

import com.ymsl.solid.web.usercontext.UserDetailsAccessor;

import jp.co.yamaha_motor.gimac.tools.dto.InterfaceLogDTO;
import jp.co.yamaha_motor.gimac.tools.model.InterfaceLogModel;
import jp.co.yamaha_motor.gimac.tools.repository.InterfaceLogRepository;

@Service
public class InterfaceLogService {

    private final InterfaceLogRepository interfaceLogRepository;

    public InterfaceLogService(InterfaceLogRepository interfaceLogRepository) {
        this.interfaceLogRepository = interfaceLogRepository;
    }
    // This service can be used to log interface calls
    public void logInterfaceCall(InterfaceLogDTO interfaceLogDTO) {
        InterfaceLogModel model = new InterfaceLogModel();
        model.setSystemCode(interfaceLogDTO.getSystemCode());
        model.setInterfaceDateTime(interfaceLogDTO.getInterfaceDateTime());
        model.setProcessingProgram(interfaceLogDTO.getProcessingProgram());
        model.setInterfaceSystemCode(interfaceLogDTO.getInterfaceSystemCode());
        model.setInterfaceCategory(interfaceLogDTO.getInterfaceCategory());
        model.setInterfaceType(interfaceLogDTO.getInterfaceType());
        model.setSendReceiveCategory(interfaceLogDTO.getSendReceiveCategory());
        model.setProcessingId(interfaceLogDTO.getProcessingId());
        model.setInterfaceFile(interfaceLogDTO.getInterfaceFile());
        String currentUser = UserDetailsAccessor.DEFAULT.get() != null ? UserDetailsAccessor.DEFAULT.get().getRealUsername() : "";
        model.setOperator(currentUser);

        interfaceLogRepository.InsertLog(model);
    }
}
