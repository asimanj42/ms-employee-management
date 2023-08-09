import axios from "axios";

const employee_service_base_url = "http://localhost:9191/api/employees";

const employee_id =11;

class EmployeeService {
  getEmployee() {
    return axios.get(employee_service_base_url + "/" + employee_id);
  }
}

export default new EmployeeService;
