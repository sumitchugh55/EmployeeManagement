package com.gynaly.expenses.ui;

import com.gynaly.expenses.domain.*;
import com.gynaly.expenses.exceptions.InvalidEmployeeIdException;
import com.gynaly.expenses.exceptions.NameTooShortException;
import com.gynaly.expenses.utility.EmployeeUtilities;

import java.time.LocalDate;
import java.util.Scanner;

public class UIFuncation {

    public Employee registerNewEmployee() {
        Scanner scanner = new Scanner(System.in);
        Employee employee = new Employee();

        EmployeeUtilities employeeUtilities = new EmployeeUtilities();

        boolean idIsValid = false;

        while (!idIsValid) {
            System.out.println("Enter the id");
            String inputId = scanner.nextLine();
            try {
                Integer id = employeeUtilities.validateEmployeeId(inputId);
                employee.setId(id);
                idIsValid = true;
            } catch (InvalidEmployeeIdException e) {
                System.out.println("Not a valid Employee ID Please Try - Again !");
            }
        }

        System.out.println("Enter The title");
        String title = scanner.nextLine();
        employee.setTitle(title);

        boolean nameIsValid = false;
        while (!nameIsValid) {
            System.out.println("Enter The First Name");
            String firstName = scanner.nextLine();
            employee.setFirstName(firstName);

            System.out.println("Enter The surname");
            String surname = scanner.nextLine();
            employee.setSurname(surname);
            try {
                employeeUtilities.validateEmployeeName(firstName,surname);
                nameIsValid = true;
            } catch (NameTooShortException e) {
                System.out.println("The name you have entred was not Valid - try Again");
            }
        }

        System.out.println("Enter The Job Title");
        String jobTitle = scanner.nextLine();
        employee.setJobTitle(jobTitle);

        boolean departmentIsvalid = false;
        while (!departmentIsvalid) {
            System.out.println("Enter The Department");
            String department = scanner.nextLine();
            try {
                Department d = Department.valueOf(department.toUpperCase());
                employee.setDepartement(d);
                departmentIsvalid = true;
            }catch (IllegalArgumentException e){
                System.out.println("Please enter a valid department");
            }
        }

        System.out.println("Is this member of Staff ? Y/N");
        String isAStaffMember = scanner.nextLine();
        if (isAStaffMember.toUpperCase().equals("Y")){
            StaffEmployee staff = new StaffEmployee(employee);

            System.out.println("Enter The username");
            String username = scanner.nextLine();
            staff.setUsername(username);

            System.out.println("Enter The password");
            String password = scanner.nextLine();
            staff.setPassword(password);
            return staff;
        }
        else {

            return (employee);
        }
    }

    public ExpenseClaim registerNewExpenseClaim(){
            Scanner scanner = new Scanner(System.in);
            EmployeeUtilities employeeUtilities = new EmployeeUtilities();

            System.out.println("Enter The Claim Id");
            int claimId = scanner.nextInt();
            scanner.nextLine();

            System.out.println("Enter The Employee Id");
            int employeeId = scanner.nextInt();
            scanner.nextLine();

            LocalDate dateOfClaim = LocalDate.now();

            System.out.println("Enter The ammout");
            double totalAmmout = scanner.nextDouble();
            scanner.nextLine();
            ExpenseClaim claim = new ExpenseClaim(claimId,employeeId,dateOfClaim);

            boolean finished = false;
            while (!finished){

                System.out.println("Enter The expense item Id");
                int eiId = scanner.nextInt();
                scanner.nextLine();

                boolean expenseTypeIsValid = false;
                ExpenseType eiType =  null;

                while (!expenseTypeIsValid){

                    System.out.println("Entre The expense Type");
                    String expenseType = scanner.nextLine();
                    try{
                        eiType = ExpenseType.valueOf(expenseType.toUpperCase());
                        expenseTypeIsValid = true;

                    }catch (IllegalArgumentException e){
                        System.out.println("The Department is not valid -- try again");
                    }
                }
                System.out.println("Enter the description");
                String description = scanner.nextLine();

                System.out.println("Enter the amount");
                Double amount = scanner.nextDouble();
                scanner.nextLine();

                ExpenseItem ei = new ExpenseItem(eiId,claimId,eiType,description,amount);
                claim.addExpenseItem(ei);

                System.out.println("Entre another expense item ? Y / N");
                String anyMore = scanner.nextLine();

                if(!anyMore.toUpperCase().equals("Y")){
                    finished = true;
                }
            }




            return claim;
    }
}
