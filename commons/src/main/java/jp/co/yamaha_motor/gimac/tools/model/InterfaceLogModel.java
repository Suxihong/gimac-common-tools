package jp.co.yamaha_motor.gimac.tools.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class InterfaceLogModel {
    private String systemCode;
    private String interfaceDateTime;
    private String processingProgram;
    private String interfaceSystemCode;
    private String interfaceCategory;
    private String interfaceType;
    private String sendReceiveCategory;
    private String processingId;
    private String interfaceFile;
    private Date operationTime;
    private String operator;
}