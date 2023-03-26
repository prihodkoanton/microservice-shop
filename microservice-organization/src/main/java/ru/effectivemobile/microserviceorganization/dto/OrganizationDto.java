package ru.effectivemobile.microserviceorganization.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import ru.effectivemobile.microserviceorganization.model.Organization;
import ru.effectivemobile.microserviceorganization.model.OrganizationStatus;

import java.time.LocalDateTime;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrganizationDto {

    private Organization organization;

    private String name;
    private String description;
    private String logoUrl;
    private OrganizationStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public OrganizationDto(Organization organization) {
        this.organization = organization;
    }

    public static Organization toOrganization(OrganizationDto organizationDto) {
        Organization organizationFromDto = new Organization();
        organizationFromDto.setName(organizationDto.getName());
        organizationFromDto.setDescription(organizationDto.getDescription());
        organizationFromDto.setLogoUrl(organizationDto.getLogoUrl());
        organizationFromDto.setStatus(organizationDto.getStatus());
        organizationFromDto.setCreatedAt(organizationDto.getCreatedAt());
        organizationFromDto.setUpdatedAt(organizationDto.getUpdatedAt());
        return organizationFromDto;
    }
}
