package jp.co.yamaha_motor.gimac.tools.service;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

import jp.co.yamaha_motor.gimac.tools.aop.annotation.OperationStatus;
import jp.co.yamaha_motor.gimac.tools.context.ThreadLocalContext;
import jp.co.yamaha_motor.gimac.tools.model.OperatingStatusModel;
import jp.co.yamaha_motor.gimac.tools.repository.OperatingStatusRepository;
import org.springframework.stereotype.Service;



@Service
public class OperatingStatusService {

    private final OperatingStatusRepository operatingStatusRepository;

    public OperatingStatusService(OperatingStatusRepository operatingStatusRepository) {
        this.operatingStatusRepository = operatingStatusRepository;
    }

    public List<OperatingStatusModel> getAllModels() {
        return operatingStatusRepository.findAll();
    }


    /**
     * 指定されたコードに基づいてモデルを検索します。
     *
     * @param code 検索するコード
     * @return コードに一致するモデルのリスト
     */
    public List<OperatingStatusModel> findModelByCode(String code) {
        return operatingStatusRepository.findAll().stream()
                .filter(model -> model.getSystemCode() != null && model.getSystemCode().contains(code))
                .toList();
    }

    /**
     * 指定されたOperatingStatusModelを使用して、データベースの状態を更新します。
     *
     * @param operatingStatusModel 更新するモデル
     * @return 更新結果のメッセージ
     */
    public String updateOperatingStatus(OperatingStatusModel operatingStatusModel) {
        return operatingStatusRepository.updateOperatingStatus(operatingStatusModel);
    }

    /**
     * 指定されたコードに基づいて、運用状態を取得します。
     *
     * @param code 検索するコード
     * @return コードに一致する運用状態
     */
    public String getOperatingStatusByCode(String code) {
        List<OperatingStatusModel> models = findModelByCode(code);
        if (Objects.isNull(models) || models.isEmpty()) {
            return "No data found for the code";
        }
        return models.get(0).getOperatingState();
    }

    /**
     * 指定されたコードに基づいて、オンライン状態を取得します。
     *
     * @param code 検索するコード
     * @return コードに一致するオンライン状態
     */
    public String getOnlineStatusByCode(String code) {
        List<OperatingStatusModel> models = findModelByCode(code);
        if (Objects.isNull(models) || models.isEmpty()) {
            return "No data found for the code";
        }
        return models.get(0).getOnlineState();
    }

    /**
     * ユーザーIDとシステムコードに基づいて利用可能なモデルを取得します。
     *
     * @param userID ユーザーID
     * @param systemCode システムコード
     * @return 利用可能なモデルのリスト
     */
    @OperationStatus()
    public List<String> getAvailableModels(String userID, String systemCode) {
        if (userID != null && systemCode == null){
            return getAvailableModelsByID(userID);
        } else if (userID != null && systemCode != null){
            return Collections.singletonList(getAvailableModelsByIDandCode(userID, systemCode));
        }
        return Collections.emptyList();
    }

    /**
     * ユーザーIDに基づいて利用可能なモデルを取得します。
     *
     * @param userID ユーザーID
     * @return 利用可能なモデルのリスト
     */
    public List<String> getAvailableModelsByID(String userID) {
        return Collections.singletonList("");
    }

    /**
     * ユーザーIDとシステムコードに基づいて利用可能なモデルを取得します。
     *
     * @param userID ユーザーID
     * @param systemCode システムコード
     * @return 利用可能なモデルのリスト
     */
    public String getAvailableModelsByIDandCode(String userID, String systemCode) {
        return " ";
    }

}
