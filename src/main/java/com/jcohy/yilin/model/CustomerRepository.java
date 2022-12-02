package com.jcohy.yilin.model;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface CustomerRepository extends R2dbcRepository<Customer,Long> {
@Query("SELECT * FROM customer WHERE last_name = :lastname")
Flux<Customer> findByLastName(String lastName);
}
