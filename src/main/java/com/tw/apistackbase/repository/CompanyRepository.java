package com.tw.apistackbase.repository;

import com.tw.apistackbase.model.Company;
import com.tw.apistackbase.model.Employee;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class CompanyRepository {

    private static Map<String, Company> companies = new HashMap<>();

    static {
        companies.put("OOCL", new Company("OOCL", 400,
                new Employee(UUID.randomUUID().toString(), "CAYDE", 20, "Male", 15000),
                new Employee(UUID.randomUUID().toString(), "PENNY", 20, "Male", 15000),
                new Employee(UUID.randomUUID().toString(), "REET", 20, "Male", 15000),
                new Employee(UUID.randomUUID().toString(), "WAYLON", 20, "Male", 15000)
        ));
    }

    public List<Company> findAll() {
        return new ArrayList<>(companies.values());
    }

    public Company findByName(String companyName) {
        return companies.get(companyName);
    }

    public List<Employee> findEmployeesByCompanyName(String companyName) {
        return companies.get(companyName).getEmployees();
    }

    public List<Company> findByPage(Integer page, Integer pageSize) {
        int startIndex = (page - 1) * pageSize;
        int endIndex = startIndex + pageSize;
        return new ArrayList<>(companies.values()).subList(startIndex, endIndex);
    }

    public Company save(Company company) {
        return companies.put(company.getCompanyName(), company);
    }

    public Company update(String companyName, Company company) {
        return companies.put(companyName, company);
    }

    public Company deleteAllEmployee(String companyName) {
        Company company = companies.get(companyName);
        company.getEmployees().clear();
        return companies.put(companyName, company);
    }

}
