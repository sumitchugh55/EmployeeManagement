package com.gynaly.expenses;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class SetupDatabase {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        Class.forName("org.h2.Driver");
        try(Connection connection = DriverManager.getConnection("jdbc:h2:./customerdata","sa","")){
            Statement statement = connection.createStatement();
            String sql = "CREATE TABLE employees (id INTEGER, title VARCHAR(30), firstName VARCHAR(255), surname VARCHAR(255), jobTitle VARCHAR(255), department VARCHAR(255), PRIMARY KEY (id))";
            statement.executeUpdate(sql);
        }

    }
}
