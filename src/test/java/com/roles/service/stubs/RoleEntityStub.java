package com.roles.service.stubs;

import com.roles.service.repository.entity.RoleEntity;

public class RoleEntityStub {

    public static RoleEntity create(String roleName) {
        return RoleEntity.builder()
                .roleType(roleName)
                .roleId("id")
                .build();
    }
}
