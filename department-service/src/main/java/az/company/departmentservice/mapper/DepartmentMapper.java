package az.company.departmentservice.mapper;

import az.company.departmentservice.entity.Department;
import az.company.departmentservice.model.DepartmentDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DepartmentMapper {
    DepartmentMapper MAPPER = Mappers.getMapper(DepartmentMapper.class);
    Department dtoToDepartmentEntity(DepartmentDto departmentDto);

    DepartmentDto entityToDepartmentDto(Department department);
}


