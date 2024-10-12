package com.ironhack.lab3_04.repository;

import com.ironhack.lab3_04.enums.CustomerStatus;
import com.ironhack.lab3_04.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    Optional<Customer> findByCustomerName(String customerName);

    List<Customer> findByCustomerStatus(CustomerStatus customerStatus);

}
