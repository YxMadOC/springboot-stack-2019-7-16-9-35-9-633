package com.tw.apistackbase.model;

import com.tw.apistackbase.repository.EmployeeRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Company {

    private Integer companyId;
    private String companyName;
    private Integer employeesNumber;
    private List<Employee> employees;

    public Company() {
    }

    public Company(Integer companyId, String companyName) {
        this.companyId = companyId;
        this.companyName = companyName;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Integer getEmployeesNumber() {
        return (int) new ArrayList<>(EmployeeRepository.getEmployees().values()).stream().filter(employee -> this.companyId.equals(employee.getCompanyId())).count();
    }

    public List<Employee> getEmployees() {
        return new ArrayList<>(EmployeeRepository.getEmployees().values()).stream().filter(employee -> this.companyId.equals(employee.getCompanyId())).collect(Collectors.toList());
    }

}
