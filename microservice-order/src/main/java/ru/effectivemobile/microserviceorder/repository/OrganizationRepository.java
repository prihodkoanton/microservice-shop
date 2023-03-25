package ru.effectivemobile.microserviceorder.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.effectivemobile.microserviceorder.core.model.Organization;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface OrganizationRepository extends JpaRepository<Organization, Long> {

    Optional<Organization> findByName(String name);

    Optional<Organization> findByAddress(String address);

    List<Organization> findByCreatedBetween(LocalDateTime startDate, LocalDateTime endDate);
}
