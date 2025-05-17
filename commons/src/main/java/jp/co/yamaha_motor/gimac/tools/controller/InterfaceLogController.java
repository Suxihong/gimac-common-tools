package jp.co.yamaha_motor.gimac.tools.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jp.co.yamaha_motor.gimac.tools.dto.InterfaceLogDTO;
import jp.co.yamaha_motor.gimac.tools.service.InterfaceLogService;

@RestController
@RequestMapping("/tools/interface/logs")
public class InterfaceLogController {

    private final InterfaceLogService interfaceLogService;

    @Autowired
    public InterfaceLogController(InterfaceLogService interfaceLogService) {
        this.interfaceLogService = interfaceLogService;
    }

    @PostMapping("/output")
    public String logInterfaceOutput(@RequestBody InterfaceLogDTO interfaceLogDTO) {
        interfaceLogService.logInterfaceCall(interfaceLogDTO);
        return "Interface logged successfully";
    }
}
