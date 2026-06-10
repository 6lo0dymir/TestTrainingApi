package dataBase;

import io.qameta.allure.*;
import org.slf4j.*;

import java.io.*;
import java.sql.*;
import java.util.*;


public class DataBaseConnector {
    private static final Logger logger = LoggerFactory.getLogger(DataBaseConnector.class);

    @Step("Подключение к базе данных {dbName}")
    public static Connection getConnection (String dbName) throws SQLException, IOException {
        logger.info("Подключаемся к базе {}", dbName);
        Properties ps = new Properties();
        try{
            ps.load(new FileInputStream("src/test/java/dataBase/db.config"));
            String host = System.getenv().getOrDefault("DB_HOST", ps.getProperty("db.host"));
            String port = System.getenv().getOrDefault("DB_PORT", ps.getProperty("db.port"));
            String user = System.getenv().getOrDefault("DB_USER", ps.getProperty("db.user"));
            String password = System.getenv().getOrDefault("DB_PASSWORD", ps.getProperty("db.password"));
            String url = "jdbc:postgresql://" + host + ":" + port + "/" + dbName;
            System.out.println(url);
            return DriverManager.getConnection(url, user, password);
        }catch (SQLException | IOException e){
            throw new RuntimeException("Ошибка подключения к базе", e);
        }
    }
}
