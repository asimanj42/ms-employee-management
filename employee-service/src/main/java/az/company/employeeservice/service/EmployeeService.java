package az.company.employeeservice.service;

import az.company.employeeservice.model.ApiResponseDto;
import az.company.employeeservice.model.EmployeeDto;

public interface EmployeeService {

    EmployeeDto saveEmployee(EmployeeDto employeeDto);
    ApiResponseDto getEmployeeById(Long employeeId);
}
