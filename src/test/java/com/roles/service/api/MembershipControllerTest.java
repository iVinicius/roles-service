package com.roles.service.api;

import com.roles.service.repository.MembershipRepository;
import com.roles.service.service.MembershipService;
import com.roles.service.service.RoleService;
import com.roles.service.stubs.MembershipEntityStub;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@WebFluxTest(controllers = MembershipController.class)
@Import(MembershipService.class)
public class MembershipControllerTest {

    @MockBean
    MembershipRepository repository;

    @MockBean
    RoleService roleService;

    @Autowired
    WebTestClient webTestClient;

    @Test
    void testFetchMembership(){
        String user = "userA";
        String team = "teamB";

        when(repository.findOne(any()))
                .thenReturn(Mono.just(MembershipEntityStub.create(user, team)));

        webTestClient.get()
                .uri(uriBuilder -> uriBuilder.path("/roles/memberships")
                        .queryParam("userId", user)
                        .queryParam("teamId", team)
                        .build())
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody()
                .jsonPath("$.membershipId").isEqualTo("id")
                .jsonPath("$.roleId").isEqualTo("roleId")
                .jsonPath("$.userId").isEqualTo(user)
                .jsonPath("$.teamId").isEqualTo(team);
    }
}
