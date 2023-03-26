package ru.effectivemobile.microserviceorder.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.effectivemobile.microserviceorder.core.model.Customer;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Optional<Customer> findByName(String name);

    Optional<Customer> findByEmail(String email);

    List<Customer> findByCreatedBetween(LocalDateTime startDate, LocalDateTime endDate);
}
