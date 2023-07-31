package az.company.departmentservice.service.impl;

import az.company.departmentservice.entity.Department;
import az.company.departmentservice.exception.DepartmentAlreadyExistsException;
import az.company.departmentservice.exception.ResourceNotFoundException;
import az.company.departmentservice.mapper.DepartmentMapper;
import az.company.departmentservice.model.DepartmentDto;
import az.company.departmentservice.repository.DepartmentRepository;
import az.company.departmentservice.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import static az.company.departmentservice.utility.ConstantErrorMessage.*;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {
    private final DepartmentRepository departmentRepository;
    private final DepartmentMapper departmentMapper;

    @Override
    public DepartmentDto saveDepartment(DepartmentDto departmentDto) {

        Optional<Department> optionalDepartment = departmentRepository.findDepartmentByDepartmentCode(departmentDto.getDepartmentCode());
        if (optionalDepartment.isPresent()){
            throw new DepartmentAlreadyExistsException(DEPARTMENT_ALREADY_EXISTS);
        }
        Department department = departmentRepository.save(departmentMapper.dtoToDepartmentEntity(departmentDto));
        return departmentMapper.entityToDepartmentDto(department);

    }

    @Override
    public DepartmentDto getDepartmentByCode(String code) {

        Department department = departmentRepository.findDepartmentByDepartmentCode(code).orElseThrow(()->new ResourceNotFoundException("Department","Code",code));
        return departmentMapper.entityToDepartmentDto(department);
    }

}
