package com.roles.service.service;

import com.roles.service.exception.RoleAlreadyExistsException;
import com.roles.service.exception.RoleNotFoundException;
import com.roles.service.repository.entity.RoleEntity;
import com.roles.service.repository.RoleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class RoleService {

    private final RoleRepository repository;

    public RoleService(RoleRepository repository) {
        this.repository = repository;
    }

    public Mono<RoleEntity> createRole(String roleTypeName) {
        RoleEntity newRole = RoleEntity.builder().roleType(roleTypeName).build();
        return roleExists(newRole)
                .filter(this::validateRole)
                .flatMap(valid -> repository.save(newRole))
                .switchIfEmpty(Mono.error(RoleAlreadyExistsException::new));
    }

    public Mono<RoleEntity> getRole(String roleType) {
        return repository.findOne(Example.of(RoleEntity.builder().roleType(roleType).build()))
                .switchIfEmpty(Mono.error(RoleNotFoundException::new));
    }

    private Mono<Boolean> roleExists(RoleEntity role) {
        return repository.findOne(Example.of(role))
                .map(found -> true)
                .switchIfEmpty(Mono.defer(() -> Mono.just(Boolean.FALSE)));
    }

    private boolean validateRole(Boolean roleExist) {
        return !roleExist;
    }

}
