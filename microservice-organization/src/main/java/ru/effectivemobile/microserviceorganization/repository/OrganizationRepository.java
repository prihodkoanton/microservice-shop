package ru.effectivemobile.microserviceorganization.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.effectivemobile.microserviceorganization.model.Organization;
import ru.effectivemobile.microserviceorganization.model.OrganizationStatus;

import java.util.List;
import java.util.Optional;

public interface OrganizationRepository extends JpaRepository<Organization, Long> {

    List<Organization> findAllByOrganizationStatus(OrganizationStatus status);

    Optional<Organization> findByIdAndOrganizationStatus(Long id, OrganizationStatus status);

    List<Organization> findByName(String name);

    List<Organization> findByOrganizationStatus(OrganizationStatus status);
}
