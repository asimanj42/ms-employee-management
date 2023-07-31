package az.company.employeeservice.service.impl;

import az.company.employeeservice.entity.Employee;
import az.company.employeeservice.exception.EmployeeAlreadyExistsException;
import az.company.employeeservice.exception.ResourceNotFoundException;
import az.company.employeeservice.mapper.EmployeeMapper;
import az.company.employeeservice.model.ApiResponseDto;
import az.company.employeeservice.model.DepartmentDto;
import az.company.employeeservice.model.EmployeeDto;
import az.company.employeeservice.repository.EmployeeRepository;
import az.company.employeeservice.service.EmployeeService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import static az.company.employeeservice.utility.ConstantErrorMessage.EMPLOYEE_ALREADY_EXISTS;

import java.util.Optional;


@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;
    private final RestTemplate restTemplate;

    @Override
    public EmployeeDto saveEmployee(EmployeeDto employeeDto) {
        Employee employee = EmployeeMapper.MAPPER.dtoToEmployeeEntity(employeeDto);
        Optional<Employee> optionalEmployee = employeeRepository.findByEmail(employee.getEmail());
        if (optionalEmployee.isPresent()) {
            throw new EmployeeAlreadyExistsException(EMPLOYEE_ALREADY_EXISTS);
        }

        Employee savedEmployee = employeeRepository.save(employee);
        return EmployeeMapper.MAPPER.entityToEmployeeDto(savedEmployee);

    }


    @Override
    public ApiResponseDto getEmployeeById(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(() -> new ResourceNotFoundException("Employee", "id", employeeId));
        ResponseEntity<DepartmentDto> responseEntity = restTemplate.getForEntity("http://localhost:8080/api/departments/" + employee.getDepartmentCode(), DepartmentDto.class);
        DepartmentDto departmentDto = responseEntity.getBody();
        EmployeeDto employeeDto = employeeMapper.entityToEmployeeDto(employee);
        ApiResponseDto apiResponseDto = new ApiResponseDto();
        apiResponseDto.setDepartmentDto(departmentDto);
        apiResponseDto.setEmployeeDto(employeeDto);
        return apiResponseDto;
    }

}
