package az.company.organizationservice.controller;

import az.company.organizationservice.model.OrganizationDto;
import az.company.organizationservice.service.OrganizationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.HTMLDocument;

@RequiredArgsConstructor
@RequestMapping("/api/organizations")
@RestController
public class OrganizationController {
    private final OrganizationService organizationService;

    @GetMapping("/{organizationCode}")
    public ResponseEntity findOrganizationByCode(@PathVariable(value = "organizationCode") String organizationCode) {
        return new ResponseEntity<>(organizationService.findOrganizationByCode(organizationCode), HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity saveOrganization(@RequestBody OrganizationDto organizationDto) {
        return new ResponseEntity<>(organizationService.saveOrganization(organizationDto), HttpStatus.CREATED);
    }
}
