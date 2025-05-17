package jp.co.yamaha_motor.gimac.tools.util;

import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * 文字列用ユーティリティクラス
 */
public class StringUtil {

    /**
     * 半角カナ
     */
    private static final String JIS_HALF_KANA = "｡｢｣､･ｦｧｨｩｪｫｬｭｮｯｰ" +
            "ｱｲｳｴｵｶｷｸｹｺｻｼｽｾｿ" +
            "ﾀﾁﾂﾃﾄﾅﾆﾇﾈﾉﾊﾋﾌﾍﾎ" +
            "ﾏﾐﾑﾒﾓﾔﾕﾖﾗﾘﾙﾚﾛﾜﾝﾞﾟ";

    /**
     * 濁点半カナ
     */
    private static final String HALF_SONANT_KANA = "ｶｷｸｹｺｻｼｽｾｿﾀﾁﾂﾃﾄﾊﾋﾌﾍﾎﾊﾋﾌﾍﾎｳ";

    /**
     * 行区切り文字
     */
    public static final String LINE_SEPARATOR = getSystemProperty("line.separator");

    /**
     * 空文字の配列
     */
    public static final String[] EMPTY_STRING_ARRAY = new String[0];

    /**
     * クォーテーション
     */
    public static final String QUOT = "\"";

    /**
     * デリミタ
     */
    private static final String DERIM = "-";

    /**
     * 標準デリミタ
     */
    public static final String DEFAULT_DELIMITER_ID = "1";

    /**
     * デリミタパターンの一覧
     */
    public static final String[][] DELIMITER_LIST = {
            { "1", new String(new char[] { 0x09 }), "Tab" },
            { "2", "|", "Pipe" }
    };

    /**
     * 改行コードパターンの一覧
     */
    public static final String[][] CRLF_DELIMITER_LIST = {
            { "1", "\r\n", "CRLF" },
            { "2", "\r", "CR" },
            { "3", "\n", "LF" }
    };

    /**
     * ゼロ（文字）
     *
     * @since 1.0.2
     */
    public static final char CHAR_ZERO = '0';
    public static final String STRING_BLANK = "";
    public static final String STRING_ZERO = "0";

    /**
     * スペース
     *
     * @since 1.0.3
     */
    public static final String STRING_SPACE = " ";

    /**
     * コンストラクタ
     */
    protected StringUtil() {
    }

    /**
     * 文字列に変換します。<br>
     *
     * @param obj 変換するオブジェクト
     * @return 変換された文字列
     */
    public static String toString(Object obj) {

        if (obj == null) {
            return null;
        }

        return obj.toString();
    }

    /**
     * nullの場合は空文字を返します。<br>
     * null以外の場合は渡された文字列を返します。<br>
     *
     * @param str 対象となる文字列
     * @return 編集された文字列
     */
    public static String toSpace(String str) {

        if (str == null) {
            return "";
        }

        return str;
    }

    /**
     * 文字列をトリムして返す
     * nullの場合は空文字を返す
     *
     * @param str 対象となる文字列
     * @return 編集された文字列
     */
    public static String trimString(String str) {
        return toSpace(str).trim();
    }

    /**
     * 文字列に変換する。<br>
     *
     * @param num 変換する数値
     * @return 変換された文字列
     */
    public static String toString(int num) {
        return Integer.valueOf(num).toString();
    }

    /**
     * 文字列に変換する。<br>
     *
     * @param b 変換する真偽値
     * @return 変換された文字列
     */
    public static String toString(Boolean b) {

        if (b == null) {
            return null;
        }
        return b.toString();
    }

    /**
     * 文字列に変換する。<br>
     *
     * @param num 変換する数値
     * @return 変換された文字列
     */
    public static String toString(Integer num) {

        if (num == null) {
            return null;
        }
        return num.toString();
    }

    /**
     * 文字列に変換する。<br>
     *
     * @param dec 変換する数値
     * @return 変換された文字列
     */
    public static String toString(BigDecimal dec) {

        if (dec == null) {
            return null;
        }
        return dec.toString();
    }

    /**
     * デリミタを取得します。
     *
     * @param delimiterId デリミタ区分
     * @return デリミタ
     */
    public static String getDelimiterValue(String delimiterId) {

        String value = null;
        for (int i = 0; i < DELIMITER_LIST.length; i++) {
            if (DELIMITER_LIST[i][0].equals(delimiterId)) {
                value = DELIMITER_LIST[i][1];
                break;
            }
        }

        if (value == null) {
            throw new IllegalArgumentException("The value of an argument is inaccurate.");
        }

        return value;
    }

    /**
     * デリミタを取得します。
     *
     * @param delimiterId デリミタ区分
     * @return デリミタ
     */
    public static char getDelimiterValueChar(String delimiterId) {
        return getDelimiterValue(delimiterId).charAt(0);
    }

    /**
     * デリミタ名称を取得します。
     *
     * @param delimiterId デリミタ区分
     * @return デリミタ名称
     */
    public static String getDelimiterName(String delimiterId) {

        String value = null;
        for (int i = 0; i < DELIMITER_LIST.length; i++) {
            if (DELIMITER_LIST[i][0].equals(delimiterId)) {
                value = DELIMITER_LIST[i][2];
                break;
            }
        }

        if (value == null) {
            throw new IllegalArgumentException("The value of an argument is inaccurate.");
        }

        return value;
    }

    /**
     * 改行コードを取得します。
     *
     * @param crlfDelimiterId 改行コード区分
     * @return 改行コード
     */
    public static String getCrlfDelimiterValue(String crlfDelimiterId) {

        String value = null;
        for (int i = 0; i < CRLF_DELIMITER_LIST.length; i++) {
            if (CRLF_DELIMITER_LIST[i][0].equals(crlfDelimiterId)) {
                value = CRLF_DELIMITER_LIST[i][1];
                break;
            }
        }

        if (value == null) {
            throw new IllegalArgumentException("The value of an argument is inaccurate.");
        }

        return value;
    }

    /**
     * 改行コードを取得します。
     *
     * @param crlfDelimiterId 改行コード区分
     * @return 改行コード
     */
    public static char getCrlfDelimiterValueChar(String crlfDelimiterId) {
        return getCrlfDelimiterValue(crlfDelimiterId).charAt(0);
    }

    /**
     * 改行コード名称を取得します。
     *
     * @param crlfDelimiterId 改行コード区分
     * @return 改行コード名称
     */
    public static String getCrlfDelimiterName(String crlfDelimiterId) {

        String value = null;
        for (int i = 0; i < CRLF_DELIMITER_LIST.length; i++) {
            if (CRLF_DELIMITER_LIST[i][0].equals(crlfDelimiterId)) {
                value = CRLF_DELIMITER_LIST[i][2];
                break;
            }
        }

        if (value == null) {
            throw new IllegalArgumentException("The value of an argument is inaccurate.");
        }

        return value;
    }

    /**
     * 改行コードを取得します。
     *
     * @param value データ
     * @return 改行コード
     */
    public static String getCrlfDelimiterCode(String value) {

        String delim = "";
        for (int i = 0; i < CRLF_DELIMITER_LIST.length; i++) {
            if (value.indexOf(CRLF_DELIMITER_LIST[i][1]) >= 0) {
                delim = CRLF_DELIMITER_LIST[i][1];
                break;
            }
        }

        return delim;
    }

    /**
     * 取得した文字列に' ”” '(ダブルコート＋ダブルコート)を
     * ”（ダブルコート）に変換する。
     *
     * @param str 対象となる文字列
     * @return String 編集後の文字列
     */
    public static String minusDoubleQuotation(String str) {

        String r_str = null;

        // StringがNullの場合は""を返す
        if (str == null) {
            r_str = new String("");
            // それ以外の場合は、データに””があった場合は
            // ”に変換する。
        } else {

            // データに””があった場合は、”に変換する
            StringBuffer sb = new StringBuffer(str);
            for (int i = 0; i < sb.length() - 1; i++) {
                if (sb.charAt(i) == '"' &&
                        sb.charAt(i + 1) == '"') {
                    sb.replace(i, i + 2, QUOT);
                }
            }
            // 前後に”を付加する。
            r_str = sb.toString();
        }
        return r_str;
    }

