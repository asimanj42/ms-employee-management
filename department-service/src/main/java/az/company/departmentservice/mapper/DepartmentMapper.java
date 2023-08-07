package az.company.departmentservice.mapper;

import az.company.departmentservice.entity.Department;
import az.company.departmentservice.model.DepartmentDto;
import org.mapstruct.Mapper;

@Mapper
public interface DepartmentMapper {

    Department dtoToDepartmentEntity(DepartmentDto departmentDto);

    DepartmentDto entityToDepartmentDto(Department department);
}


