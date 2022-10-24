package by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class BDConnector {
    private final HikariDataSource hikariDataSource;

    public BDConnector(String fileConfig) {
        HikariConfig hikariConfig = new HikariConfig(fileConfig);
        this.hikariDataSource = new HikariDataSource(hikariConfig);
    }

    public Connection getConnection() throws SQLException {
        try {
            return hikariDataSource.getConnection();
        } catch (Exception e) {
            throw new SQLException(e);
        }
    }
}
