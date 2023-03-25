package ru.effectivemobile.microserviceorganization.dto;

import lombok.Data;
import ru.effectivemobile.microserviceorganization.model.Organization;

@Data
public class OrganizationDto {

    private Organization organization;

    public OrganizationDto(Organization organization) {
        this.organization = organization;
    }

    public Organization toOrganization() {
        Organization organizationFromDto = new Organization();
        organizationFromDto.setId(organization.getId());
        organizationFromDto.setName(organization.getName());
        organizationFromDto.setDescription(organization.getDescription());
        organizationFromDto.setLogoUrl(organization.getLogoUrl());
        organizationFromDto.setOrganizationStatus(organization.getOrganizationStatus());
        organizationFromDto.setCreatedAt(organization.getCreatedAt());
        organizationFromDto.setUpdatedAt(organization.getUpdatedAt());
        return organizationFromDto;
    }
}
