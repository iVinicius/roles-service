package com.roles.service.repository;

import com.roles.service.repository.entity.RoleEntity;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends ReactiveMongoRepository<RoleEntity, String> {
}
