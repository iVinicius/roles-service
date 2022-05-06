package com.roles.service.service.query;

import com.roles.service.repository.entity.MembershipEntity;
import lombok.Data;

@Data
public class MembershipQuery {

    private MembershipEntity.MembershipEntityBuilder queryBuilder;

    public MembershipQuery(){
        this.queryBuilder = MembershipEntity.builder();
    }

    public void addUserToQuery(String value) {
        queryBuilder.userId(value);
    }

    public void addTeamToQuery(String value) {
        queryBuilder.teamId(value);
    }

    public void addRoleToQuery(String value) {
        queryBuilder.roleId(value);
    }

    public MembershipEntity ready(){
        return queryBuilder.build();
    }
}
