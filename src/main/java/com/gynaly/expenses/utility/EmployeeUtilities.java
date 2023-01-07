package com.gynaly.expenses.utility;


import com.gynaly.expenses.domain.Employee;
import com.gynaly.expenses.domain.EmployeesInMemoryImpl;
import com.gynaly.expenses.exceptions.InvalidEmployeeIdException;
import com.gynaly.expenses.exceptions.NameTooShortException;

public class EmployeeUtilities {
    public boolean employeeExists(EmployeesInMemoryImpl employees, Employee employee){
        return employees.findBySurname(employee.getSurname()) != null;
    }

    public Integer validateEmployeeId(String inputId) throws InvalidEmployeeIdException {
       try {
           Integer id = Integer.valueOf(inputId);
           return id;
       }catch (NumberFormatException e){
           throw new InvalidEmployeeIdException();
       }
    }

    public void validateEmployeeName(String firstName, String surname) throws NameTooShortException {
       Integer length = firstName.length() + surname.length();
       if(length < 6){
           throw new NameTooShortException();
       }

    }

}
