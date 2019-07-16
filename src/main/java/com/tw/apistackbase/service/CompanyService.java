package com.tw.apistackbase.service;

import com.tw.apistackbase.model.Company;
import com.tw.apistackbase.model.Employee;
import com.tw.apistackbase.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    public List<Company> findAll() {
        return companyRepository.findAll();
    }

    public Company findByName(String companyName) {
        return companyRepository.findByName(companyName);
    }

    public List<Employee> findEmployeesByCompanyName(String companyName) {
        return companyRepository.findEmployeesByCompanyName(companyName);
    }

    public List<Company> findByPage(Integer page, Integer pageSize) {
        return companyRepository.findByPage(page, pageSize);
    }

    public Company save(Company company) {
        return companyRepository.save(company);
    }

    public Company update(String companyName, Company company) {
        return companyRepository.update(companyName, company);
    }

    public Company deleteAllEmployee(String companyName) {
        return companyRepository.deleteAllEmployee(companyName);
    }

}
