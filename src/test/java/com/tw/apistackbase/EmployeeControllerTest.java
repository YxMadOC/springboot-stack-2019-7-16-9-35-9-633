package com.tw.apistackbase;

import com.tw.apistackbase.controller.EmployeeController;
import com.tw.apistackbase.model.Employee;
import com.tw.apistackbase.service.EmployeeService;
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
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(EmployeeController.class)
class EmployeeControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private EmployeeService employeeService;

    @Test
    void should_return_all_company() throws Exception {
        Employee employee = new Employee(1, 1, "Cayde", 22, "Male", 20000);
        Employee employee1 = new Employee(2, 1, "Kimr", 22, "Male", 20000);
        when(employeeService.findAll()).thenReturn(Arrays.asList(employee, employee1));

        ResultActions result = mvc.perform(get("/employees"));

        result.andExpect(status().isOk()).andExpect(jsonPath("$.[1].name", is("Kimr")));
    }

    @Test
    void should_return_employees_with_specify_gender() throws Exception {
        Employee employee = new Employee(1, 1, "Cayde", 22, "Male", 20000);
        when(employeeService.findByGender(anyString())).thenReturn(Collections.singletonList(employee));

        ResultActions result = mvc.perform(get("/employees?gender=Male"));

        result.andExpect(status().isOk()).andExpect(jsonPath("$.[0].employeeId", is(1)));
    }

    @Test
    void should_return_specify_employee() throws Exception {
        Employee employee = new Employee(1, 1, "Cayde", 22, "Male", 20000);
        when(employeeService.findById(anyInt())).thenReturn(employee);

        ResultActions result = mvc.perform(get("/employees/{employeeId}", 1));

        result.andExpect(status().isOk()).andExpect(jsonPath("$.employeeId", is(1)));
    }

    @Test
    void should_return_employee_pages() throws Exception {
        Employee employee = new Employee(1, 1, "Cayde", 22, "Male", 20000);
        Employee employee1 = new Employee(2, 1, "Waylon", 22, "Male", 20000);
        Employee employee2 = new Employee(3, 1, "Penny", 22, "Male", 20000);
        Employee employee3 = new Employee(4, 1, "Kimr", 22, "Male", 20000);

        List<Employee> employees = Arrays.asList(employee, employee1, employee2, employee3);
        when(employeeService.findByPage(anyInt(), anyInt())).thenReturn(employees);

        ResultActions result = mvc.perform(get("/employees?page=1&pageSize=5"));

        result.andExpect(status().isOk()).andExpect(jsonPath("$.[3].employeeId", is(4)));
    }

    @Test
    void should_return_company_after_saving_company() throws Exception {
        Employee employee = new Employee(1, 1, "Cayde", 22, "Male", 20000);
        when(employeeService.save(ArgumentMatchers.any())).thenReturn(employee);

        ResultActions result = mvc.perform(post("/employees").content("{\"companyId\": 1, \"companyName\": \"OOCL\"}").contentType(MediaType.APPLICATION_JSON));

        result.andExpect(status().isOk()).andExpect(jsonPath("$.employeeId", is(1)));
    }

    @Test
    void should_return_company_after_updating_company() throws Exception {
        Employee employee = new Employee(1, 1, "Cayde", 22, "Male", 20000);
        when(employeeService.update(anyInt(), ArgumentMatchers.any())).thenReturn(employee);

        ResultActions result = mvc.perform(put("/employees/{employeeId}", 1).content("{\"companyId\": 1, \"companyName\": \"OOCL\"}").contentType(MediaType.APPLICATION_JSON));

        result.andExpect(status().isOk()).andExpect(jsonPath("$.employeeId", is(1)));
    }

    @Test
    void should_return_employee_after_deleted_employee() throws Exception {
        Employee employee = new Employee(1, 1, "Cayde", 22, "Male", 20000);

        when(employeeService.delete(anyInt())).thenReturn(employee);

        ResultActions result = mvc.perform(delete("/employees/{employeeId}", 1));

        result.andExpect(status().isOk()).andExpect(jsonPath("$.employeeId", is(1)));
    }

}
