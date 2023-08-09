package az.company.organizationservice.mapper;

import az.company.organizationservice.entity.Organization;
import az.company.organizationservice.model.OrganizationDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OrganizationMapper {

    OrganizationMapper MAPPER = Mappers.getMapper(OrganizationMapper.class);
    Organization dtoToOrganizationEntity(OrganizationDto organizationDto);

    OrganizationDto entityToOrganizationDto(Organization organization);
}
