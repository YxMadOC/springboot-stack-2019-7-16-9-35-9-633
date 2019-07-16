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

    @GetMapping("/companies/{companyId}")
    public Company findById(@PathVariable Integer companyId) {
        return companyService.findById(companyId);
    }

    @GetMapping(value = "/companies/{companyId}/employees")
    public List<Employee> findEmployeesByCompanyName(@PathVariable Integer companyId) {
        return companyService.findEmployeesByCompanyId(companyId);
    }

    @GetMapping(value = "/companies", params = {"page", "pageSize"})
    public List<Company> findByPage(@RequestParam Integer page, @RequestParam Integer pageSize) {
        return companyService.findByPage(page, pageSize);
    }

    @PostMapping("/companies")
    public Company save(@RequestBody Company company) {
        return companyService.save(company);
    }

    @PutMapping("/companies/{companyId}")
    public Company update(@PathVariable Integer companyId, @RequestBody Company company) {
        return companyService.update(companyId, company);
    }

    @DeleteMapping("/companies/{companyId}/employees")
    public Company deleteAllEmployee(@PathVariable Integer companyId) {
        return companyService.deleteAllEmployee(companyId);
    }

}
