package jp.co.yamaha_motor.gimac.tools.controller;

import jp.co.yamaha_motor.gimac.tools.model.OperatingStatusModel;
import jp.co.yamaha_motor.gimac.tools.service.OperatingStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@RestController
@RequestMapping("/tools/operating/status")
public class OperatingStatusController {

    private final OperatingStatusService operatingStatusService;

    @Autowired
    public OperatingStatusController(OperatingStatusService operatingStatusService) {
        this.operatingStatusService = operatingStatusService;
    }

    /**
     * 指定されたOperatingStatusModelを使用して、データベースの状態を更新します。
     *
     * @param operatingStatusModel 更新するモデル
     * @return 更新結果のメッセージ
     */
    @PostMapping("/updateOperatingStatus")
    public String updateOperatingStatus(@RequestBody OperatingStatusModel operatingStatusModel) {
        return operatingStatusService.updateOperatingStatus(operatingStatusModel);
    }

    /**
     * データベースからすべてのOperatingStatusModelを取得します。
     *
     * @return すべてのOperatingStatusModelのリスト
     */
    @PostMapping("/getAllModels")
    public List<OperatingStatusModel> getAllModels() {
        return operatingStatusService.getAllModels();
    }

    /**
     * 指定されたコードに基づいてモデルを検索します。
     *
     * @param code 検索するコード
     * @return コードに一致するモデルのリスト
     */
    @PostMapping("/findModelByCode")
    public List<OperatingStatusModel> findModelByCode(@RequestParam String code) {
        return  operatingStatusService.findModelByCode(code);
    }

    /**
     * 指定されたコードに基づいて、運用状態を取得します。
     *
     * @param code 検索するコード
     * @return コードに一致する運用状態
     */
    @PostMapping("/validateOperationState")
    public String validateOperationState(@RequestParam String code) {
        return operatingStatusService.getOperatingStatusByCode(code);
    }

    /**
     * 指定されたコードに基づいて、オンライン状態を取得します。
     *
     * @param code 検索するコード
     * @return コードに一致するオンライン状態
     */
    @PostMapping("/validateOnlineState")
    public String validateOnlineState(@RequestParam String code) {
        return operatingStatusService.getOnlineStatusByCode(code);
    }

    /**
     * 指定されたユーザーIDとシステムコードに基づいて、利用可能なモデルを取得します。
     *
     * @param userID ユーザーID
     * @param systemCode システムコード
     * @return 利用可能なモデルのリスト
     */
    @PostMapping("/getAvailableModels")
    public List<String> getAvailableModels(@RequestParam(required = false) String userID, @RequestParam(required = false) String systemCode) {
        return operatingStatusService.getAvailableModels(userID, systemCode);
    }
}
