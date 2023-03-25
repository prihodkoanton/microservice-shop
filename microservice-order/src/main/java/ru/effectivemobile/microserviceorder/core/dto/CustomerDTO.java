package ru.effectivemobile.microserviceorder.core.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import ru.effectivemobile.microserviceorder.core.model.Customer;

import java.time.LocalDateTime;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CustomerDTO {
    private Long id;
    private String name;
    private String email;
    private LocalDateTime created;
    private LocalDateTime updated;

    public static Customer toCustomer(CustomerDTO customerDTO){
        Customer customer = new Customer();
        customer.setId(customerDTO.getId());
        customer.setName(customerDTO.getName());
        customer.setEmail(customerDTO.getEmail());
        customer.setCreated(customerDTO.getCreated());
        customer.setUpdated(customerDTO.getUpdated());
        return customer;
    }

    public static CustomerDTO toCustomerDTO(Customer customer){
        CustomerDTO dto = new CustomerDTO();
        dto.setId(customer.getId());
        dto.setName(customer.getName());
        dto.setEmail(customer.getEmail());
        dto.setCreated(customer.getCreated());
        dto.setUpdated(customer.getUpdated());
        return dto;
    }
}
