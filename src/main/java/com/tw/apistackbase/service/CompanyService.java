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

    public Company findById(Integer companyId) {
        return companyRepository.findByName(companyId);
    }

    public List<Employee> findEmployeesByCompanyId(Integer companyId) {
        return companyRepository.findEmployeesByCompanyName(companyId);
    }

    public List<Company> findByPage(Integer page, Integer pageSize) {
        return companyRepository.findByPage(page, pageSize);
    }

    public Company save(Company company) {
        return companyRepository.save(company);
    }

    public Company update(Integer companyId, Company company) {
        return companyRepository.update(companyId, company);
    }

    public Company deleteAllEmployee(Integer companyId) {
        return companyRepository.deleteAllEmployee(companyId);
    }

}
