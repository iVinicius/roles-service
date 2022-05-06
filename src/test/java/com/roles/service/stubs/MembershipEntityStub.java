package com.roles.service.stubs;

import com.roles.service.repository.entity.MembershipEntity;

public class MembershipEntityStub {

    public static MembershipEntity create(String user, String team) {
        return MembershipEntity.builder()
                .userId(user)
                .teamId(team)
                .membershipId("id")
                .roleId("roleId")
                .build();
    }
}
