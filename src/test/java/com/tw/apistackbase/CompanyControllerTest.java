package com.tw.apistackbase;

import com.tw.apistackbase.controller.CompanyController;
import com.tw.apistackbase.model.Company;
import com.tw.apistackbase.model.Employee;
import com.tw.apistackbase.service.CompanyService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(CompanyController.class)
class CompanyControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private CompanyService companyService;

    @Test
    void should_return_all_company() throws Exception {
        when(companyService.findAll()).thenReturn(Arrays.asList(new Company(1, "OOCL"), new Company(2, "ThoughtWorks")));

        ResultActions result = mvc.perform(get("/companies"));

        result.andExpect(status().isOk()).andExpect(jsonPath("$.[0].companyName", is("OOCL"))).andExpect(jsonPath("$.[1].companyName", is("ThoughtWorks")));
    }

    @Test
    void should_return_specify_company() throws Exception {
        when(companyService.findById(anyInt())).thenReturn(new Company(1, "OOCL"));

        ResultActions result = mvc.perform(get("/companies/{companyId}", 1));

        result.andExpect(status().isOk()).andExpect(jsonPath("$.companyId", is(1)));
    }

    @Test
    void should_return_specify_company_employees() throws Exception {
        Employee employee = new Employee(1, 1, "Cayde", 22, "Male", 20000);
        when(companyService.findEmployeesByCompanyId(anyInt())).thenReturn(Collections.singletonList(employee));

        ResultActions result = mvc.perform(get("/companies/{companyId}/employees", 1));

        result.andExpect(status().isOk()).andExpect(jsonPath("$.[0].name", is("Cayde")));
    }

    @Test
    void should_return_company_pages() throws Exception {
        Company company = new Company(1, "OOCL");
        Company company1 = new Company(2, "OOCL");
        Company company2 = new Company(3, "OOCL");
        Company company3 = new Company(4, "OOCL");
        List<Company> companies = Arrays.asList(company, company1, company2, company3);
        when(companyService.findByPage(anyInt(), anyInt())).thenReturn(companies);

        ResultActions result = mvc.perform(get("/companies?page=1&pageSize=5"));

        result.andExpect(status().isOk()).andExpect(jsonPath("$.[3].companyId", is(4)));
    }

    @Test
    void should_return_company_after_saving_company() throws Exception {
        Company company = new Company(1, "OOCL");
        when(companyService.save(ArgumentMatchers.any())).thenReturn(company);

        ResultActions result = mvc.perform(post("/companies").content("{\"companyId\": 1, \"companyName\": \"OOCL\"}").contentType(MediaType.APPLICATION_JSON));

        result.andExpect(status().isOk()).andExpect(jsonPath("$.companyId", is(1)));
    }

    @Test
    void should_return_company_after_updating_company() throws Exception {
        Company company = new Company(1, "OOCL");
        when(companyService.update(anyInt(), ArgumentMatchers.any())).thenReturn(company);

        ResultActions result = mvc.perform(put("/companies/{companyId}", 1).content("{\"companyId\": 1, \"companyName\": \"OOCL\"}").contentType(MediaType.APPLICATION_JSON));

        result.andExpect(status().isOk()).andExpect(jsonPath("$.companyId", is(1)));
    }

    @Test
    void should_return_company_after_deleted_all_employees() throws Exception {
        Company company = new Company(1, "OOCL");

        when(companyService.deleteAllEmployee(anyInt())).thenReturn(company);

        ResultActions result = mvc.perform(delete("/companies/{companyId}/employees", 1));

        result.andExpect(status().isOk()).andExpect(jsonPath("$.companyId", is(1)));
    }


}
