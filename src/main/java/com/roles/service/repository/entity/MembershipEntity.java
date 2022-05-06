package com.roles.service.repository.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Builder(toBuilder = true)
@Data
public class MembershipEntity {
    @Id
    private String membershipId;
    private String roleId;
    private String userId;
    private String teamId;
}
