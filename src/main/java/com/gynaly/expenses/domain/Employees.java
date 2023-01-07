package com.gynaly.expenses.domain;

import com.gynaly.expenses.exceptions.EmployeeNotFouindException;

import java.util.List;

public interface Employees {
    void addEmployee(Employee employee);

    List<Employee> getEmployeeList();

    void printEmployee();

    Employee findBySurname(String surname);

    Employee findById(int id);

    boolean employeeExists(int id);

    void addExpenseClaim(ExpenseClaim claim) throws EmployeeNotFouindException;
}
