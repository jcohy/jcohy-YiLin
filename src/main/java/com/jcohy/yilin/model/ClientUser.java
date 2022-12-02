package com.jcohy.yilin.model;

import jakarta.persistence.Table;
import org.springframework.data.annotation.Id;

@Table
public record ClientUser(@Id String UserID,String nickName,String phoneNumber,Integer gender) {
}
