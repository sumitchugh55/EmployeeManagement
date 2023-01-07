package com.gynaly.expenses.domain;

import com.gynaly.expenses.exceptions.EmployeeNotFouindException;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EmployeeDatabaseImpl implements Employees{

    public  EmployeeDatabaseImpl() throws ClassNotFoundException {
        Class.forName("org.h2.Driver");
    }
    @Override
    public void addEmployee(Employee employee) {

        try(Connection connection = DriverManager.getConnection("jdbc:h2:./customerdata","sa","")){
            Statement statement = connection.createStatement();
            String sql = "INSERT INTO employees (id, title, firstName, surname, jobTitle, department)" +
                    " VALUES (" + employee.getId() + ", '" + employee.getTitle() + "'," +
                    "'" + employee.getFirstName() + "', '" + employee.getSurname() + "'," +
                    "'" + employee.getJobTitle() + "', '" + employee.getDepartment(Department.FINANCE) + "')";
            statement.executeUpdate(sql);
        }catch (SQLException throwables){
            System.out.println("There was a probelm with connection to the database");
            throw new RuntimeException(throwables);
        }

    }

    @Override
    public List<Employee> getEmployeeList() {

        List<Employee> employeeList = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection("jdbc:h2:./customerdata", "sa", "")) {
            Statement statement = connection.createStatement();

            ResultSet rs = statement.executeQuery("SELECT * FROM employees");

            while (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                String firstName = rs.getString("firstName");
                String surname = rs.getString("surname");
                String jobTitle = rs.getString("jobTitle");
                Department department = Department.valueOf(rs.getString("department").toUpperCase());

                Employee e = new Employee(id,title, firstName, surname, jobTitle, department);
//                List<ExpenseClaim> claims = getExpenseClaimsForEmployee(id);
//                claims.forEach( claim -> e.addClaim(claim));
                employeeList.add(e);

            }

        }catch (SQLException throwables){
            System.out.println("There was a probelm with connection to the database");
            throw new RuntimeException(throwables);
        }

        return employeeList;
    }

    @Override
    public void printEmployee() {
        List <Employee> employeeList = getEmployeeList();
        Collections.sort(employeeList);
        for(Employee e:employeeList){
            System.out.println(e);
            for(ExpenseClaim claim: e.getClaims().values()){
                System.out.println(claim);
                claim.printExpenseItem();
                System.out.println("Total Value of the claim "+ claim.getTotalAmount());
            }
        }

    }

    @Override
    public Employee findBySurname(String surname) {
        return null;
    }

    @Override
    public Employee findById(int id) {
        return null;
    }

    @Override
    public boolean employeeExists(int id) {
        return false;
    }

    @Override
    public void addExpenseClaim(ExpenseClaim claim) throws EmployeeNotFouindException {

    }
}
