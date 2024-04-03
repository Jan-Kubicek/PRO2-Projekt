package org.example.pro2projekt.controller;

import java.sql.*;

public class Database {
    private String connectionUrl = "jdbc:mariadb://localhost:3307/mojLachtan";
    private String username = "kubicja";
    private String password = "heslo";

    public Database() {

    }

    public ResultSet getData(String query){
        try(Connection connection = DriverManager.getConnection(connectionUrl,username,password)) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            return resultSet;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void newEntity(String query){
        try(Connection connection = DriverManager.getConnection(connectionUrl,username,password)){
            Statement statement = connection.createStatement();
            statement.executeQuery(query);
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}