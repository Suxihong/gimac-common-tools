package jp.co.yamaha_motor.gimac.tools.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CodeDefinitionDTO {
    /**
     * システムコード
     */
    String systemCode;

    /**
     * コードグループ
     */
    String codeGroup;
    
    /**
     * 編集区分
     */
    String editType;
}
