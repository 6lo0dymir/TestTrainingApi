package dataBase.test;

import dataBase.*;
import io.qameta.allure.*;
import org.junit.jupiter.api.*;
import org.slf4j.*;

import java.io.*;
import java.sql.*;

import static dataBase.DBList.connection;

@Epic("Authentication Service")
@Feature("DB Connection")
@Owner("Терехин Владимир")
@DisplayName("Смоук тест: Подключение к БД")
public class SimpleSQLRequests extends BaseDBTest {
    private static final Logger logger = LoggerFactory.getLogger(SimpleSQLRequests.class);

    @DisplayName("Проверка подключения к базе данных")
    @Test
    public void authenticationServiceConnectionTest() throws SQLException, IOException {
        try (Connection conn = connection(DBList.Database.DB_AUTHENTICATION_SERVICE_TEST);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT now()")) {

            if (rs.next()) {
                String currTime = rs.getString(1);
                System.out.println("Текущая дата и время:" + currTime);
                logger.info("Текущая дата и время из БД: {}", currTime);
            }
        }

    }
    @DisplayName("Получение UID по email")
    @Test
    public void getUIDByEmail() throws SQLException, IOException{
        try (Connection conn = connection(DBList.Database.DB_AUTHENTICATION_SERVICE_TEST);
        Statement stmt = conn.createStatement();
        ResultSet rst = stmt.executeQuery("SELECT id FROM client WHERE email = 'a.petrov@mail.ru'")){

            if (rst.next()){
                String uID = rst.getString(1);
                System.out.println("UUID: " + uID);
            }


        }
    }

    @DisplayName("Отображение названия и описания всех актуальных карточных продуктов (дебет)")
    @Test
    public void getListOfActualDebetCards() throws SQLException, IOException{
        try (Connection conn = connection(DBList.Database.DB_CARD_SERVICE_TEST);
             Statement stmt = conn.createStatement();
             ResultSet rst = stmt.executeQuery("SELECT name, description " +
                     "FROM product " +
                     "WHERE status IS true ")){
            while (rst.next()){
                String name = rst.getString("name");
                String description = rst.getString("description");
                System.out.println(name + " - " + description);
            }
        }
    }
    @DisplayName("Получение списка активных счетов по типу (Debit, Credit, Deposit")
    @Test
    public void getListOfActualAccountsByType() throws SQLException, IOException{
        try (Connection conn = connection(DBList.Database.DB_ACCOUNT_SERVICE_TEST);
        Statement stmt = conn.createStatement();
        ResultSet rst = stmt.executeQuery("SELECT account_number, type " +
                "FROM account a " +
                "JOIN type_account ta ON a.type_account_id = ta.id " +
                "WHERE a.account_status = 'ACTIVE'")){
            while (rst.next()){
                String acc_number = rst.getString("account_number");
                String type = rst.getString("type");
                System.out.println("Номер счета - " + acc_number + " Тип счета - " + type);
            }
        }
    }
}
