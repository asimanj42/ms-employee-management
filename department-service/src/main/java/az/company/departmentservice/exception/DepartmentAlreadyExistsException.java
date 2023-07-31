package az.company.departmentservice.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class DepartmentAlreadyExistsException extends RuntimeException {

    private String message;

    public DepartmentAlreadyExistsException(String message) {
        super(message);
    }
}
