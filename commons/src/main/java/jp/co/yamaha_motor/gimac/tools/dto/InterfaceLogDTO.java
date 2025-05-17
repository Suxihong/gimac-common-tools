package jp.co.yamaha_motor.gimac.tools.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InterfaceLogDTO {

    private String systemCode;
    private String interfaceDateTime;
    private String processingProgram;
    private String interfaceSystemCode;
    private String interfaceCategory;
    private String interfaceType;
    private String sendReceiveCategory;
    private String processingId;
    private String interfaceFile;
}