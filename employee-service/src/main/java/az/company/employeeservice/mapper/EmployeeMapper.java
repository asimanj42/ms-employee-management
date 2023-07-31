package az.company.employeeservice.mapper;

import az.company.employeeservice.entity.Employee;
import az.company.employeeservice.model.EmployeeDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface EmployeeMapper {

    EmployeeMapper MAPPER = Mappers.getMapper(EmployeeMapper.class);
    Employee dtoToEmployeeEntity(EmployeeDto employeeDto);

    EmployeeDto entityToEmployeeDto(Employee employee);
}
