package viktormiller.employeemanager;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import viktormiller.employeemanager.data.EmployeeCriteria;
import viktormiller.employeemanager.model.Employee;
import viktormiller.employeemanager.service.EmployeeService;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/employee")
@Validated
public class EmployeeResource {
    private final EmployeeService employeeService;

    public EmployeeResource(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees(@Validated EmployeeCriteria criteria) {
        List<Employee> employees = this.employeeService.search(criteria);

        System.out.println(criteria);

        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") Long id) {
        Employee employee = this.employeeService.findEmployeeById(id);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee) {
        Employee newEmployee = this.employeeService.addEmployee(employee);
        return new ResponseEntity<>(newEmployee, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee) {
        Employee newEmployee = this.employeeService.updateEmployee(employee);
        return new ResponseEntity<>(newEmployee, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> addEmployee(@PathVariable("id") Long id) {
        System.out.println(id);
        this.employeeService.deleteEmployee(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
