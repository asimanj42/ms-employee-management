package az.company.departmentservice.repository;

import az.company.departmentservice.entity.Department;
import az.company.departmentservice.model.DepartmentDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DepartmentRepository extends JpaRepository<Department,Long> {
     Optional<Department> findDepartmentByDepartmentCode(String departmentCode);

}
