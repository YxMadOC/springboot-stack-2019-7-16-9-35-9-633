package com.tw.apistackbase.repository;

import com.tw.apistackbase.model.Employee;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository
public class EmployeeRepository {

    private static Map<String, Employee> employees = new HashMap<>();

    static {
        employees.put(UUID.randomUUID().toString(), new Employee(UUID.randomUUID().toString(), "CAYDE", 20, "Male", 15000));
    }

    public Employee save(Employee employee) {
        employee.setId(UUID.randomUUID().toString());
        return employees.put(employee.getId(), employee);
    }

    public List<Employee> findAll() {
        return new ArrayList<>(employees.values());
    }

    public Employee findById(String employeeId) {
        return employees.get(employeeId);
    }

    public List<Employee> findByPage(Integer page, Integer pageSize) {
        int startIndex = (page - 1) * pageSize;
        int endIndex = startIndex + pageSize;
        return new ArrayList<>(employees.values()).subList(startIndex, endIndex);
    }

    public List<Employee> findByGender(String gender) {
        return new ArrayList<>(employees.values()).stream().filter(item -> item.getGender().equals(gender)).collect(Collectors.toList());
    }

    public Employee update(String employeeId, Employee employee) {
        return employees.put(employeeId, employee);
    }

    public Employee delete(String employeeId) {
        return employees.remove(employeeId);
    }

}
