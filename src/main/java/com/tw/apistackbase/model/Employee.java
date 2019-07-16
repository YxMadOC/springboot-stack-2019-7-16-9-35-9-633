package com.tw.apistackbase.model;

import java.util.ArrayList;
import java.util.List;

public class Employee {

    private static final List<Employee> employeeTable = new ArrayList<>();
    private Integer employeeId;
    private String name;
    private Integer age;
    private String gender;
    private Integer salary;
    private Integer companyId;

    public Employee() {
    }

    public Employee(Integer employeeId, Integer companyId, String name, Integer age, String gender, Integer salary) {
        this.employeeId = employeeId;
        this.companyId = companyId;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.salary = salary;
        employeeTable.add(this);
    }

    public static List<Employee> getEmployeeTable() {
        return employeeTable;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }
}
