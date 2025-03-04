package com.hoanght.bookingsystem.repository;

import com.hoanght.bookingsystem.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}