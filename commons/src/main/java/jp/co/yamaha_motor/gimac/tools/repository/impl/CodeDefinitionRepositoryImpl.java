package jp.co.yamaha_motor.gimac.tools.repository.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ymsl.solid.jpa.query.JpaNativeQuerySupportRepository;

import jp.co.yamaha_motor.gimac.tools.model.ComboboxModel;
import jp.co.yamaha_motor.gimac.tools.repository.CodeDefinitionRepository;

/**
 * CodeDefinition リポジトリ 実装する
 */
@Repository
public class CodeDefinitionRepositoryImpl extends JpaNativeQuerySupportRepository implements CodeDefinitionRepository {

    /**
     * コード値とコード名称が格納されたモデルをListで取得する
     *
     * @param sysOwnerCd       sysOwnerCd
     * @param systemCode       systemCode
     * @param codeGroup        codeGroup
     * @param systemLocaleCode systemLocaleCode
     * @return {@code List<ComboboxModel>} ComboboxModelのリスト
     */
    @Override
    public List<ComboboxModel> getCodeName(String sysOwnerCd, String systemCode, String codeGroup,
            String systemLocaleCode) {
        StringBuilder sql = new StringBuilder();
        Map<String, Object> map = new HashMap<>();
        sql.append("SELECT b.code, b.code_name")
                .append(" FROM lz_code_definition a, lz_code_name b")
                .append(" WHERE a.sys_owner_cd = :sysOwnerCd")
                .append(" AND a.system_code = :systemCode")
                .append(" AND a.code_group = :codeGroup")
                .append(" AND a.available_yn = 'Y'")
                .append(" AND a.sys_owner_cd = b.sys_owner_cd")
                .append(" AND a.system_code = b.system_code")
                .append(" AND a.code_group = b.code_group")
                .append(" AND a.code = b.code")
                .append(" AND b.locale_code = :localeCode")
                .append(" ORDER BY a.display_seq");
        map.put("sysOwnerCd", sysOwnerCd);
        map.put("systemCode", systemCode);
        map.put("codeGroup", codeGroup);
        map.put("localeCode", systemLocaleCode);
        return queryForList(sql.toString(), map, ComboboxModel.class);
    }

}
