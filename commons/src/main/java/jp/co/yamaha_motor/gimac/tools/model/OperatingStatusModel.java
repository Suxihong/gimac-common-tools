package jp.co.yamaha_motor.gimac.tools.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class OperatingStatusModel {
    private String contactUsName;
    private String contactUsNumber;
    private String operatingState;
    private String onlineState;
    private String systemCode;
    private String systemName;
    private String remark;
}
