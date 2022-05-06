package com.roles.service.repository.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Builder
@Data
public class RoleEntity {
    @Id
    private String roleId;
    private String roleType;
}
