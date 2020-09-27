package com.mczen.api.repository;

import com.mczen.api.model.LoginEvent;
import com.mczen.api.model.LoginEventType;
import org.apache.log4j.Logger;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class LoginEventRepository {

    private final Logger log = Logger.getLogger(LoginEventRepository.class);
    private String url = "jdbc:h2:tcp://localhost:9092/~/tmp/h2dbs/mczendb";
    private String driver = "org.h2.Driver";
    private String user = "sa";
    private String password = "sa";
    private Statement statement;

    public LoginEventRepository() {
        if (this.statement == null) {
            this.initDbConnection();
        }
    }

    public List<LoginEvent> getAll() {
        List<LoginEvent> events = new ArrayList<>();
        try {
            final ResultSet resultSet = this.statement.executeQuery("SELECT * FROM LOGINLOGS");
            while (resultSet.next()) {
                events.add(toEvent(resultSet));
            }
        } catch (SQLException ex) {
            log.error("Error while reading login-logs {}", ex);
        }
        return events;
    }

    private LoginEvent toEvent(ResultSet resultSet) throws SQLException {
        return new LoginEvent(
                resultSet.getString("USER"),
                LoginEventType.valueOf(resultSet.getString("ACTION")),
                resultSet.getObject("LOG_DATE", LocalDateTime.class));
    }

    private void initDbConnection() {
        try {
            Class.forName(driver);
            Connection con = DriverManager.getConnection(url, user, password);
            this.statement = con.createStatement();
        } catch (SQLException | ClassNotFoundException ex) {
            log.error("Error while connection to db {}", ex);
        }
    }
}
