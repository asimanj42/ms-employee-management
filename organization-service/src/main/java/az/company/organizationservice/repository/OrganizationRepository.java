package az.company.organizationservice.repository;

import az.company.organizationservice.entity.Organization;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrganizationRepository extends JpaRepository<Organization, Long> {

    Optional<Organization> findOrganizationByOrganizationCode(String organizationCode);


}