    /**
     * シリアル番号を編集する。
     *
     * @param str 対象となる文字列
     * @return String 編集した文字列
     */
    public static String toSerialNumber(String str) {

        if (str == null || str.length() == 0) {
            return str;
        }

        // ７桁未満は０埋めする
        return padCharLeft(str, 7, '0');
    }

    /**
     * 文字列を指定された文字で左埋めします。
     *
     * @param str     対象となる文字列
     * @param len     対象となる文字数
     * @param padChar 埋める文字列
     * @return 変換した文字列
     */
    public static String padCharLeft(String str, int len, char padChar) {

        if (str == null || str.length() == 0) {
            return str;
        }

        if (str.length() >= len) {
            // 対象となる文字数以上はそのまま返却する
            return str;
        }

        // 左埋め
        StringBuffer sb = new StringBuffer(str);
        int max = len - str.length();

        for (int i = 0; i < max; i++) {
            sb.insert(0, padChar);
        }

        return sb.toString();
    }

    /**
     * 文字列を指定された文字で右埋めします。
     *
     * @param str     対象となる文字列
     * @param len     対象となる文字数
     * @param padChar 埋める文字列
     * @return 変換した文字列
     */
    public static String padCharRight(String str, int len, char padChar) {

        if (str == null || str.length() == 0) {
            return str;
        }

        if (str.length() >= len) {
            // 対象となる文字数以上はそのまま返却する
            return str;
        }

        // 右埋め
        StringBuffer sb = new StringBuffer(str);
        int max = len - str.length();

        for (int i = 0; i < max; i++) {
            str = padChar + str;
            sb.append(padChar);
        }

        return sb.toString();
    }

    /**
     * 文字列を0で左埋めします。
     *
     * @param str 対象となる文字列
     * @param len 対象となる文字数
     * @return 変換した文字列
     */
    public static String padZeroLeft(String str, int len) {
        return padCharLeft(str, len, '0');
    }

    /**
     * 文字列から左にある指定された文字を取り除きます。
     *
     * @param str    対象となる文字列
     * @param rmChar 取り除く文字列
     * @return 変換した文字列
     */
    public static String rmCharLeft(String str, char rmChar) {

        if (str == null || str.length() == 0) {
            return str;
        }

        int idx = 0;

        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);

            idx = i;

