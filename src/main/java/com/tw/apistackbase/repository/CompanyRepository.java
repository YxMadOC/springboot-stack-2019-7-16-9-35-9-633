package com.tw.apistackbase.repository;

import com.tw.apistackbase.model.Company;
import com.tw.apistackbase.model.Employee;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class CompanyRepository {

    private static Map<Integer, Company> companies = new HashMap<>();
    private static int currentIndex = 2;

    {
        companies.put(1, new Company(1, "OOCL"));
    }

    public List<Company> findAll() {
        return new ArrayList<>(companies.values());
    }

    public Company findByName(Integer companyId) {
        return companies.get(companyId);
    }

    public List<Employee> findEmployeesByCompanyName(Integer companyId) {
        return companies.get(companyId).getEmployees();
    }

    public List<Company> findByPage(Integer page, Integer pageSize) {
        int startIndex = (page - 1) * pageSize;
        int endIndex = startIndex + pageSize;
        return new ArrayList<>(companies.values()).subList(startIndex, endIndex);
    }

    public Company save(Company company) {
        company.setCompanyId(currentIndex);
        companies.put(currentIndex++, company);
        return companies.get(company.getCompanyId());
    }

    public Company update(Integer companyId, Company company) {
        return companies.put(companyId, company);
    }

    public Company deleteAllEmployee(Integer companyId) {
        Company company = companies.get(companyId);
        company.getEmployees().clear();
        return companies.put(companyId, company);
    }

}
