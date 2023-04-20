package viktormiller.employeemanager.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import viktormiller.employeemanager.data.EmployeeCriteria;
import viktormiller.employeemanager.exception.UserNotFoundException;
import viktormiller.employeemanager.model.Employee;
import viktormiller.employeemanager.repository.EmployeeRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class EmployeeService {
    private final EmployeeRepository repository;

    @Autowired
    public EmployeeService(EmployeeRepository repository) {
        this.repository = repository;
    }

    public Employee findEmployeeById(Long id) {
        return this.repository.findEmployeeById(id)
                .orElseThrow(() -> new UserNotFoundException("Employee by id "+ id +" was not found."));
    }

    public Employee addEmployee(Employee employee) {
        employee.setEmployeeCode(UUID.randomUUID().toString());

        return this.repository.save(employee);
    }

    public List<Employee> search(EmployeeCriteria criteria) {
        Page<Employee> result = (criteria.getQuery().length() > 0)
            ? this.repository.findBySearchQuery(criteria.getQuery(), criteria.getPagable())
            : this.repository.findAll(criteria.getPagable());

        return result.hasContent()? result.getContent() : new ArrayList<>();
    }

    public Employee updateEmployee(Employee employee) {
        return this.repository.save(employee);
    }

    @Transactional
    public void deleteEmployee(Long id) {
        this.repository.deleteEmployeeById(id);
    }
}
