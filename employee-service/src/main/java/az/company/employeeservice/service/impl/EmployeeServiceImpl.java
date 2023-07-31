package az.company.employeeservice.service.impl;

import az.company.employeeservice.entity.Employee;
import az.company.employeeservice.exception.EmployeeAlreadyExistsException;
import az.company.employeeservice.exception.ResourceNotFoundException;
import az.company.employeeservice.mapper.EmployeeMapper;
import az.company.employeeservice.model.ApiResponseDto;
import az.company.employeeservice.model.DepartmentDto;
import az.company.employeeservice.model.EmployeeDto;
import az.company.employeeservice.repository.EmployeeRepository;
import az.company.employeeservice.service.ApiClient;
import az.company.employeeservice.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import static az.company.employeeservice.utility.ConstantErrorMessage.EMPLOYEE_ALREADY_EXISTS;

import java.util.Optional;


@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;
    //    private final RestTemplate restTemplate;
    //    private final WebClient webClient;
    private final ApiClient apiClient;

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


//    @Override
//    public ApiResponseDto getEmployeeById(Long employeeId) {
//        Employee employee = employeeRepository.findById(employeeId).orElseThrow(() -> new ResourceNotFoundException("Employee", "id", employeeId));
//        ResponseEntity<DepartmentDto> responseEntity = restTemplate.getForEntity("http://localhost:8080/api/departments/" + employee.getDepartmentCode(), DepartmentDto.class);
//        DepartmentDto departmentDto = responseEntity.getBody();
//        EmployeeDto employeeDto = employeeMapper.entityToEmployeeDto(employee);
//        ApiResponseDto apiResponseDto = new ApiResponseDto();
//        apiResponseDto.setDepartment(departmentDto);
//        apiResponseDto.setEmployee(employeeDto);
//        return apiResponseDto;
//    }

//    @Override
//    public ApiResponseDto getEmployeeById(Long employeeId) {
//        Employee employee = employeeRepository.findById(employeeId).orElseThrow(() -> new ResourceNotFoundException("Employee", "id", employeeId));
//       DepartmentDto departmentDto = webClient.get()
//                .uri("http://localhost:8080/api/departments/"+employee.getDepartmentCode())
//                .retrieve()
//                .bodyToMono(DepartmentDto.class)
//                .block();
//        EmployeeDto employeeDto = employeeMapper.entityToEmployeeDto(employee);
//        ApiResponseDto apiResponseDto = new ApiResponseDto();
//        apiResponseDto.setDepartment(departmentDto);
//        apiResponseDto.setEmployee(employeeDto);
//        return apiResponseDto;
//    }


    @Override
    public ApiResponseDto getEmployeeById(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(() -> new ResourceNotFoundException("Employee", "id", employeeId));
        DepartmentDto departmentDto = apiClient.getDepartment(employee.getDepartmentCode());
        EmployeeDto employeeDto = employeeMapper.entityToEmployeeDto(employee);
        ApiResponseDto apiResponseDto = new ApiResponseDto();
        apiResponseDto.setDepartment(departmentDto);
        apiResponseDto.setEmployee(employeeDto);
        return apiResponseDto;
    }
}
