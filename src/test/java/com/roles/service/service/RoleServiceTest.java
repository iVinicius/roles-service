package com.roles.service.service;

import com.roles.service.exception.RoleAlreadyExistsException;
import com.roles.service.repository.RoleRepository;
import com.roles.service.stubs.RoleEntityStub;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@Import(RoleService.class)
public class RoleServiceTest {

    @Autowired
    RoleService service;

    @MockBean
    RoleRepository repository;

    @Test
    void testRoleCreationSuccessful() {
        String role = "ROLE_ONE";

        when(repository.findOne(any()))
                .thenReturn(Mono.empty());

        when(repository.save(any()))
                .thenReturn(Mono.just(RoleEntityStub.create(role)));

        StepVerifier.create(service.createRole(role))
                .expectNextMatches(x -> {
                    verify(repository, times(1)).findOne(any());
                    verify(repository, times(1)).save(any());
                    return true;
                })
                .verifyComplete();
    }

    @Test
    void testRoleCreationFailureAlreadyExists() {
        String role = "ROLE_TWO";

        when(repository.findOne(any()))
                .thenReturn(Mono.just(RoleEntityStub.create(role)));

        StepVerifier.create(service.createRole(role))
                .verifyError(RoleAlreadyExistsException.class);
    }
}
