package com.gynaly.expenses;

import com.gynaly.expenses.domain.*;
import com.gynaly.expenses.exceptions.EmployeeNotFouindException;
import com.gynaly.expenses.management.ExpenseManagementProcess;
import com.gynaly.expenses.management.ExpressExpenseManagementProcess;
import com.gynaly.expenses.management.RegularExpenseManagementProcess;
import com.gynaly.expenses.ui.UIFuncation;
import com.gynaly.expenses.utility.ExpenseAnalysis;
import com.gynaly.expenses.utility.ExpenseAnalysisImpl;

import java.time.LocalDate;
import java.util.Scanner;

public class ExpenseManagementSystem {

    public static void main(String[] args) throws ClassNotFoundException {
        Employees employees = new EmployeeDatabaseImpl();
        Scanner scanner = new Scanner(System.in);
        UIFuncation uiFunctions = new UIFuncation();

        ExpenseManagementProcess expressEMP = new ExpressExpenseManagementProcess();
        ExpenseManagementProcess regularEMP = new RegularExpenseManagementProcess();

        boolean readyToExit = false;

        while(!readyToExit) {

            System.out.println("Expense Management System");
            System.out.println("-------------------------");
            System.out.println("e - register new employee");
            System.out.println("c - register new claim");
            System.out.println("p - print all employees");

            System.out.println("a - approve claims");
            System.out.println("r1 - outstanding expense claim");
            System.out.println("r2 - paid expense claim");
            System.out.println("r3 - expense claims above specified amount");
            System.out.println("x - exit");

            String option = scanner.nextLine();

//            ExpenseAnalysis expenseAnalysis = new ExpenseAnalysisImpl(employees);

            switch (option) {
                case "e":
                    Employee e = uiFunctions.registerNewEmployee();
                    employees.addEmployee(e);
                    break;
                case "c":
                    ExpenseClaim claim = uiFunctions.registerNewExpenseClaim();
                    try {
                        employees.addExpenseClaim(claim);
                        expressEMP.registerExpenseClaim(claim);
                        int id = regularEMP.registerExpenseClaim(claim);
                        System.out.println("The claim has been registered with ID " + id);
                    } catch (EmployeeNotFouindException employeeNotFoundException) {
                        System.out.println("There was no employee with ID " + claim.getEmployeeId());
                    }
                    break;
                case "p":
                    employees.printEmployee();
                    break;
                case "x": //exit
                    readyToExit = true;
                    break;
                case "a":
//                    get the ID of the claim
                    System.out.println("Enter The claim Id");
                    int claimId = scanner.nextInt();
                    scanner.nextLine();


//                    get the employee ID
                    System.out.println("Enter The employee Id");
                    int employeeId = scanner.nextInt();
                    scanner.nextLine();

                    Employee foundEmployee = employees.findById(employeeId);
                    System.out.println("Enter r for Regular , or e for Express");
                    String claimType = scanner.nextLine();

                    ExpenseManagementProcess requestProcess;

                    if(claimType.equals("r")){
                        requestProcess = regularEMP;
                    }else {
                        requestProcess = expressEMP;
                    }

                    boolean result = requestProcess.approveClaim(claimId,foundEmployee);
                    System.out.println("The result was " + result);
                    break;

                case "r1":
//                    expenseAnalysis.printOutStandingClaims();

                case "r2":
                    System.out.println("Entre date from ");
                    String dateFrom = scanner.nextLine();

                    System.out.println("Entre date TO ");
                    String dateTo = scanner.nextLine();
//                    expenseAnalysis.printPaidClaim(LocalDate.parse(dateFrom),LocalDate.parse(dateTo));
                case "r3":
                    System.out.println("Entre date amount");
                    Double amount = scanner.nextDouble();
                    scanner.nextLine();
//                    expenseAnalysis.printClaimsOverAmount(amount);
                default:
                    System.out.println("Option not valid");
            }
        }
    }
}
