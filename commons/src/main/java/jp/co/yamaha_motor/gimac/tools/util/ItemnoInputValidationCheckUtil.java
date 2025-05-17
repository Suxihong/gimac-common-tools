package jp.co.yamaha_motor.gimac.tools.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ItemnoInputValidationCheckUtil {

    /**
     * 数字英字,"-","@"の正規表現
     */
    private static final String AL_NUM_HYP_MAILSIGN = "[0-9A-Z\\-@]+";
    private static final Pattern AL_NUM_HYP_MAILSIGN_PATTERN = Pattern.compile(AL_NUM_HYP_MAILSIGN);

    /**
     * 文字列が英数ハイフンアスタリスクか判定します。
     *
     * @param str 対象となる文字列
     * @return 数字英字,"-","@"の場合は true、そうでない場合は false
     */
    public static boolean isAlphNumHyphenAsterisk(String str) {
        return str != null && AL_NUM_HYP_MAILSIGN_PATTERN.matcher(str).matches();
    }

    /**
     * 正規表現チェックを行います。
     *
     * @param str     対象となる文字列
     * @param ptn     正規表現パターン
     * @return 検証結果 true - 妥当である場合, false - そうでない場合
     */
    public static boolean isMask(String str, String ptn) {

        Pattern pattern = Pattern.compile(ptn);
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }
}
