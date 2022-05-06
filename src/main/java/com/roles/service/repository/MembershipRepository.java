package com.roles.service.repository;

import com.roles.service.repository.entity.MembershipEntity;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MembershipRepository extends ReactiveMongoRepository<MembershipEntity, String> {
}
