package com.challenge.api.services;

import com.challenge.api.model.Employee;
import com.challenge.api.model.EmployeeImplementation;
import java.time.Instant;
import java.util.*;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class EmployeeService {
    private List<Employee> employeeList = new ArrayList<>();

    public EmployeeService(){
        // Constructor to initialize a mock employeeList
        // in actual implementation could have load employees from the database or
        // just execute queries to database for each function and not have it stored in memory
        this.employeeList = createMockList();
    }
    public List<Employee> createMockList() {
        // use a stream to add three mock employees to the list
        return Stream.of(
                        new EmployeeImplementation() {
                            {
                                setUuid(UUID.randomUUID());
                                setFirstName("John");
                                setLastName("Doe");
                                setFullName("John Doe");
                                setSalary(60000);
                                setAge(30);
                                setJobTitle("Software Engineer");
                                setEmail("john.doe@example.com");
                                setContractHireDate(Instant.parse("2022-01-15T00:00:00Z"));
                                setContractTerminationDate(Instant.parse("2025-01-15T00:00:00Z"));
                            }
                        },
                        new EmployeeImplementation() {
                            {
                                setUuid(UUID.randomUUID());
                                setFirstName("Jane");
                                setLastName("Smith");
                                setFullName("Jane Smith");
                                setSalary(75000);
                                setAge(28);
                                setJobTitle("Data Analyst");
                                setEmail("jane.smith@example.com");
                                setContractHireDate(Instant.parse("2021-06-10T00:00:00Z"));
                                setContractTerminationDate(Instant.parse("2024-06-10T00:00:00Z"));
                            }
                        },
                        new EmployeeImplementation() {
                            {
                                setUuid(UUID.randomUUID());
                                setFirstName("Alice");
                                setLastName("Brown");
                                setFullName("Alice Brown");
                                setSalary(82000);
                                setAge(35);
                                setJobTitle("Project Manager");
                                setEmail("alice.brown@example.com");
                                setContractHireDate(Instant.parse("2020-03-20T00:00:00Z"));
                                setContractTerminationDate(Instant.parse("2025-03-20T00:00:00Z"));
                            }
                        })
                .collect(Collectors.toList());
    }

    public List<Employee> getAllEmployees() {
        // Return the current list of all employees
        // With a database this could query the databse to get the list so we dont need to hold it in memory
        return employeeList;
    }

    public Employee getEmployeeByUuid(UUID uuid) {
        // Iterate over list of employees and find the one with the uuid that matches or return error
        return employeeList.stream()
                .filter(employee -> employee.getUuid().equals(uuid))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee not found"));
    }

    public Employee createEmployee(Employee employee) {
        // Generate a random UUID for the new employee
        employee.setUuid(UUID.randomUUID());
        // Add the new employee to the list
        employeeList.add(employee);
        return employee;
    }
}
