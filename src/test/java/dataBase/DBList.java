package dataBase;

import io.qameta.allure.*;
import org.slf4j.*;

import java.io.*;
import java.sql.*;

public class DBList {
    private static final Logger logger = LoggerFactory.getLogger(DBList.class);

    public enum Database {
        DB_AUTHENTICATION_SERVICE_TEST ("db_authentication_service_test"),
        DB_CARD_SERVICE_TEST("db_card_service_test"),
        DB_ACCOUNT_SERVICE_TEST("db_account_service_test");


        private final String dbName;

        Database(String dbName) {
            this.dbName = dbName;
        }

        public String getDbName() {
            return dbName;
        }

    }

    @Step("Выбор и подключение к ДБ")
    public static Connection connection(Database db) throws SQLException, IOException {
        logger.info("Выбрана база: {}", db.getDbName());
        try {
            return DataBaseConnector.getConnection(db.getDbName());
        } catch (SQLException | IOException e) {
            throw new RuntimeException("Ошибка подключения к базе", e);
        }
    }

}
