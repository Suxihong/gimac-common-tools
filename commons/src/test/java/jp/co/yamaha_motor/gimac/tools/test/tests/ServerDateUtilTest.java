package jp.co.yamaha_motor.gimac.tools.test.tests;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Locale;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import jp.co.yamaha_motor.gimac.tools.util.ServerDateUtil;

@DisplayName("ServerDateUtilのテストクラス")
class ServerDateUtilTest {

    @Test
    @DisplayName("データベースサーバー日付とタイムスタンプの変換テストです")
    void testServerDateUtil() {
        // JdbcTemplateのモックオブジェクトを設定
        JdbcTemplate jdbcTemplate = new JdbcTemplate() {
            @Override
            public <T> T queryForObject(String sql, Class<T> requiredType) {
                // データベースから現在のタイムスタンプを取得するモック
                return requiredType.cast(Timestamp.valueOf("2024-05-15 14:30:45"));
            }
        };
        ServerDateUtil.setJdbcTemplate(jdbcTemplate);

        // データベースサーバーの日付を取得するテスト
        LocalDate date = ServerDateUtil.getDbServerDate();
        Assertions.assertEquals(LocalDate.of(2024, 5, 15), date);

        // データベースサーバーのタイムスタンプを取得するテスト
        Timestamp timestamp = ServerDateUtil.getDbServerTime();
        Assertions.assertEquals(Timestamp.valueOf("2024-05-15 14:30:45"), timestamp);

        // データベースサーバーの日付を中間形式で取得するテスト
        String mediumFormat = ServerDateUtil.getDbServerDateMediumFormat(Locale.JAPAN);
        Assertions.assertEquals("2024/05/15", mediumFormat);

        // データベースサーバーのタイムスタンプをフォーマットするテスト
        String timestampFormat = ServerDateUtil.getDbServerDateTimestampFormat(Locale.JAPAN);
        Assertions.assertEquals("2024/05/15 14:30:45", timestampFormat);

        // データベースサーバーの日付をyyyyMMdd形式で取得するテスト
        String ymdFormat = ServerDateUtil.getDbServerDateYMD();
        Assertions.assertEquals("20240515", ymdFormat);

        // データベースサーバーの日時をyyyyMMddHHmmss形式で取得するテスト
        String ymdhmsFormat = ServerDateUtil.getDbServerDateYMDHMS();
        Assertions.assertEquals("20240515143045", ymdhmsFormat);
    }

//    @Test
//    @DisplayName("実際のデータベースからタイムスタンプを取得して変換するテスト")
//    void testRealDatabase() {
//        // データベース接続用のDataSourceを設定
//        BasicDataSource dataSource = new BasicDataSource();
//        dataSource.setDriverClassName("org.postgresql.Driver");
//        dataSource.setUrl("jdbc:postgresql://localhost:5432/postgres");
//        dataSource.setUsername("postgres");
//        dataSource.setPassword("43h77b9");
//
//        // JdbcTemplateを設定
//        JdbcTemplate realJdbcTemplate = new JdbcTemplate(dataSource);
//        ServerDateUtil.setJdbcTemplate(realJdbcTemplate);
//
//        // データベースから現在のタイムスタンプを取得
//        Timestamp timestamp = ServerDateUtil.getDbServerTime();
//        System.out.println("データベースから取得したタイムスタンプ: " + timestamp);
//
//        // 各種フォーマットで変換して出力
//        LocalDate date = ServerDateUtil.getDbServerDate();
//        System.out.println("データベースから取得した日付: " + date);
//
//        String mediumFormat = ServerDateUtil.getDbServerDateMediumFormat(Locale.JAPAN);
//        System.out.println("中間形式の日付: " + mediumFormat);
//
//        String timestampFormat = ServerDateUtil.getDbServerDateTimestampFormat(Locale.JAPAN);
//        System.out.println("フォーマットされたタイムスタンプ: " + timestampFormat);
//
//        String ymdFormat = ServerDateUtil.getDbServerDateYMD();
//        System.out.println("yyyyMMdd形式の日付: " + ymdFormat);
//
//        String ymdhmsFormat = ServerDateUtil.getDbServerDateYMDHMS();
//        System.out.println("yyyyMMddHHmmss形式の日時: " + ymdhmsFormat);
//    }
}