package ru.effectivemobile.microserviceorganization.service;

import ru.effectivemobile.microserviceorganization.model.Organization;
import ru.effectivemobile.microserviceorganization.model.OrganizationStatus;

import java.util.List;

public interface OrganizationService {
    List<Organization> findAll();

    Organization findById(Long id);

    Organization create(Organization organization);

    Organization update(Organization organization);

    List<Organization> findByName(String name);

    List<Organization> findByStatus(OrganizationStatus status);

    void delete(Long id);
}
