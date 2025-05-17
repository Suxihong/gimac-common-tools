package jp.co.yamaha_motor.gimac.tools.repository;

import java.util.List;

import jp.co.yamaha_motor.gimac.tools.model.ComboboxModel;

/**
 * CodeDefinition リポジトリ インターフェース
 */
public interface CodeDefinitionRepository {
    
    /**
     * コード名マップ取得
     * @param sysOwnerCd システムオーナーコード
     * @param systemCode システムコード
     * @param codeGroup コードグループ
     * @return コード名マップ
     */
    List<ComboboxModel> getCodeName(String sysOwnerCd, String systemCode, String codeGroup, String systemLocaleCode);
}
