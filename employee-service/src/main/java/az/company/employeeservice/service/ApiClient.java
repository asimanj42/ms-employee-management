package az.company.employeeservice.service;


import az.company.employeeservice.model.DepartmentDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "DEPARTMENT-SERVICE")
public interface ApiClient {

    @GetMapping("api/departments/{departmentCode}")
    DepartmentDto getDepartment(@PathVariable("departmentCode") String departmentCode);

}
