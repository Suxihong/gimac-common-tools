package jp.co.yamaha_motor.gimac.tools.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jp.co.yamaha_motor.gimac.tools.dto.CodeDefinitionDTO;
import jp.co.yamaha_motor.gimac.tools.model.ComboboxModel;
import jp.co.yamaha_motor.gimac.tools.service.CodeDefinitionLogicService;

/**
 * CodeDefinition コントローラ クラス
 */
@RestController
@RequestMapping("/tools/code/definition")
public class CodeDefinitionController {

    @Autowired
    private CodeDefinitionLogicService codeDefinitionLogicsService;

    @PostMapping("/getCodeNameMap")
    public Map<String, String> getCodeNameMap(@RequestBody CodeDefinitionDTO codeDefinitionDTO) {
        return codeDefinitionLogicsService.getCodeNameMap(codeDefinitionDTO.getSystemCode(), codeDefinitionDTO.getCodeGroup(), codeDefinitionDTO.getEditType());
    }

    @PostMapping("/getCodeNameList")
    public List<ComboboxModel> getCodeNameList(@RequestBody CodeDefinitionDTO codeDefinitionDTO) {
        return codeDefinitionLogicsService.getCodeNameList(codeDefinitionDTO.getSystemCode(), codeDefinitionDTO.getCodeGroup(), codeDefinitionDTO.getEditType());
    }
}
