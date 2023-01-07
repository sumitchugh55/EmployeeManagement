package com.gynaly.expenses.utility;

import java.time.LocalDate;

public class ExpenseAnalysisTempImpl implements ExpenseAnalysis{
    @Override
    public void printOutStandingClaims() {
        System.out.println("This feature is not currently available");
    }

    @Override
    public void printPaidClaim(LocalDate from, LocalDate to) {
        System.out.println("This feature is not currently available");

    }

    @Override
    public void printClaimsOverAmount(Double amount) {
        System.out.println("This feature is not currently available");
    }
}