            if (c != rmChar) {
                break;
            }
        }

        return str.substring(idx, str.length());
    }

    /**
     * 文字列から右にある指定された文字を取り除きます。
     *
     * @param str    対象となる文字列
     * @param rmChar 取り除く文字列
     * @return 変換した文字列
     */
    public static String rmCharRight(String str, char rmChar) {

        if (str == null || str.length() == 0) {
            return str;
        }

        int idx = str.length();

        for (int i = str.length() - 1; i >= 0; i--) {
            char c = str.charAt(i);

            if (c != rmChar) {
                break;
            }

            idx = i;
        }

        return str.substring(0, idx);
    }

    /**
     * 文字列から左にある0を取り除きます。
     *
     * @param str 対象となる文字列
     * @return 変換した文字列
     */
    public static String rmZeroLeft(String str) {
        return rmCharLeft(str, '0');
    }

    /**
     * 文字列から右にある0を取り除きます。
     *
     * @param str 対象となる文字列
     * @return 変換した文字列
     */
    public static String rmZeroRight(String str) {
        return rmCharRight(str, '0');
    }

    /**
     * 文字列から左にあるスペースを取り除きます。
     *
     * @param str 対象となる文字列
     * @return 変換した文字列
     */
    public static String trimLeft(String str) {
        return rmCharLeft(str, ' ');
    }

    /**
     * 文字列から右にあるスペースを取り除きます。
     *
     * @param str 対象となる文字列
     * @return 変換した文字列
     */
    public static String trimRight(String str) {
        return rmCharRight(str, ' ');
    }

    /**
     * 指定数の指定文字を返します。
     *
     * @param c 指定文字
     * @param n 指定文字の数
     * @return 指定数の指定文字
     */
    public static String getChar(char c, int n) {

        StringBuffer sb = new StringBuffer();

        for (int i = 0; i < n; i++) {
            sb.append(c);
        }

        return sb.toString();
    }

    /**
     * 範囲指定の大小チェック
     *
     * @param start 開始文字列
     * @param end   終了文字列
     * @return boolean true :正しい, false:正しくない
     */
    public static boolean isLargeSmall(String start, String end) {

        // 開始未入力エラー
        if (start == null || start.length() == 0) {
            return false;
        }

        // 終了未入力エラー
        if (end == null || end.length() == 0) {
            return false;
        }

        // 大小エラー
        if (start.compareTo(end) > 0) {
            return false;
        }

        return true;
    }

    /**
     * 文字列を規定のバイトに切り揃えます。
     * マルチバイト文字が途中で切れる場合はその文字を含みません。
     *
     * @param str 文字列
     * @return 編集後の文字列
     */
    public static String cutsByLenB(String str, int maxLenB) {

        if (str == null) {
            return null;
        }

        StringBuffer sb = new StringBuffer();

        int totalLenB = 0;
        for (int i = 0; i < str.length() && totalLenB <= maxLenB; i++) {
            char c = str.charAt(i);
            int lenB = new String(new char[] { c }).getBytes().length;
            if (totalLenB + lenB > maxLenB) {
                break;
            }
            sb.append(c);
            totalLenB += lenB;
        }

        return sb.toString();
    }

    /**
     * 文字列を区切り文字で連結します。<br>
     *
     * @param strs       文字列
     * @param derim      デリミタ
     * @param limit      連結最大値
     * @param derimLimit 連結最大値のデリミタ
     * @return 区切り文字で連結をした文字列
     */
    public static String joinString(String[] strs, String derim, int limit, String derimLimit) {

        if (strs == null || strs.length == 0) {
            return null;
        }

        if (derim == null || derim.length() == 0) {
            return null;
        }

        if (derimLimit == null || derimLimit.length() == 0) {
            return null;
        }

        StringBuffer sb = new StringBuffer();

        for (int i = 0; i < strs.length; i++) {
            if (i % limit != 0) {
                sb.append((i != 0 ? derim : "") + strs[i]);
            } else {
                sb.append((i != 0 ? derimLimit : "") + strs[i]);
            }
        }

        return sb.toString();
    }

    /**
     * 文字列を最大文字数で分割します。
     *
     * @param src   文字列
     * @param delim 区切り文字
     * @return 文字列配列
     */
    public static String[] splitDgrd(String src, String delim) {
        ArrayList list = new ArrayList();

        int start = 0;
        int end = 0;

        while (start < src.length() && (end = src.indexOf(delim, start)) != -1) {
            list.add(src.substring(start, end));
            start = end + delim.length();
        }

        if (start < src.length()) {
            list.add(src.substring(start));
        }

        if (list.size() == 0) {
            return null;
        }

        int len = list.size();
        String[] val = new String[len];

        for (int i = 0; i < len; i++) {
            val[i] = (String) list.get(i);
        }
        return val;
    }

    /**
     * 文字列を最大文字数で分割します。
     *
     * @param str 文字列
     * @param max 最大数
     * @return 文字列配列
     */
    public static String[] splitMaxLength(String str, int max) {

        ArrayList list = new ArrayList();

        if (str == null || str.length() <= max) {
            list.add(str);
        } else {
            double dlen = str.length();
            double dmax = max;
            double cnt = Math.ceil(dlen / dmax);
            for (int i = 0; i < cnt; i++) {
                if (i + 1 == cnt) {
                    list.add(str.substring(max * i));
                } else {
                    list.add(str.substring(max * i, max * (i + 1)));
                }
            }
        }

        int len = list.size();
        String[] val = new String[len];

        for (int i = 0; i < len; i++) {
            val[i] = (String) list.get(i);
        }
        return val;

    }

    /**
     * 対象文字列の先頭に指定の文字列を付与し、返します。
     *
     * @param str   対象文字列
     * @param grant 指定文字列
     * @param n     指定文字列の数
     * @return 指定文字列が付与された対象文字列
     */
    public static String addHeadChar(String str, String grant, int n) {

        if (str == null || grant == null) {
            return str;
        }

        StringBuffer sb = new StringBuffer();

        for (int i = 0; i < n; i++) {
            sb.append(grant);
        }

        return sb.toString() + str;

    }

    /**
     * 固定長の文字数の指定文字列を返します。
     *
     * @param grant 指定文字列
     * @param n     固定長の文字数
     * @return 固定長の文字数分の指定文字列
     */
    public static String replaceFixedLength(String grant, int n) {

        StringBuffer sb = new StringBuffer();

        for (int i = 0; i < n; i++) {
            sb.append(grant);
        }

        return sb.toString();
    }

    /**
     * 対象文字列が固定長の文字数に足りない場合、対象文字列の後に指定の文字列を付与し、返します。
     *
     * @param target 対象文字列
     * @param grant  指定文字列
     * @param n      固定長の文字数
     * @return 指定文字列が付与された対象文字列
     */
    public static String replaceFixedLengthLeft(String target, String grant, int n) {

        int targetLen = toSpace(target).length();

        if (target != null && target.length() == n) {
            return target;
        }

        if (target != null && target.length() > n) {
            return target.substring(0, n);
        }

        StringBuffer sb = new StringBuffer();

        for (int i = 0; i < (n - targetLen); i++) {
            sb.append(grant);
        }

        return toSpace(target) + sb.toString();
    }

    /**
     * 対象文字列が固定長の文字数に足りない場合、対象文字列の前に指定の文字列を付与し、返します。
     *
     * @param target 対象文字列
     * @param grant  指定文字列
     * @param n      固定長の文字数
     * @return 指定文字列が付与された対象文字列
     */
    public static String replaceFixedLengthRight(String target, String grant, int n) {

        int targetLen = toSpace(target).length();

        if (target != null && target.length() == n) {
            return target;
        }

        if (target != null && target.length() > n) {
            return target.substring((n - targetLen));
        }

        StringBuffer sb = new StringBuffer();

        for (int i = 0; i < (n - targetLen); i++) {
            sb.append(grant);
        }

        return sb.toString() + toSpace(target);
    }

    /**
     * 文字列に含まれるダブルクォーテーションをキーワードに置換する。<br>
     *
     * @param str 置換する文字列
     * @return 置換後の文字列
     */
    public static String toReferenceDoubleQuotation(String str) {

        if (str == null) {
            return null;
        }

        StringBuffer sb = new StringBuffer(str);

        for (int i = 0; i < sb.length(); i++) {
            char c = sb.charAt(i);
            switch (c) {
                case '"':
                    sb.replace(i, i + 1, "\\\"");
                    i += 1;
                    break;
                default:
                    break;
            }
        }
        return sb.toString();
    }

    /**
     * 全角半角を変換するメソッドです
     *
     * @param cvType 変換タイプ
     *               (1:半角→全角 2:全角→半角)
     * @param inStr  変換する文字列
     * @return String 変換した文字列
     * @throws IllegalArgumentException cvTypeが1か2以外の場合
     */
    public static String stringSizeConversion(int cvType, String inStr) throws IllegalArgumentException {

        // 半角英数記号
        final String[] WORD_H1 = { "A", "B", "C", "D", "E",
                "F", "G", "H", "I", "J",
                "K", "L", "M", "N", "O",
                "P", "Q", "R", "S", "T",
                "U", "V", "W", "X", "Y",
                "Z",
                "a", "b", "c", "d", "e",
                "f", "g", "h", "i", "j",
                "k", "l", "m", "n", "o",
                "p", "q", "r", "s", "t",
                "u", "v", "w", "x", "y",
                "z",
                "1", "2", "3", "4", "5",
                "6", "7", "8", "9", "0",
                "\\", "\"",
                "!", "#", "$", "%", "&",
                "'", "(", ")", "*", "+",
                "-", "/", ",", ".", ":",
                ";", "<", "=", ">", "?",
                "@", "[", "]", "^", "_",
                "`", "{", "}", "|", "~" };

        // 全角英数記号
        final String[] WORD_Z1 = { "Ａ", "Ｂ", "Ｃ", "Ｄ", "Ｅ",
                "Ｆ", "Ｇ", "Ｈ", "Ｉ", "Ｊ",
                "Ｋ", "Ｌ", "Ｍ", "Ｎ", "Ｏ",
                "Ｐ", "Ｑ", "Ｒ", "Ｓ", "Ｔ",
                "Ｕ", "Ｖ", "Ｗ", "Ｘ", "Ｙ",
                "Ｚ",
                "ａ", "ｂ", "ｃ", "ｄ", "ｅ",
                "ｆ", "ｇ", "ｈ", "ｉ", "ｊ",
                "ｋ", "ｌ", "ｍ", "ｎ", "ｏ",
                "ｐ", "ｑ", "ｒ", "ｓ", "ｔ",
                "ｕ", "ｖ", "ｗ", "ｘ", "ｙ",
                "ｚ",
                "１", "２", "３", "４", "５",
                "６", "７", "８", "９", "０",
                "￥", "”",
                "！", "＃", "＄", "％", "＆",
                "’", "（", "）", "＊", "＋",
                "－", "／", "，", "．", "：",
                "；", "＜", "＝", "＞", "？",
                "＠", "［", "］", "＾", "＿",
                "｀", "｛", "｝", "｜", "￣" };

        // 半角カナ文字
        final String[] WORD_H2 = { "ｱ", "ｲ", "ｳ", "ｴ", "ｵ",
                "ｶ", "ｷ", "ｸ", "ｹ", "ｺ",
                "ｻ", "ｼ", "ｽ", "ｾ", "ｿ",
                "ﾀ", "ﾁ", "ﾂ", "ﾃ", "ﾄ",
                "ﾅ", "ﾆ", "ﾇ", "ﾈ", "ﾉ",
                "ﾊ", "ﾋ", "ﾌ", "ﾍ", "ﾎ",
                "ﾏ", "ﾐ", "ﾑ", "ﾒ", "ﾓ",
                "ﾔ", "ﾕ", "ﾖ",
                "ﾗ", "ﾘ", "ﾙ", "ﾚ", "ﾛ",
                "ﾜ", "ｦ", "ﾝ",
                "ｧ", "ｨ", "ｩ", "ｪ", "ｫ",
                "ｬ", "ｭ", "ｮ",
                "ﾞ", "ﾟ", "ｰ", "｡", "､",
                "｢", "｣", "･" };

        // 全角カナ文字
        final String[] WORD_Z2 = { "ア", "イ", "ウ", "エ", "オ",
                "カ", "キ", "ク", "ケ", "コ",
                "サ", "シ", "ス", "セ", "ソ",
                "タ", "チ", "ツ", "テ", "ト",
                "ナ", "ニ", "ヌ", "ネ", "ノ",
                "ハ", "ヒ", "フ", "ヘ", "ホ",
                "マ", "ミ", "ム", "メ", "モ",
                "ヤ", "ユ", "ヨ",
                "ラ", "リ", "ル", "レ", "ロ",
                "ワ", "ヲ", "ン",
                "ァ", "ィ", "ゥ", "ェ", "ォ",
                "ャ", "ュ", "ョ",
                "゛", "゜", "ー", "。", "、",
                "「", "」", "・" };

        // 半角濁点文字
        final String[] WORD_H3 = { "ｳﾞ",
                "ｶﾞ", "ｷﾞ", "ｸﾞ", "ｹﾞ", "ｺﾞ",
                "ｻﾞ", "ｼﾞ", "ｽﾞ", "ｾﾞ", "ｿﾞ",
                "ﾀﾞ", "ﾁﾞ", "ﾂﾞ", "ﾃﾞ", "ﾄﾞ",
                "ﾊﾞ", "ﾋﾞ", "ﾌﾞ", "ﾍﾞ", "ﾎﾞ" };

        // 全角濁点文字
        final String[] WORD_Z3 = { "ヴ",
                "ガ", "ギ", "グ", "ゲ", "ゴ",
                "ザ", "ジ", "ズ", "ゼ", "ゾ",
                "ダ", "ヂ", "ヅ", "デ", "ド",
                "バ", "ビ", "ブ", "ベ", "ボ" };

        // 半角半濁点文字
        final String[] WORD_H4 = { "ﾊﾟ", "ﾋﾟ", "ﾌﾟ", "ﾍﾟ", "ﾎﾟ" };

        // 全角半濁点文字
        final String[] WORD_Z4 = { "パ", "ピ", "プ", "ペ", "ポ" };

        // 半角濁点
        final String DAKUTEN_H = "ﾞ";
        // 全角濁点
        final String DAKUTEN_Z = "゛";
        // 半角半濁点
        final String HANDAKUTEN_H = "ﾟ";
        // 全角半濁点
        final String HANDAKUTEN_Z = "゜";

        // 引数チェック
        if (inStr == null) {
            return null;
        }

        if (cvType != 1 && cvType != 2) {
            throw new IllegalArgumentException("first parameter error!!");
        }

        StringBuffer sb = new StringBuffer(inStr);

        if (cvType == 1) {

            /****************/
            /* 半角 → 全角 */
            /****************/

            // 対象文字列を1文字ずつ調べる
            for (int i = 0; i < sb.length(); i++) {

                // 1文字取得
                String ch = String.valueOf(sb.charAt(i));

                // 英数記号変換フラグ
                boolean eisukigouFlg = false;

                // 取得した1文字が半角英数記号か調べる
                for (int j = 0; j < WORD_H1.length; j++) {

                    // 半角英数記号と比較
                    if (ch.equals(WORD_H1[j])) {

                        // 半角英数記号と一致した場合

                        // 半角を全角に変換する
                        sb.replace(i, i + 1, WORD_Z1[j]);
                        eisukigouFlg = true;
                        break;
                    } else {

                        // 一致しない場合
                        // 次の半角英数記号と比較する
                        continue;
                    }
                }

                // 半角英数記号を全角英数記号に変更した場合は次の文字を調べる
                if (eisukigouFlg) {
                    continue;
                }

                // 取得した1文字が半角カナか調べる
                for (int j = 0; j < WORD_H2.length; j++) {

                    // 半角カナ文字と比較
                    if (ch.equals(WORD_H2[j])) {

                        // 半角カナと一致した場合

                        // 最終文字か調べる
                        if (i != (sb.length() - 1)) {

                            // 最終文字でない場合は次の文字を取得
                            String ch2 = String.valueOf(sb.charAt(i + 1));

                            if (ch2.equals(DAKUTEN_H)) {

                                // 次の文字が半角濁点の場合

                                // 濁点文字変更フラグ
                                boolean dakutenFlg = false;

                                // 濁点文字か調べる
                                for (int k = 0; k < WORD_H3.length; k++) {

                                    if ((ch + DAKUTEN_H).equals(WORD_H3[k])) {

                                        // 半角を全角に変換する
                                        // 半角カナ + 半角濁点 -> 全角濁点文字
                                        sb.replace(i, i + 2, WORD_Z3[k]);
                                        dakutenFlg = true;
                                        break;
                                    }
                                }

                                // 全角濁点文字に変換できない場合は 全角カナ + 全角濁点 に変換する
                                if (!dakutenFlg) {

                                    // 半角を全角に変換する
                                    // 半角カナ + 半角濁点 -> 全角カナ + 全角濁点
                                    sb.replace(i, i + 1, WORD_Z2[j]);
                                    sb.replace(i + 1, i + 2, DAKUTEN_Z);
                                    i++;
                                }
                                break;

                            } else if (ch2.equals(HANDAKUTEN_H)) {

                                // 次の文字が半角半濁点の場合

                                // 半濁点文字変更フラグ
                                boolean handakutenFlg = false;

                                // 半濁点文字か調べる
                                for (int k = 0; k < WORD_H4.length; k++) {

                                    if ((ch + HANDAKUTEN_H).equals(WORD_H4[k])) {

                                        // 半角を全角に変換する
                                        // 半角カナ + 半角半濁点 -> 全角半濁点文字
                                        sb.replace(i, i + 2, WORD_Z4[k]);
                                        handakutenFlg = true;
                                        break;
                                    }
                                }

                                // 全角半濁点文字に変換できない場合は 全角カナ + 全角半濁点 に変換する
                                if (!handakutenFlg) {

                                    // 半角を全角に変換する
                                    // 半角カナ + 半角半濁点 -> 全角カナ + 全角半濁点
                                    sb.replace(i, i + 1, WORD_Z2[j]);
                                    sb.replace(i + 1, i + 2, HANDAKUTEN_Z);
                                    i++;
                                }
                                break;

                            } else {

                                // 次の文字が半角濁点または半角半濁点でない場合

                                // 半角を全角に変換する
                                sb.replace(i, i + 1, WORD_Z2[j]);
                                break;
                            }
                        } else {

                            // 最終文字の場合

                            // 半角を全角に変換する
                            sb.replace(i, i + 1, WORD_Z2[j]);
                            break;
                        }
                    } else {
                        // 一致しない場合は何もしない
                        // 次の半角カナ文字と比較する
                        continue;
                    }
                }
            }
        } else {

            /****************/
            /* 全角 → 半角 */
            /****************/

            // 対象文字列を1文字ずつ調べる
            for (int i = 0; i < sb.length(); i++) {

                // 1文字取得
                String ch = String.valueOf(sb.charAt(i));

                // 英数記号変換フラグ
                boolean eisukigouFlg = false;

                // 取得した1文字が全角英数記号か調べる
                for (int j = 0; j < WORD_Z1.length; j++) {

                    // 全角英数記号と比較
                    if (ch.equals(WORD_Z1[j])) {

                        // 全角英数記号と一致した場合

                        // 全角を半角に変換する
                        sb.replace(i, i + 1, WORD_H1[j]);
                        eisukigouFlg = true;
                        break;
                    } else {

                        // 一致しない場合
                        // 次の全角英数記号と比較する
                        continue;
                    }
                }

                // 半角英数記号を全角英数記号に変更した場合は次の文字を調べる
                if (eisukigouFlg) {
                    continue;
                }

                // カナ文字変換フラグ
                boolean kanaFlg = false;

                // 取得した1文字が全角カナ文字か調べる
                for (int j = 0; j < WORD_Z2.length; j++) {

                    // 全角カナ文字と比較
                    if (ch.equals(WORD_Z2[j])) {

                        // 全角カナ文字と一致した場合

                        // 全角を半角に変換する
                        sb.replace(i, i + 1, WORD_H2[j]);
                        kanaFlg = true;
                        break;
                    } else {
                        // 一致しない場合
                        // 次の全角カナ文字と比較する
                        continue;
                    }
                }

                // 半角カナ文字を全角カナ文字に変更した場合は次の文字を調べる
                if (kanaFlg) {
                    continue;
                }

                // カナ濁点文字変換フラグ
                boolean dakutenFlg = false;

                // 取得した1文字が全角カナ濁点文字文字か調べる
                for (int j = 0; j < WORD_Z3.length; j++) {

                    // 全角カナ濁点文字文字と比較
                    if (ch.equals(WORD_Z3[j])) {

                        // 全角カナ濁点文字文字と一致した場合

                        // 全角を半角に変換する
                        sb.replace(i, i + 1, WORD_H3[j]);
                        dakutenFlg = true;
                        break;
                    } else {

                        // 一致しない場合
                        // 次の全角カナ文字と比較する
                        continue;
                    }
                }

                // 半角カナ濁点文字を全角カナ濁点文字に変更した場合は次の文字を調べる
                if (dakutenFlg) {
                    // 全角1文字を半角2文字に変換した為カウンターをインクリメント
                    i++;
                    continue;
                }

                // カナ半濁点文字変換フラグ
                boolean handakutenFlg = false;

                // 取得した1文字が全角カナ半濁点文字文字か調べる
                for (int j = 0; j < WORD_Z4.length; j++) {

                    // 全角カナ半濁点文字文字と比較
                    if (ch.equals(WORD_Z4[j])) {

                        // 全角カナ半濁点文字文字と一致した場合

                        // 全角を半角に変換する
                        sb.replace(i, i + 1, WORD_H4[j]);
                        handakutenFlg = true;
                        break;
                    } else {

                        // 一致しない場合
                        // 次の全角カナ文字と比較する
                        continue;
                    }
                }

                // 半角カナ半濁点文字を全角カナ半濁点文字に変更した場合は次の文字を調べる
                if (handakutenFlg) {
                    // 全角1文字を半角2文字に変換した為カウンターをインクリメント
                    i++;
                    continue;
                }
            }
        }
        return sb.toString();
    }

    /**
     * カナ文字を全角半角を変換するメソッドです
     *
     * @param cvType 変換タイプ
     *               (1:半角→全角 2:全角→半角)
     * @param inStr  変換する文字列
     * @return String 変換した文字列
     * @throws IllegalArgumentException cvTypeが1か2以外の場合
     */
    public static String stringSizeConversionKana(int cvType, String inStr) throws IllegalArgumentException {

        // 半角カナ文字
        final String[] WORD_H2 = { "ｱ", "ｲ", "ｳ", "ｴ", "ｵ",
                "ｶ", "ｷ", "ｸ", "ｹ", "ｺ",
                "ｻ", "ｼ", "ｽ", "ｾ", "ｿ",
                "ﾀ", "ﾁ", "ﾂ", "ﾃ", "ﾄ",
                "ﾅ", "ﾆ", "ﾇ", "ﾈ", "ﾉ",
                "ﾊ", "ﾋ", "ﾌ", "ﾍ", "ﾎ",
                "ﾏ", "ﾐ", "ﾑ", "ﾒ", "ﾓ",
                "ﾔ", "ﾕ", "ﾖ",
                "ﾗ", "ﾘ", "ﾙ", "ﾚ", "ﾛ",
                "ﾜ", "ｦ", "ﾝ",
                "ｯ",
                "ｧ", "ｨ", "ｩ", "ｪ", "ｫ",
                "ｬ", "ｭ", "ｮ",
                "ﾞ", "ﾟ", "ｰ", "｡", "､",
                "｢", "｣", "･", "-" };

        // 全角カナ文字
        final String[] WORD_Z2 = { "ア", "イ", "ウ", "エ", "オ",
                "カ", "キ", "ク", "ケ", "コ",
                "サ", "シ", "ス", "セ", "ソ",
                "タ", "チ", "ツ", "テ", "ト",
                "ナ", "ニ", "ヌ", "ネ", "ノ",
                "ハ", "ヒ", "フ", "ヘ", "ホ",
                "マ", "ミ", "ム", "メ", "モ",
                "ヤ", "ユ", "ヨ",
                "ラ", "リ", "ル", "レ", "ロ",
                "ワ", "ヲ", "ン",
                "ッ",
                "ァ", "ィ", "ゥ", "ェ", "ォ",
                "ャ", "ュ", "ョ",
                "゛", "゜", "ー", "。", "、",
                "「", "」", "・", "ー" };

        // 半角濁点文字
        final String[] WORD_H3 = { "ｳﾞ",
                "ｶﾞ", "ｷﾞ", "ｸﾞ", "ｹﾞ", "ｺﾞ",
                "ｻﾞ", "ｼﾞ", "ｽﾞ", "ｾﾞ", "ｿﾞ",
                "ﾀﾞ", "ﾁﾞ", "ﾂﾞ", "ﾃﾞ", "ﾄﾞ",
                "ﾊﾞ", "ﾋﾞ", "ﾌﾞ", "ﾍﾞ", "ﾎﾞ" };

        // 全角濁点文字
        final String[] WORD_Z3 = { "ヴ",
                "ガ", "ギ", "グ", "ゲ", "ゴ",
                "ザ", "ジ", "ズ", "ゼ", "ゾ",
                "ダ", "ヂ", "ヅ", "デ", "ド",
                "バ", "ビ", "ブ", "ベ", "ボ" };

        // 半角半濁点文字
        final String[] WORD_H4 = { "ﾊﾟ", "ﾋﾟ", "ﾌﾟ", "ﾍﾟ", "ﾎﾟ" };

        // 全角半濁点文字
        final String[] WORD_Z4 = { "パ", "ピ", "プ", "ペ", "ポ" };

        // 半角濁点
        final String DAKUTEN_H = "ﾞ";
        // 全角濁点
        final String DAKUTEN_Z = "゛";
        // 半角半濁点
        final String HANDAKUTEN_H = "ﾟ";
        // 全角半濁点
        final String HANDAKUTEN_Z = "゜";

        // 引数チェック
        if (inStr == null) {
            return null;
        }
        if (cvType != 1 && cvType != 2) {
            throw new IllegalArgumentException("first parameter error!!");
        }

        StringBuffer sb = new StringBuffer(inStr);

        if (cvType == 1) {

            /****************/
            /* 半角 → 全角 */
            /****************/

            String pKanaFlg = "0";

            // 対象文字列を1文字ずつ調べる
            for (int i = 0; i < sb.length(); i++) {

                // 1文字取得
                String ch = String.valueOf(sb.charAt(i));

                // 取得した1文字が半角カナか調べる
                for (int j = 0; j < WORD_H2.length; j++) {

                    // 半角カナ文字と比較
                    if (ch.equals(WORD_H2[j])) {

                        // 半角カナと一致した場合

                        if (pKanaFlg.equals("0") && ch.equals("-")) {
                            break;
                        }

                        pKanaFlg = "1";

                        // 最終文字か調べる
                        if (i != (sb.length() - 1)) {

                            // 最終文字でない場合は次の文字を取得
                            String ch2 = String.valueOf(sb.charAt(i + 1));

                            if (ch2.equals(DAKUTEN_H)) {

                                // 次の文字が半角濁点の場合

                                // 濁点文字変更フラグ
                                boolean dakutenFlg = false;

                                // 濁点文字か調べる
                                for (int k = 0; k < WORD_H3.length; k++) {

                                    if ((ch + DAKUTEN_H).equals(WORD_H3[k])) {

                                        // 半角を全角に変換する
                                        // 半角カナ + 半角濁点 -> 全角濁点文字
                                        sb.replace(i, i + 2, WORD_Z3[k]);
                                        dakutenFlg = true;
                                        break;
                                    }
                                }

                                // 全角濁点文字に変換できない場合は 全角カナ + 全角濁点 に変換する
                                if (!dakutenFlg) {

                                    // 半角を全角に変換する
                                    // 半角カナ + 半角濁点 -> 全角カナ + 全角濁点
                                    sb.replace(i, i + 1, WORD_Z2[j]);
                                    sb.replace(i + 1, i + 2, DAKUTEN_Z);
                                    i++;
                                }
                                break;

                            } else if (ch2.equals(HANDAKUTEN_H)) {

                                // 次の文字が半角半濁点の場合

                                // 半濁点文字変更フラグ
                                boolean handakutenFlg = false;

                                // 半濁点文字か調べる
                                for (int k = 0; k < WORD_H4.length; k++) {

                                    if ((ch + HANDAKUTEN_H).equals(WORD_H4[k])) {

                                        // 半角を全角に変換する
                                        // 半角カナ + 半角半濁点 -> 全角半濁点文字
                                        sb.replace(i, i + 2, WORD_Z4[k]);
                                        handakutenFlg = true;
                                        break;
                                    }
                                }

                                // 全角半濁点文字に変換できない場合は 全角カナ + 全角半濁点 に変換する
                                if (!handakutenFlg) {

                                    // 半角を全角に変換する
                                    // 半角カナ + 半角半濁点 -> 全角カナ + 全角半濁点
                                    sb.replace(i, i + 1, WORD_Z2[j]);
                                    sb.replace(i + 1, i + 2, HANDAKUTEN_Z);
                                    i++;
                                }
                                break;

                            } else {

                                // 次の文字が半角濁点または半角半濁点でない場合

                                // 半角を全角に変換する
                                sb.replace(i, i + 1, WORD_Z2[j]);
                                break;
                            }
                        } else {

                            // 最終文字の場合

                            // 半角を全角に変換する
                            sb.replace(i, i + 1, WORD_Z2[j]);
                            break;
                        }
                    } else {
                        if (j == (WORD_H2.length - 1)) {
                            pKanaFlg = "0";
                        }
                        // 一致しない場合は何もしない
                        // 次の半角カナ文字と比較する
                        continue;
                    }
                }
            }

        } else {

            /****************/
            /* 全角 → 半角 */
            /****************/

            // 対象文字列を1文字ずつ調べる
            for (int i = 0; i < sb.length(); i++) {

                // 1文字取得
                String ch = String.valueOf(sb.charAt(i));

                // カナ文字変換フラグ
                boolean kanaFlg = false;

                // 取得した1文字が全角カナ文字か調べる
                for (int j = 0; j < WORD_Z2.length; j++) {

                    // 全角カナ文字と比較
                    if (ch.equals(WORD_Z2[j])) {

                        // 全角カナ文字と一致した場合

                        // 全角を半角に変換する
                        sb.replace(i, i + 1, WORD_H2[j]);
                        kanaFlg = true;
                        break;
                    } else {

                        // 一致しない場合
                        // 次の全角カナ文字と比較する
                        continue;
                    }
                }

                // 半角カナ文字を全角カナ文字に変更した場合は次の文字を調べる
                if (kanaFlg) {
                    continue;
                }

                // カナ濁点文字変換フラグ
                boolean dakutenFlg = false;

                // 取得した1文字が全角カナ濁点文字文字か調べる
                for (int j = 0; j < WORD_Z3.length; j++) {

                    // 全角カナ濁点文字文字と比較
                    if (ch.equals(WORD_Z3[j])) {

                        // 全角カナ濁点文字文字と一致した場合

                        // 全角を半角に変換する
                        sb.replace(i, i + 1, WORD_H3[j]);
                        dakutenFlg = true;
                        break;
                    } else {

                        // 一致しない場合
                        // 次の全角カナ文字と比較する
                        continue;
                    }
                }

                // 半角カナ濁点文字を全角カナ濁点文字に変更した場合は次の文字を調べる
                if (dakutenFlg) {
                    // 全角1文字を半角2文字に変換した為カウンターをインクリメント
                    i++;
                    continue;
                }

                // カナ半濁点文字変換フラグ
                boolean handakutenFlg = false;

                // 取得した1文字が全角カナ半濁点文字文字か調べる
                for (int j = 0; j < WORD_Z4.length; j++) {

                    // 全角カナ半濁点文字文字と比較
                    if (ch.equals(WORD_Z4[j])) {

                        // 全角カナ半濁点文字文字と一致した場合

                        // 全角を半角に変換する
                        sb.replace(i, i + 1, WORD_H4[j]);
                        handakutenFlg = true;
                        break;
                    } else {

                        // 一致しない場合
                        // 次の全角カナ文字と比較する
                        continue;
                    }
                }

                // 半角カナ半濁点文字を全角カナ半濁点文字に変更した場合は次の文字を調べる
                if (handakutenFlg) {
                    // 全角1文字を半角2文字に変換した為カウンターをインクリメント
                    i++;
                    continue;
                }
            }
        }
        return sb.toString();
    }

    /**
     * 文字列内の変換対象パラメータを変換パラメータに置換します。
     *
     * @param str        文字列
     * @param fromParams 変換対象パラメータ配列
     * @param toParams   変換パラメータ配列
     * @return 置換後の文字列
     * @throws IllegalArgumentException 変換対象パラメータ配列が null、
     *                                  または変換パラメータ配列が null、
     *                                  または配列の数が異なる場合
     */
    public static String replaceParams(String str, String[] fromParams,
            String[] toParams) throws IllegalArgumentException {

        if (fromParams == null || toParams == null || fromParams.length != toParams.length) {
            throw new IllegalArgumentException();
        }

        if (str == null) {
            return null;
        }

        StringBuffer sb = new StringBuffer(str);

        for (int i = 0; i < fromParams.length; i++) {
            if (fromParams[i] == null || toParams[i] == null) {
                continue;
            }
            int idx = sb.toString().indexOf(fromParams[i]);
            if (idx != -1) {
                sb.replace(idx, idx + fromParams[i].length(), toParams[i]);
            }
        }
        return sb.toString();
    }

    /**
     * JISコード変換を行い正式UnicodeをWindowsのUnicodeに変換します。
     *
     * @param str 変換前文字列
     * @return 変換後文字列
     */
    public static String toJisForWindows(String str) {

        if (str == null) {
            return null;
        }

        StringBuffer sb = new StringBuffer();
        char c;
        for (int i = 0; i < str.length(); i++) {
            c = str.charAt(i);
            switch (c) {
                case 0x005c: // FULLWIDTH REVERSE SOLIDUS ->
                    c = 0xff3c; // REVERSE SOLIDUS（＼）
                    break;
                case 0x301c: // FULLWIDTH TILDE ->
                    c = 0xff5e; // WAVE DASH（～）
                    break;
                case 0x2016: // PARALLEL TO ->
                    c = 0x2225; // DOUBLE VERTICAL LINE（∥）
                    break;
                case 0x2212: // FULLWIDTH HYPHEN-MINUS ->
                    c = 0xff0d; // MINUS SIGN（－）
                    break;
                case 0x00a2: // FULLWIDTH CENT SIGN ->
                    c = 0xffe0; // CENT SIGN（￠）
                    break;
                case 0x00a3: // FULLWIDTH POUND SIGN ->
                    c = 0xffe1; // POUND SIGN（￡）
                    break;
                case 0x00ac: // FULLWIDTH NOT SIGN ->
                    c = 0xffe2; // NOT SIGN（￢）
                    break;
            }
            sb.append(c);
        }
        return new String(sb);
    }

    /**
     * 文字列に含まれる特殊文字をキーワードに置換する。<br>
     *
     * @param str 置換する文字列
     * @return 置換後の文字列
     */
    public static String toReference(String str) {

        if (str == null) {
            return null;
        }

        StringBuffer sb = new StringBuffer(str);

        for (int i = 0; i < sb.length(); i++) {
            char c = sb.charAt(i);
            switch (c) {
                case '&':
                    sb.replace(i, i + 1, "&amp;");
                    break;
                case '<':
                    sb.replace(i, i + 1, "&lt;");
                    break;
                case '>':
                    sb.replace(i, i + 1, "&gt;");
                    break;
                case '"':
                    sb.replace(i, i + 1, "&quot;");
                    break;
                // case '\'' :
                // sb.replace(i, i + 1, "&#39;");
                // break;
                default:
                    break;
            }
        }
        return sb.toString();
    }

    /**
     * システムプロパティから情報を取得します。
     *
     * @param property プロパティ名
     * @return プロパティ値
     */
    private static String getSystemProperty(String property) {
        try {
            return System.getProperty(property);
        } catch (SecurityException ex) {
            // we are not allowed to look at this property
            System.err.println(
                    "Caught a SecurityException reading the system property '" + property
                            + "'; the SystemUtils property value will default to null.");
            return null;
        }
    }

    /**
     * 指定された配列の内容の文字列表現を返します。
     *
     * @param a 文字列表現を返す配列
     * @return a の文字列表現
     */
    public static String toString(long[] a) {
        if (a == null)
            return "null";
        if (a.length == 0)
            return "[]";

        StringBuffer buf = new StringBuffer();
        buf.append('[');
        buf.append(a[0]);

        for (int i = 1; i < a.length; i++) {
            buf.append(", ");
            buf.append(a[i]);
        }

        buf.append("]");
        return buf.toString();
    }

    /**
     * 指定された配列の内容の文字列表現を返します。
     *
     * @param a 文字列表現を返す配列
     * @return a の文字列表現
     */
    public static String toString(int[] a) {
        if (a == null)
            return "null";
        if (a.length == 0)
            return "[]";

        StringBuffer buf = new StringBuffer();
        buf.append('[');
        buf.append(a[0]);

        for (int i = 1; i < a.length; i++) {
            buf.append(", ");
            buf.append(a[i]);
        }

        buf.append("]");
        return buf.toString();
    }

    /**
     * 指定された配列の内容の文字列表現を返します。
     *
     * @param a 文字列表現を返す配列
     * @return a の文字列表現
     */
    public static String toString(short[] a) {
        if (a == null)
            return "null";
        if (a.length == 0)
            return "[]";

        StringBuffer buf = new StringBuffer();
        buf.append('[');
        buf.append(a[0]);

        for (int i = 1; i < a.length; i++) {
            buf.append(", ");
            buf.append(a[i]);
        }

        buf.append("]");
        return buf.toString();
    }

    /**
     * 指定された配列の内容の文字列表現を返します。
     *
     * @param a 文字列表現を返す配列
     * @return a の文字列表現
     */
    public static String toString(char[] a) {
        if (a == null)
            return "null";
        if (a.length == 0)
            return "[]";

        StringBuffer buf = new StringBuffer();
        buf.append('[');
        buf.append(a[0]);

        for (int i = 1; i < a.length; i++) {
            buf.append(", ");
            buf.append(a[i]);
        }

        buf.append("]");
        return buf.toString();
    }

    /**
     * 指定された配列の内容の文字列表現を返します。
     *
     * @param a 文字列表現を返す配列
     * @return a の文字列表現
     */
    public static String toString(byte[] a) {
        if (a == null)
            return "null";
        if (a.length == 0)
            return "[]";

        StringBuffer buf = new StringBuffer();
        buf.append('[');
        buf.append(a[0]);

        for (int i = 1; i < a.length; i++) {
            buf.append(", ");
            buf.append(a[i]);
        }

        buf.append("]");
        return buf.toString();
    }

    /**
     * 指定された配列の内容の文字列表現を返します。
     *
     * @param a 文字列表現を返す配列
     * @return a の文字列表現
     */
    public static String toString(boolean[] a) {
        if (a == null)
            return "null";
        if (a.length == 0)
            return "[]";

        StringBuffer buf = new StringBuffer();
        buf.append('[');
        buf.append(a[0]);

        for (int i = 1; i < a.length; i++) {
            buf.append(", ");
            buf.append(a[i]);
        }

        buf.append("]");
        return buf.toString();
    }

    /**
     * 指定された配列の内容の文字列表現を返します。
     *
     * @param a 文字列表現を返す配列
     * @return a の文字列表現
     */
    public static String toString(float[] a) {
        if (a == null)
            return "null";
        if (a.length == 0)
            return "[]";

        StringBuffer buf = new StringBuffer();
        buf.append('[');
        buf.append(a[0]);

        for (int i = 1; i < a.length; i++) {
            buf.append(", ");
            buf.append(a[i]);
        }

        buf.append("]");
        return buf.toString();
    }

    /**
     * 指定された配列の内容の文字列表現を返します。
     *
     * @param a 文字列表現を返す配列
     * @return a の文字列表現
     */
    public static String toString(double[] a) {
        if (a == null)
            return "null";
        if (a.length == 0)
            return "[]";

        StringBuffer buf = new StringBuffer();
        buf.append('[');
        buf.append(a[0]);

        for (int i = 1; i < a.length; i++) {
            buf.append(", ");
            buf.append(a[i]);
        }

        buf.append("]");
        return buf.toString();
    }

    /**
     * 指定された配列の内容の文字列表現を返します。
     *
     * @param a 文字列表現を返す配列
     * @return a の文字列表現
     */
    public static String toString(Object[] a) {
        if (a == null)
            return "null";
        if (a.length == 0)
            return "[]";

        StringBuffer buf = new StringBuffer();

        for (int i = 0; i < a.length; i++) {
            if (i == 0)
                buf.append('[');
            else
                buf.append(", ");

            buf.append(String.valueOf(a[i]));
        }

        buf.append("]");
        return buf.toString();
    }

    /**
     * 指定された文字列がnull または 空文字("")の場合、デフォルト値を返します。
     *
     * <pre>
     * StringUtil.defaultIfEmpty("1", "default")  = "1"
     * StringUtil.defaultIfEmpty("1", "")         = "1"
     * StringUtil.defaultIfEmpty("1", null)       = "1"
     * StringUtil.defaultIfEmpty("", "default")   = "default"
     * StringUtil.defaultIfEmpty("", "")          = ""
     * StringUtil.defaultIfEmpty("", null)        = null
     * StringUtil.defaultIfEmpty(null, "default") = "default"
     * StringUtil.defaultIfEmpty(null, "")        = ""
     * StringUtil.defaultIfEmpty(null, null)      = null
     * </pre>
     *
     * @param str        対象となる文字列
     * @param defaultStr デフォルト文字列
     * @return 変換した文字列
     * @since 1.0.2
     */
    public static String defaultIfEmpty(String str, String defaultStr) {
        return str.isEmpty() ? defaultStr : str;
    }

    /**
     * ２つの文字列の比較を行います。<br>
     * 第一引数と、第二引数が等しい場合、0を返します。<br>
     * 第一引数が、第二引数より小さい場合、負数を返します。<br>
     * 第一引数が、第二引数より大きい場合、正数を返します。
     *
     * <pre>
     * StringUtil.compare("1", "1")               = 0
     * StringUtil.compare("", "")                 = 0
     * StringUtil.compare(null, null)             = 0
     * StringUtil.compare("A", "A")               = 0
     * StringUtil.compare("012345", "012345")     = 0
     * StringUtil.compare("0.12345", "0.12345")   = 0
     * StringUtil.compare("-0.12345", "-0.12345") = 0
     * {@code
     * StringUtil.compare("1", "2")               < 0
     * StringUtil.compare("", "2")                < 0
     * StringUtil.compare(null, "2")              < 0
     * StringUtil.compare("A", "a")               < 0
     * StringUtil.compare("89", "90")             < 0
     * StringUtil.compare("8.9", "9.0")           < 0
     * StringUtil.compare("-8.9", "9.0")          < 0
     * StringUtil.compare("2", "1")               > 0
     * StringUtil.compare("2", "")                > 0
     * StringUtil.compare("2", null)              > 0
     * StringUtil.compare("a", "A")               > 0
     * StringUtil.compare("21", "2")              > 0
     * StringUtil.compare("0.21", "0.2")          > 0
     * StringUtil.compare("0.21", "-0.2")         > 0
     * }
     * </pre>
     *
     * @param str1 文字列１
     * @param str2 文字列２
     * @return int ゼロ：str1 = str2の場合
     *         正数：str1 &gt; str2の場合
     *         負数：str1 &lt; str2の場合
     * @since 1.0.2
     */
    public static int compare(String str1, String str2) {

        str1 = defaultIfEmpty(str1, STRING_BLANK);
        str2 = defaultIfEmpty(str2, STRING_BLANK);

        return str1.compareTo(str2);
    }

    /**
     * ２つの文字列の比較を行います。<br>
     * 第一引数が、第二引数より大きい場合trueを返します。
     *
     * <pre>
     * StringUtil.gt("2", "1")               = true
     * StringUtil.gt("2", "")                = true
     * StringUtil.gt("2", null)              = true
     * StringUtil.gt("a", "A")               = true
     * StringUtil.gt("21", "2")              = true
     * StringUtil.gt("0.21", "0.2")          = true
     * StringUtil.gt("0.21", "-0.2")         = true
     * StringUtil.gt("1", "1")               = false
     * StringUtil.gt("", "")                 = false
     * StringUtil.gt(null, null)             = false
     * StringUtil.gt("A", "A")               = false
     * StringUtil.gt("012345", "012345")     = false
     * StringUtil.gt("0.12345", "0.12345")   = false
     * StringUtil.gt("-0.12345", "-0.12345") = false
     * StringUtil.gt("1", "2")               = false
     * StringUtil.gt("", "2")                = false
     * StringUtil.gt(null, "2")              = false
     * StringUtil.gt("A", "a")               = false
     * StringUtil.gt("89", "90")             = false
     * StringUtil.gt("8.9", "9.0")           = false
     * StringUtil.gt("-8.9", "9.0")          = false
     * </pre>
     *
     * @param str1 文字列１
     * @param str2 文字列２
     * @return boolean true:str1&gt;str2, false:str1&lt;=str2
     * @since 1.0.2
     */
    public static boolean gt(String str1, String str2) {

        if (compare(str1, str2) > 0) {
            return true;
        }

        return false;
    }

    /**
     * ２つの文字列の比較を行います。<br>
     * 第一引数が、第二引数より大きいか、等しい場合trueを返します。
     *
     * <pre>
     * StringUtil.ge("1", "1")               = true
     * StringUtil.ge("", "")                 = true
     * StringUtil.ge(null, null)             = true
     * StringUtil.ge("A", "A")               = true
     * StringUtil.ge("012345", "012345")     = true
     * StringUtil.ge("0.12345", "0.12345")   = true
     * StringUtil.ge("-0.12345", "-0.12345") = true
     * StringUtil.ge("2", "1")               = true
     * StringUtil.ge("2", "")                = true
     * StringUtil.ge("2", null)              = true
     * StringUtil.ge("a", "A")               = true
     * StringUtil.ge("21", "2")              = true
     * StringUtil.ge("0.21", "0.2")          = true
     * StringUtil.ge("0.21", "-0.2")         = true
     * StringUtil.ge("1", "2")               = false
     * StringUtil.ge("", "2")                = false
     * StringUtil.ge(null, "2")              = false
     * StringUtil.ge("A", "a")               = false
     * StringUtil.ge("89", "90")             = false
     * StringUtil.ge("8.9", "9.0")           = false
     * StringUtil.ge("-8.9", "9.0")          = false
     * </pre>
     *
     * @param str1 文字列１
     * @param str2 文字列２
     * @return boolean true:str1&gt;=str2, false:str1&lt;str2
     * @since 1.0.2
     */
    public static boolean ge(String str1, String str2) {

        return !lt(str1, str2);
    }

    /**
     * ２つの文字列の比較を行います。<br>
     * 第一引数が、第二引数より小さい場合trueを返します。
     *
     * <pre>
     * StringUtil.lt("1", "2")               = true
     * StringUtil.lt("", "2")                = true
     * StringUtil.lt(null, "2")              = true
     * StringUtil.lt("A", "a")               = true
     * StringUtil.lt("89", "90")             = true
     * StringUtil.lt("8.9", "9.0")           = true
     * StringUtil.lt("-8.9", "9.0")          = true
     * StringUtil.lt("1", "1")               = false
     * StringUtil.lt("", "")                 = false
     * StringUtil.lt(null, null)             = false
     * StringUtil.lt("A", "A")               = false
     * StringUtil.lt("012345", "012345")     = false
     * StringUtil.lt("0.12345", "0.12345")   = false
     * StringUtil.lt("-0.12345", "-0.12345") = false
     * StringUtil.lt("2", "1")               = false
     * StringUtil.lt("2", "")                = false
     * StringUtil.lt("2", null)              = false
     * StringUtil.lt("a", "A")               = false
     * StringUtil.lt("21", "2")              = false
     * StringUtil.lt("0.21", "0.2")          = false
     * StringUtil.lt("0.21", "-0.2")         = false
     * </pre>
     *
     * @param str1 文字列１
     * @param str2 文字列２
     * @return boolean true:str1&lt;str2, false:str1&gt;=str2
     * @since 1.0.2
     */
    public static boolean lt(String str1, String str2) {

        if (compare(str1, str2) < 0) {
            return true;
        }

        return false;
    }

    /**
     * ２つの文字列の比較を行います。<br>
     * 第一引数が、第二引数より小さいか、等しい場合trueを返します。
     *
     * <pre>
     * StringUtil.le("1", "1")               = true
     * StringUtil.le("", "")                 = true
     * StringUtil.le(null, null)             = true
     * StringUtil.le("A", "A")               = true
     * StringUtil.le("012345", "012345")     = true
     * StringUtil.le("0.12345", "0.12345")   = true
     * StringUtil.le("-0.12345", "-0.12345") = true
     * StringUtil.le("1", "2")               = true
     * StringUtil.le("", "2")                = true
     * StringUtil.le(null, "2")              = true
     * StringUtil.le("A", "a")               = true
     * StringUtil.le("89", "90")             = true
     * StringUtil.le("8.9", "9.0")           = true
     * StringUtil.le("-8.9", "9.0")          = true
     * StringUtil.le("2", "1")               = false
     * StringUtil.le("2", "")                = false
     * StringUtil.le("2", null)              = false
     * StringUtil.le("a", "A")               = false
     * StringUtil.le("21", "2")              = false
     * StringUtil.le("0.21", "0.2")          = false
     * StringUtil.le("0.21", "-0.2")         = false
     * </pre>
     *
     * @param str1 文字列１
     * @param str2 文字列２
     * @return boolean true:str1&lt;=str2, false:str1&gt;str2
     * @since 1.0.2
     */
    public static boolean le(String str1, String str2) {

        return !gt(str1, str2);
    }

    /**
     * 与えられた引数が0 または 引数をtrimした結果が空文字("") の場合、trueを返します。<br>
     * それ以外の場合はfalseを返します。<br>
     *
     * <pre>
     * StringUtils.isZero("0")        = true
     * StringUtils.isZero("")         = true
     * StringUtils.isZero(" ")        = true
     * StringUtils.isZero(" 0 ")      = false
     * StringUtils.isZero("1")        = false
     * StringUtils.isZero("-1")       = false
     * StringUtils.isZero("0.001")    = false
     * StringUtils.isZero("-0.001")   = false
     * StringUtils.isZero(null)       = false
     * </pre>
     *
     * @param str the String to check, may be null
     * @return <code>true</code> if the String is empty or 0
     * @since 1.0.2
     */
    public static boolean isZero(String str) {

        if (str == null) {
            return false;
        }

        if (STRING_ZERO.equals(str)) {
            return true;
        }

        if (STRING_BLANK.equals(str.trim())) {
            return true;
        }

        return false;
    }

    /**
     * unformat string to replace "-" with ""
     *
     * @param formatStr the String to trim
     * @return 変換した文字列
     * @since 1.0.2
     */
    public static String unformatString(String formatStr) {
        if (!formatStr.isBlank()) {
            return formatStr.replaceAll(DERIM, STRING_BLANK);
        }
        return null;
    }

    /**
     * 指定された文字列がnull または 空文字("")の場合、" "(スペース)を返します。
     *
     * <pre>
     * StringUtil.blankToSpace("abc")  = "abc"
     * StringUtil.blankToSpace(null)   = " "
     * StringUtil.blankToSpace("")     = " "
     * StringUtil.blankToSpace(" ")    = " "
     * StringUtil.blankToSpace("  ")   = "  "
     * </pre>
     *
     * @param str 対象となる文字列
     * @return 変換した文字列
     * @since 1.0.3
     */
    public static String blankToSpace(String str) {
        return defaultIfEmpty(str, STRING_SPACE);
    }

    /**
     * 指定された文字列の文字幅を返す。
     * (半角文字は幅1文字、全角文字は幅2文字として計算する。)
     *
     * <pre>
     * StringUtil.getStrWidth("ABC")    = 3
     * StringUtil.getStrWidth("ＡＢＣ")  = 6
     * StringUtil.getStrWidth(" ")      = 1
     * StringUtil.getStrWidth(null)     = 0
     * </pre>
     *
     * @param value 対象となる文字列
     * @return 文字の幅数
     * @since 1.0.20
     */
    public static int getStrWidth(String value) {
        int length = 0;

        if (value == null) {

            return 0;
        }

        for (int i = 0; i < value.length(); i++) {
            char c = value.charAt(i);
            if (c >= 0x20 && c <= 0x7E) {
                // JISローマ字(ASCII)
                length++;
            } else if (c >= 0xFF61 && c <= 0xFF9F) {
                // JISカナ(半角カナ)
                length++;
            } else {
                // その他(全角)
                length += 2;
            }
        }
        return length;
    }

    /**
     * 文字列を指定された文字で左埋めします。
     * (全角文字 = 2文字、半角文字 = 1文字として計算します。)
     *
     * @param str      対象となる文字列
     * @param strWidth 対象となる文字幅
     * @param padChar  埋める文字列
     * @return 変換した文字列
     * @since 1.0.20
     */
    public static String padCharLeftByWidth(String str, int strWidth, char padChar) {

        if (str == null || getStrWidth(str) == 0) {
            return str;
        }

        if (getStrWidth(str) >= strWidth) {
            // 対象となる文字幅以上はそのまま返却する
            return str;
        }

        // 左埋め
        StringBuffer sb = new StringBuffer(str);
        int max = strWidth - getStrWidth(str);

        for (int i = 0; i < max; i++) {
            sb.insert(0, padChar);
        }

        return sb.toString();
    }

    /**
     * 文字列を指定された文字でバイト長により右埋めします。
     * (全角文字 = 2文字、半角文字 = 1文字として計算します。)
     *
     * @param str      対象となる文字列
     * @param strWidth 対象となる文字幅
     * @param padChar  埋める文字列
     * @return 変換した文字列
     * @since 1.0.20
     */
    public static String padCharRightByWidth(String str, int strWidth, char padChar) {

        if (str == null || getStrWidth(str) == 0) {
            return str;
        }

        if (getStrWidth(str) >= strWidth) {
            // 対象となる文字幅以上はそのまま返却する
            return str;
        }

        // 右埋め
        StringBuffer sb = new StringBuffer(str);
        int max = strWidth - getStrWidth(str);

        for (int i = 0; i < max; i++) {
            str = padChar + str;
            sb.append(padChar);
        }

        return sb.toString();
    }

}