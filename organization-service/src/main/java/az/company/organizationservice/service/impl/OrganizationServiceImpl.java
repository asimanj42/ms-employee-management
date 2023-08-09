package az.company.organizationservice.service.impl;

import az.company.organizationservice.entity.Organization;
import az.company.organizationservice.exception.OrganizationAlreadyExistsException;
import az.company.organizationservice.exception.ResourceNotFoundException;
import az.company.organizationservice.mapper.OrganizationMapper;
import az.company.organizationservice.model.OrganizationDto;
import az.company.organizationservice.repository.OrganizationRepository;
import az.company.organizationservice.service.OrganizationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrganizationServiceImpl implements OrganizationService {
    private final OrganizationRepository organizationRepository;

    @Override
    public OrganizationDto saveOrganization(OrganizationDto organizationDto) {
        Organization organization = OrganizationMapper.MAPPER.dtoToOrganizationEntity(organizationDto);
        Optional<Organization> optionalOrganization = organizationRepository.findOrganizationByOrganizationCode(organizationDto.getOrganizationCode());
        if (optionalOrganization.isPresent()) {
            throw new OrganizationAlreadyExistsException("Organization already exists");
        }
        Organization savedOrganization = organizationRepository.save(organization);
        return OrganizationMapper.MAPPER.entityToOrganizationDto(savedOrganization);
    }

    @Override
    public OrganizationDto findOrganizationByCode(String organizationCode) {
        Organization organization = organizationRepository.findOrganizationByOrganizationCode(organizationCode).orElseThrow(() -> new ResourceNotFoundException("Organization", "code", organizationCode));
        return OrganizationMapper.MAPPER.entityToOrganizationDto(organization);
    }
}
