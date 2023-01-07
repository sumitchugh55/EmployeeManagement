package com.gynaly.expenses.domain;


import com.gynaly.expenses.exceptions.EmployeeNotFouindException;

import java.util.*;

public class EmployeesInMemoryImpl implements Employees {

    private Map<Integer, Employee> employees = new HashMap<>();

    @Override
    public void addEmployee(Employee employee){
       employees.put(employee.getId(),employee);
    }

    @Override
    public List<Employee> getEmployeeList(){
        return new ArrayList<Employee>(employees.values());
    }
    @Override
    public void printEmployee(){
        List <Employee> employeeList = new ArrayList<>(employees.values());
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
    public Employee findBySurname(String surname){
        for(Employee e : employees.values()){
            if(e.getSurname().equals(surname)){
                return e;
            }
        }
        return null;
    }


    @Override
    public Employee findById(int id){
        return employees.get(id);
    }

    @Override
    public boolean employeeExists(int id){
        return employees.containsKey(id);

    }

    @Override
    public void addExpenseClaim(ExpenseClaim claim) throws EmployeeNotFouindException {
        int employeeId = claim.getEmployeeId();

        if(!employeeExists(employeeId)){
            throw new EmployeeNotFouindException();
        }

//        This need to be update using Arraylist
        for(Employee e : employees.values()){
            if(e.getId() == employeeId){
                e.getClaims().put(claim.getId(), claim);
            }
        }

    }

    @Override
    public String toString() {
        return "Employees{" +
                "employees=" + employees +
                '}';
    }
}
