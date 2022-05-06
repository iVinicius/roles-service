package com.roles.service.service;

import com.roles.service.exception.MembershipAlreadyExistsException;
import com.roles.service.exception.MembershipNotFoundException;
import com.roles.service.repository.entity.MembershipEntity;
import com.roles.service.repository.MembershipRepository;
import com.roles.service.repository.entity.RoleEntity;
import com.roles.service.service.query.MembershipQuery;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

@Slf4j
@Service
public class MembershipService {

    private final MembershipRepository repository;

    private final RoleService roleService;

    public MembershipService(MembershipRepository repository, RoleService roleService) {
        this.repository = repository;
        this.roleService = roleService;
    }

    public Mono<MembershipEntity> createMembership(String roleId, String userId, String teamId) {
        MembershipEntity newMembership = MembershipEntity.builder()
                .roleId(roleId)
                .userId(userId)
                .teamId(teamId)
                .build();

        return membershipExists(newMembership)
                .filter(this::validateMembership)
                .flatMap(valid -> repository.save(newMembership))
                .switchIfEmpty(Mono.error(MembershipAlreadyExistsException::new));
    }

    public Mono<MembershipEntity> fetchMembership(String userId, String teamId) {
        return repository.findOne(Example.of(MembershipEntity.builder()
                        .userId(userId)
                        .teamId(teamId)
                        .build())
                )
                .switchIfEmpty(Mono.error(MembershipNotFoundException::new));
    }

    public Flux<MembershipEntity> searchMemberships(String userId, String teamId, String roleType) {
        MembershipQuery query = new MembershipQuery();

        Mono<RoleEntity> getRoleId = getGetRoleId(roleType, query);

        query.addUserToQuery(userId);
        query.addTeamToQuery(teamId);

        Flux<MembershipEntity> result = repository.findAll(Example.of(query.ready()));
        return Flux.zip(getRoleId, result).map(Tuple2::getT2);
    }

    private Mono<Boolean> membershipExists(MembershipEntity membership) {
        return repository.findOne(Example.of(membership.toBuilder().roleId(null).build()))
                .map(found -> true)
                .switchIfEmpty(Mono.defer(() -> Mono.just(Boolean.FALSE)));
    }

    private boolean validateMembership(Boolean membershipExist) {
        return !membershipExist;
    }

    private Mono<RoleEntity> getGetRoleId(String roleType, MembershipQuery query) {
        Mono<RoleEntity> getRoleId = Mono.just(RoleEntity.builder().build());
        if(roleType != null) {
            getRoleId = roleService.getRole(roleType).doOnNext(role -> query.addRoleToQuery(role.getRoleId()));
        }
        return getRoleId;
    }

}
