package az.company.employeeservice.mapper;

import az.company.employeeservice.entity.Employee;
import az.company.employeeservice.model.EmployeeDto;
import org.mapstruct.Mapper;

@Mapper
public interface EmployeeMapper {

    Employee dtoToEmployeeEntity(EmployeeDto employeeDto);

    EmployeeDto entityToEmployeeDto(Employee employee);
}
