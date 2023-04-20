package viktormiller.employeemanager.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import viktormiller.employeemanager.model.Employee;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    /**
     * Delete by given id.
     *
     * @param id
     */
    void deleteEmployeeById(Long id);

    /**
     * Find by given id.
     *
     * @param id
     */
    Optional<Employee> findEmployeeById(Long id);

    /**
     * Find all rows.
     *
     * @param pageable
     */
    Page<Employee> findAll(Pageable pageable);

    /**
     * Search by given query.
     *
     * @param query
     * @param pageable
     */
    @Query("SELECT e FROM Employee e " +
            "WHERE e.name LIKE %?1% " +
            "OR e.email LIKE %?1% " +
            "OR e.employeeCode LIKE %?1% " +
            "OR e.phone LIKE %?1% " +
            "OR e.jobTitle LIKE %?1%")
    Page<Employee> findBySearchQuery(String query, Pageable pageable);
}
