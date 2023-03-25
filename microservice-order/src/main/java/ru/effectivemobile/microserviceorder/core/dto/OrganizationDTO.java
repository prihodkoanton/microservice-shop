package ru.effectivemobile.microserviceorder.core.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import ru.effectivemobile.microserviceorder.core.model.Organization;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrganizationDTO {

    private Organization organization;

    public static Organization toOrganization(OrganizationDTO dto){
        Organization organizationFromDto = new Organization();
        return organizationFromDto;
    }

    public static OrganizationDTO toOrganizationDTO(Organization organization) {
        OrganizationDTO dto = new OrganizationDTO();
        return dto;
    }
}
