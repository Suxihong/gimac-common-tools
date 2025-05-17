package jp.co.yamaha_motor.gimac.tools.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ymsl.solid.base.util.StringUtils;
import com.ymsl.solid.web.usercontext.UserDetailsAccessor;

import jp.co.yamaha_motor.gimac.tools.model.ComboboxModel;
import jp.co.yamaha_motor.gimac.tools.repository.CodeDefinitionRepository;

@Service
public class CodeDefinitionLogicService {

    @Autowired
    private CodeDefinitionRepository codeDefinitionRepository;

    /**
     * コード値とコード名称が格納されたモデルをMapで取得する
     * 
     * @param systemCode systemCode
     * @param codeGroup  codeGroup
     * @param editType   1:名称のみ/2:コード+名称/3:コードのみ
     * @return Map コード値とコード名称のMap
     */
    public Map<String, String> getCodeNameMap(String systemCode, String codeGroup, String editType) {
        List<ComboboxModel> comboboxModelList = getCodeNameList(systemCode, codeGroup, editType);

        Map<String, String> codeNameMap = new HashMap<String, String>();

        for (ComboboxModel comboboxModel : comboboxModelList) {
            codeNameMap.put(comboboxModel.getCode(), comboboxModel.getCodeName());
        }

        return codeNameMap;
    }

    /**
     * コード値とコード名称が格納されたモデルをListで取得する
     * 
     * @param systemCode systemCode
     * @param codeGroup  codeGroup
     * @param editType   1:名称のみ/2:コード+名称/3:コードのみ
     * @return List ComboboxModelのList
     */
    public List<ComboboxModel> getCodeNameList(String systemCode, String codeGroup, String editType) {
        List<ComboboxModel> ComboboxModelList = selectComboboxData(systemCode, codeGroup,
                editType, "1");

        return ComboboxModelList;
    }

    /**
     * コード値とコード名称が格納されたモデルをListで取得する
     * 
     * @param systemCode systemCode
     * @param codeGroup  codeGroup
     * @param editType   1:名称のみ/2:コード+名称/3:コードのみ
     * @param addType    1:追加なし/2:ブランク追加/3:ALL追加/4:ブランクとALL追加/5:空文字("")追加/6:空文字("")とALL追加
     * @param trimType   1:trim無し/2:trim有り (省略時は1:trim無しと同じ動き)
     * @return List ComboboxModelのList
     */
    public List<ComboboxModel> selectComboboxData(String systemCode, String codeGroup, String editType, String addType,
            String trimType) {

        String delimiter = ":";
        // TODO: siteCodeとlocaleCodeの取得方法が変更される
        String siteCode = UserDetailsAccessor.DEFAULT.get();
        String localeCode = UserDetailsAccessor.DEFAULT.get();
        

        List<ComboboxModel> resultComboBoxList = new ArrayList<ComboboxModel>();

        // 項目追加
        String _SPACE = " ";
        String _ALL = "ALL";
        switch (addType) {
            case "1":
                break;
            case "2":
                addComboboxModel(resultComboBoxList, _SPACE, _SPACE);
                break;
            case "3":
                addComboboxModel(resultComboBoxList, _ALL, _ALL);
                break;
            case "4":
                addComboboxModel(resultComboBoxList, _SPACE, _SPACE);
                addComboboxModel(resultComboBoxList, _ALL, _ALL);
                break;
            default:
                // 追加なしの場合は何もしない
                break;
        }

        // ------------------------------------------------------
        // DBよりデータを取得する
        // ------------------------------------------------------
        List<ComboboxModel> comboboxlist = codeDefinitionRepository.getCodeName(siteCode, systemCode, codeGroup,
                localeCode);

        // DBから取得した値をコンボボックスに使いやすい形式に整形して、Listに詰めなおす
        for (ComboboxModel dbModel : comboboxlist) {
            ComboboxModel combBox = new ComboboxModel();

            String code = dbModel.getCode();
            String codeName = dbModel.getCodeName();

            // Trim()指定の場合
            if ("2".equals(trimType)) {
                code = StringUtils.trimRight(code);
                codeName = StringUtils.trimRight(codeName);
            }

            combBox.setCode(code); // Code格納

            // 引数のオプションに応じた名称を格納
            switch (editType) {
                case "1":
                    combBox.setCodeName(codeName);
                    break;
                case "2":
                    combBox.setCodeName(code + delimiter + codeName);
                    break;
                case "3":
                    combBox.setCodeName(code);
                    break;
                default:
                    // デフォルトでは何もしない
                    break;
            }
            resultComboBoxList.add(combBox);
        }

        return resultComboBoxList;
    }

    /**
     * ComboboxModelを作成してリストに追加するヘルパーメソッド
     * 
     * @param list 追加先のリスト
     * @param code モデルのコード値
     * @param name モデルの名称
     */
    private void addComboboxModel(List<ComboboxModel> list, String code, String name) {
        ComboboxModel model = new ComboboxModel();
        model.setCode(code);
        model.setCodeName(name);
        list.add(model);
    }

    /**
     * コード値とコード名称が格納されたモデルをListで取得する
     * 
     * @param systemCode 取得するコード定義のシステムコード
     * @param codeGroup  取得するコード定義のコードグループ
     * @param editType   1:名称のみ/2:コード+名称/3:コードのみ
     * @param addType    1:追加なし/2:ブランク追加/3:ALL追加/4:ブランクとALL追加
     * @return List ComboboxModelのList
     */
    public List<ComboboxModel> selectComboboxData(
            String systemCode,
            String codeGroup,
            String editType,
            String addType) {

        List<ComboboxModel> comboBoxList = selectComboboxData(systemCode,
                codeGroup,
                editType,
                addType,
                "1");
        return comboBoxList;
    }

    /**
     * dh_codeをDBより取得し、Map形式で返却します
     * 
     * @param systemCode 取得するコード定義のシステムコード
     * @param codeGroup  取得するコード定義のコードグループ
     * @param editType   1:名称のみ/2:コード+名称/3:コードのみ
     * @param addType    1:追加なし/2:ブランク追加/3:ALL追加/4:ブランクとALL追加
     * @param trimType   1:trim無し/2:trim有り (省略時は1:trim無しと同じ動き)
     * @return Map コード値とコード名称のMap
     */
    public Map<String, String> selectComboboxDataEx(String systemCode,
            String codeGroup,
            String editType,
            String addType,
            String trimType) {

        List<ComboboxModel> resultList = selectComboboxData(systemCode,
                codeGroup,
                editType,
                addType,
                trimType);

        Map<String, String> resultMap = convList2Map(resultList);

        return resultMap;
    }

    /**
     * dh_codeをDBより取得し、Map形式で返却します
     * 
     * @param systemCode 取得するコード定義のシステムコード
     * @param codeGroup  取得するコード定義のコードグループ
     * @param editType   1:名称のみ/2:コード+名称/3:コードのみ
     * @param addType    1:追加なし/2:ブランク追加/3:ALL追加/4:ブランクとALL追加
     * @return コード値とコード名称のMap
     */
    public Map<String, String> selectComboboxDataEx(String systemCode,
            String codeGroup,
            String editType,
            String addType) {

        return selectComboboxDataEx(systemCode,
                codeGroup,
                editType,
                addType,
                "1");
    }

    public Map<String, String> convList2Map(List<ComboboxModel> list) {
        Map<String, String> map = new LinkedHashMap<>();

        for (ComboboxModel model : list) {
            map.put(model.getCode(), model.getCodeName());
        }

        return map;
    }

}
