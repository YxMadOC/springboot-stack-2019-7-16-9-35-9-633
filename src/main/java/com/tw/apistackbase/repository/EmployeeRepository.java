package com.tw.apistackbase.repository;

import com.tw.apistackbase.model.Employee;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class EmployeeRepository {

    private static final Map<Integer, Employee> employees = new HashMap<>();
    private static int currentIndex = 2;

    {
        employees.put(1, new Employee(1, 1, "Cayde", 20, "Male", 20000));
    }

    public static Map<Integer, Employee> getEmployees() {
        return employees;
    }

    public List<Employee> findAll() {
        return new ArrayList<>(employees.values());
    }

    public Employee save(Employee employee) {
        employee.setEmployeeId(currentIndex);
        employees.put(currentIndex++, employee);
        return employees.get(employee.getEmployeeId());
    }

    public List<Employee> findByPage(Integer page, Integer pageSize) {
        int startIndex = (page - 1) * pageSize;
        int endIndex = startIndex + pageSize;
        return new ArrayList<>(employees.values()).subList(startIndex, endIndex);
    }

    public List<Employee> findByGender(String gender) {
        return new ArrayList<>(employees.values()).stream().filter(item -> item.getGender().equals(gender)).collect(Collectors.toList());
    }

    public Employee findById(Integer employeeId) {
        return employees.get(employeeId);
    }

    public Employee update(Integer employeeId, Employee employee) {
        employee.setEmployeeId(employeeId);
        return employees.put(employeeId, employee);
    }

    public Employee delete(Integer employeeId) {
        return employees.remove(employeeId);
    }
}
