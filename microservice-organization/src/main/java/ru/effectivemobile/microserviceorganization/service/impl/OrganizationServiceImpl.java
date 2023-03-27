package ru.effectivemobile.microserviceorganization.service.impl;

import org.springframework.stereotype.Service;
import ru.effectivemobile.microserviceorganization.model.Organization;
import ru.effectivemobile.microserviceorganization.model.OrganizationStatus;
import ru.effectivemobile.microserviceorganization.repository.OrganizationRepository;
import ru.effectivemobile.microserviceorganization.service.OrganizationService;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class OrganizationServiceImpl implements OrganizationService {

    private final OrganizationRepository organizationRepository;

    public OrganizationServiceImpl(OrganizationRepository organizationRepository) {
        this.organizationRepository = organizationRepository;
    }

    @Override
    public List<Organization> findAll() {
        return organizationRepository.findAll();
    }

    @Override
    public Organization findById(Long id) {
        Organization organization = organizationRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Organization with id: " + id + " doesn't find"));
        return organization;
    }

    @Override
    @Transactional
    public Organization create(Organization organization) {
        return organizationRepository.save(organization);
    }

    @Override
    @Transactional
    public Organization update(Organization organization) {

        return null;
    }

    @Override
    public List<Organization> findByName(String name) {
        List<Organization> organizations = organizationRepository.findByName(name);
        return organizations;
    }

    @Override
    public List<Organization> findByStatus(OrganizationStatus status) {
        List<Organization> organizations = organizationRepository.findByStatus(status);
        return organizations;
    }

    @Override
    @Transactional
    public void delete(Long id) {
        organizationRepository.deleteById(id);
    }
}
