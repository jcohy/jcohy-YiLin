package com.jcohy.yilin.model;


import org.springframework.data.annotation.Id;

public record Customer(@Id Long id, String firstName, String lastName) {
}
