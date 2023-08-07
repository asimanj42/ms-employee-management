package az.company.employeeservice.controller;

import kotlin.js.ExperimentalJsExport;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.util.ConditionalOnBootstrapDisabled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.processing.Generated;

@RefreshScope
@RestController
public class MessageController {

    @Value("${spring.boot.message}")
    private String message;
    @GetMapping("/users/message")
    public String getMessage() {
        return message;
    }
}
