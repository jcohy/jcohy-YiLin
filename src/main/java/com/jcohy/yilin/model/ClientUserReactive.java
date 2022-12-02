package com.jcohy.yilin.model;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface ClientUserReactive extends R2dbcRepository<ClientUser,String> {
}
