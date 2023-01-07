package com.gynaly.expenses.management;

import com.gynaly.expenses.domain.Employee;
import com.gynaly.expenses.domain.ExpenseClaim;

public interface ExpenseManagementProcess {
    public int registerExpenseClaim(ExpenseClaim claim);
    public boolean approveClaim(int id, Employee approver);
}
