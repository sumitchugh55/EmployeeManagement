package com.gynaly.expenses.utility;

import java.time.LocalDate;

public interface ExpenseAnalysis {
    public void printOutStandingClaims();
    public void printPaidClaim(LocalDate from, LocalDate to);
    public void printClaimsOverAmount(Double amount);
}
