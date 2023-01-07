package com.gynaly.expenses.utility;

import com.gynaly.expenses.domain.EmployeesInMemoryImpl;

import java.time.LocalDate;

public class ExpenseAnalysisImpl implements ExpenseAnalysis{

    private EmployeesInMemoryImpl employees;

    public ExpenseAnalysisImpl(EmployeesInMemoryImpl employees){
        this.employees = employees;
    }


    @Override
    public void printOutStandingClaims() {
        employees.getEmployeeList().stream().forEach(employee->{
            employee.getClaims().values().stream()
                    .filter(claim -> !claim.getApproved())
                    .forEach(claim -> System.out.println(claim));

        });
    }

    @Override
    public void printPaidClaim(LocalDate from, LocalDate to) {
        employees.getEmployeeList().stream().forEach(employee -> {
            employee.getClaims().values().stream()
                    .filter(claim -> claim.getPaid())
                    .filter(claim -> claim.getDateOfClaim().isAfter(from) && claim.getDateOfClaim().isBefore(to))
                    .forEach(claim -> System.out.println(claim));
        });

    }

    @Override
    public void printClaimsOverAmount(Double amount) {
        employees.getEmployeeList().stream().forEach(employee -> {

            employee.getClaims().values().stream()
                    .filter(claim -> claim.getTotalAmount() >= amount)
                    .forEach(claim -> System.out.println(claim));


        });

    }
}
