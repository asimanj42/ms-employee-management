package az.company.organizationservice.service;

import az.company.organizationservice.model.OrganizationDto;

public interface OrganizationService {
    OrganizationDto saveOrganization(OrganizationDto organizationDto);
    OrganizationDto findOrganizationByCode(String organizationCode);
}
