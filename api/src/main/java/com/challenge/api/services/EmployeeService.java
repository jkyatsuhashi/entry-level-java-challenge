package com.challenge.api.services;

import com.challenge.api.model.Employee;
import com.challenge.api.model.EmployeeImplementation;
import java.time.Instant;
import java.util.*;
import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class EmployeeService {
    private List<Employee> employeeList;

    public EmployeeService() {
        // Initialize the mock employeeList directly in the constructor
        // Normally this could be a query to database if you want data to be stored in memory
        this.employeeList = new ArrayList<>(List.of(
                new EmployeeImplementation(
                        UUID.randomUUID(),
                        "John",
                        "Doe",
                        "John Doe",
                        60000,
                        30,
                        "Software Engineer",
                        "john.doe@example.com",
                        Instant.parse("2022-01-15T00:00:00Z"),
                        Instant.parse("2025-01-15T00:00:00Z")),
                new EmployeeImplementation(
                        UUID.randomUUID(),
                        "Jane",
                        "Smith",
                        "Jane Smith",
                        75000,
                        28,
                        "Data Analyst",
                        "jane.smith@example.com",
                        Instant.parse("2021-06-10T00:00:00Z"),
                        Instant.parse("2024-06-10T00:00:00Z")),
                new EmployeeImplementation(
                        UUID.randomUUID(),
                        "Alice",
                        "Brown",
                        "Alice Brown",
                        82000,
                        35,
                        "Project Manager",
                        "alice.brown@example.com",
                        Instant.parse("2020-03-20T00:00:00Z"),
                        Instant.parse("2025-03-20T00:00:00Z"))));
    }

    public List<Employee> getAllEmployees() {
        // Return the current list of all employees
        // With a database this could query the database to get the list so we dont need to hold it in memory
        return employeeList;
    }

    public Employee getEmployeeByUuid(UUID uuid) {
        // Iterate over list of employees and find the one with the uuid that matches or return error
        return employeeList.stream()
                .filter(employee -> employee.getUuid().equals(uuid))
                .findFirst() // Ensures only one
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee not found"));
    }

    public Employee createEmployee(Employee employee) {
        // Generate a random UUID for the new employee if none already
        Employee newEmployee = new EmployeeImplementation(
                employee.getUuid() != null ? employee.getUuid() : UUID.randomUUID(),
                employee.getFirstName(),
                employee.getLastName(),
                employee.getFullName(),
                employee.getSalary(),
                employee.getAge(),
                employee.getJobTitle(),
                employee.getEmail(),
                employee.getContractHireDate(),
                employee.getContractTerminationDate());
        // Add the new employee to the list (would be sending to database here)
        employeeList.add(newEmployee);
        return newEmployee;
    }
}
