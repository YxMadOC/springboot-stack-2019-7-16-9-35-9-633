package com.tw.apistackbase.controller;

import com.tw.apistackbase.model.Company;
import com.tw.apistackbase.model.Employee;
import com.tw.apistackbase.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @GetMapping("/companies")
    public List<Company> findAll() {
        return companyService.findAll();
    }

    @GetMapping("/companies/{companyName}")
    public Company findByName(@PathVariable String companyName) {
        return companyService.findByName(companyName);
    }

    @GetMapping(value = "/companies/{companyName}/employees")
    public List<Employee> findEmployeesByCompanyName(@PathVariable String companyName) {
        return companyService.findEmployeesByCompanyName(companyName);
    }

    @GetMapping(value = "/companies", params = {"page", "pageSize"})
    public List<Company> findByPage(@RequestParam Integer page, @RequestParam Integer pageSize) {
        return companyService.findByPage(page, pageSize);
    }

    @PostMapping("/companies")
    public Company save(Company company) {
        return companyService.save(company);
    }

    @PutMapping("/companies")
    public Company update(String companyName, Company company) {
        return companyService.update(companyName, company);
    }

    @DeleteMapping("/companies")
    public Company deleteAllEmployee(String companyName) {
        return companyService.deleteAllEmployee(companyName);
    }

}
