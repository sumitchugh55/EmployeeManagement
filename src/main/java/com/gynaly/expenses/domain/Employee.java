package com.gynaly.expenses.domain;

import java.util.*;

public class Employee implements Comparable<Employee>{
    private int id;
    private String title;
    private String firstName;
    private String surname;
    private String jobTitle;
    private Department departement;

    private Map<Integer,ExpenseClaim> claims = new HashMap<>();


    public Employee(){

    }

//    public Employee(String title,String firstName, String surname){
//        this.title = title;
//        this.firstName = firstName;
//        this.surname = surname;
//
//    }

    public Employee(int id, String title, String firstName, String surname, String jobTitle, Department departement) {
        this.id = id;
        this.title = title;
        this.firstName = firstName;
        this.surname = surname;
        this.jobTitle = jobTitle;
        this.departement = departement;

    }


    public String getMailingName(){
        return title + " "+ firstName + " " + surname;
    }
//   Mr. chandra vishwakram
//    Mr. C Vishwakarma
//    Mr. Vishwakrma

    public String getMailingName(boolean firstInitialOnly){

        if (firstInitialOnly){
            return title + " " + firstName.substring(0,1) + " " + surname;
        }else {
            return title + " " + surname;
        }
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
//        if(firstName.length() < 2){
//            System.out.println("Error - First Name Must be at least 2 Char");
//        }else {
//            this.firstName = firstName;
//        }
        this.firstName = firstName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public Department getDepartement() {
        return departement;
    }

    public void setDepartement(Department departement) {
        this.departement = departement;
    }

    public Map<Integer,ExpenseClaim> getClaims() {
        return claims;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", firstName='" + firstName + '\'' +
                ", surname='" + surname + '\'' +
                ", jobTitle='" + jobTitle + '\'' +
                ", departement=" + departement +
                ", claims=" + claims +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employee)) return false;
        Employee employee = (Employee) o;
        return id == employee.id && Objects.equals(title, employee.title) && Objects.equals(firstName, employee.firstName) && Objects.equals(surname, employee.surname) && Objects.equals(jobTitle, employee.jobTitle) && departement == employee.departement && Objects.equals(claims, employee.claims);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, firstName, surname, jobTitle, departement, claims);
    }

    @Override
    public int compareTo(Employee o) {
        return this.surname.compareTo(o.getSurname());
    }

    public Department getDepartment(Department department) {
        return department;
    }
}
