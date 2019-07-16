package com.tw.apistackbase.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Company {

    private String companyName;
    private Integer employeesNumber;
    private List<Employee> employees = new ArrayList<>();

    public Company() {
    }

    public Company(String companyName, Integer employeesNumber, Employee... employees) {
        this.companyName = companyName;
        this.employeesNumber = employeesNumber;
        this.employees.addAll(Arrays.asList(employees));
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Integer getEmployeesNumber() {
        return employeesNumber;
    }

    public void setEmployeesNumber(Integer employeesNumber) {
        this.employeesNumber = employeesNumber;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }
}
