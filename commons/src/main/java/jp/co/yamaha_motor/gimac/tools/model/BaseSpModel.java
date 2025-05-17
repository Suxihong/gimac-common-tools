package jp.co.yamaha_motor.gimac.tools.model;

import lombok.Data;

/**
 * PL/pgSQLの戻り値を格納するModelクラスのスーパークラス
 * 規約で決められた処理結果が格納される固定戻り値を有する
 * 
 */
@Data
public class BaseSpModel {

	/** SPステータス */
	private long rnStatus;

	/** SQLコード */
	private String rsSqlCode;

	/** エラーコード */
	private String rsErrCode;

	/** エラーメッセージ */
	private String rsErrMsg;

}

