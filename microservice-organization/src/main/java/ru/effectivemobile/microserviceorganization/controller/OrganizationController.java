package ru.effectivemobile.microserviceorganization.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import ru.effectivemobile.microserviceorganization.dto.OrganizationDto;
import ru.effectivemobile.microserviceorganization.dto.UserCredentials;
import ru.effectivemobile.microserviceorganization.model.Organization;
import ru.effectivemobile.microserviceorganization.model.OrganizationStatus;
import ru.effectivemobile.microserviceorganization.service.OrganizationService;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(OrganizationController.BASE_URL)
public class OrganizationController {

    public final static String BASE_URL = "/api/v1/organizations";

    private final OrganizationService organizationService;

    private final RestTemplate restTemplate;

    @Value("${spring.authenticationServiceUrl.value}")
    private String authenticationServiceUrl;

    public OrganizationController(OrganizationService organizationService, RestTemplate restTemplate) {
        this.organizationService = organizationService;
        this.restTemplate = restTemplate;
    }

    @PostMapping("/authenticate")
    public ResponseEntity<String> authenticate(@RequestBody UserCredentials credentials) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<UserCredentials> request = new HttpEntity<>(credentials, headers);
        ResponseEntity<String> response = restTemplate.postForEntity(authenticationServiceUrl + "/authenticate", request, String.class);
        return ResponseEntity.ok(response.getBody());
    }

    @GetMapping
    public ResponseEntity<List<OrganizationDto>> getAllOrganizations() {
        List<Organization> organizations = organizationService.findAll();
        return ResponseEntity.ok(organizations.stream()
                .map(OrganizationDto::new)
                .collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrganizationDto> getOrganizationById(@PathVariable Long id) {
        Organization organization = organizationService.findById(id);
        return ResponseEntity.ok(new OrganizationDto(organization));
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<List<OrganizationDto>> getOrganizationByName(@PathVariable String name) {
        List<Organization> organizations = organizationService.findByName(name);
        return ResponseEntity.ok(organizations.stream()
                .map(OrganizationDto::new)
                .collect(Collectors.toList()));
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<OrganizationDto>> getOrganizationByStatus(@PathVariable OrganizationStatus status) {
        List<Organization> organizations = organizationService.findByStatus(status);
        return ResponseEntity.ok(organizations.stream()
                .map(OrganizationDto::new)
                .collect(Collectors.toList()));
    }

    @PostMapping
    public ResponseEntity<OrganizationDto> createOrganization(@Valid @RequestBody OrganizationDto organizationDto) {
        Organization organization = organizationService.create(OrganizationDto.toOrganization(organizationDto));
        OrganizationDto createdDto = new OrganizationDto(organization);
        return new ResponseEntity<>(createdDto, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<OrganizationDto> updateOrganization(@PathVariable Long id, @Valid @RequestBody OrganizationDto organizationDto) {
        Organization organization = OrganizationDto.toOrganization(organizationDto);
        organization.setId(id);
        organization = organizationService.update(organization);
        OrganizationDto updatedDto = new OrganizationDto(organization);
        return new ResponseEntity<>(updatedDto, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteOrganization(@PathVariable Long id) {
        organizationService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
