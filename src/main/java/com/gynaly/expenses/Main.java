package com.gynaly.expenses;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gynaly.expenses.domain.*;

import java.sql.*;

public class Main {
    public static void main(String[] args) throws JsonProcessingException, ClassNotFoundException, SQLException {

        ExpenseItem expenseItem = new ExpenseItem(1234,12345678, ExpenseType.MEAL,"To stay for the meeting",40.45);
        System.out.println(expenseItem.getDescription());

        Employee employee1 = new Employee(123,"Mr.","Gyan","Vishwakarma","Manager", Department.FINANCE);
//        Employee employee2 = new Employee(123,"Mrs.","Gyan","Vishwakarma","Manager",Department.FINANCE);
//        Employee employee3 = new Employee("Mr.","Vivek","Xyz");

        EmployeesInMemoryImpl employees = new EmployeesInMemoryImpl();

//        System.out.println(employee1);

//        ExpenseClaim expenseClaim = new ExpenseClaim(24,643, LocalDate.now());
//        System.out.println(expenseClaim);
//        String name1 = "gyan";
//        String name2 = "gyan";
//        System.out.println(employee1.equals(employee2));
//
//        employees.addEmployee(employee1);
//        employees.addEmployee(employee2);
//        employees.addEmployee(employee3);
//
//        employees.printEmployee();
//        Employee abc =  employees.findBySurname("hjgghj");
//        System.out.println("Did Not find "+ (abc == null));

        System.out.println(employee1);

        ObjectMapper objectMapper = new ObjectMapper();
        String employeeJSON = objectMapper.writeValueAsString(employee1);
        System.out.println(employeeJSON);
//        Class.forName("com.mysql.cj.jdbc.Driver")
        Class.forName("org.h2.Driver");
        Connection connection = DriverManager.getConnection("jdbc:h2:./customerdata","sa","");
        Statement statement = connection.createStatement();
//        statement.executeUpdate("CREATE TABLE customer (id INTEGER , name VARCHAR (255),age INTEGER, PRIMARY KEY (id))");
//        statement.executeUpdate("INSERT INTO customer(id,name,age) VALUES (1,'Gyna',29)");
//        statement.executeUpdate("INSERT INTO customer(id,name,age) VALUES (2,'Akash',21)");
        ResultSet rs = statement.executeQuery("SELECT * FROM customer");
        while (rs.next()){
            System.out.println(rs.getInt("id"));
            System.out.println(rs.getString("name"));
            System.out.println(rs.getInt("age"));
        }

        connection.close();
    }
}