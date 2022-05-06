package com.roles.service.api;

import com.roles.service.api.requests.MembershipCreationRequest;
import com.roles.service.api.requests.MembershipFetchRequest;
import com.roles.service.api.responses.MembershipResponse;
import com.roles.service.service.MembershipService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Slf4j
@RestController
@RequestMapping("/roles/memberships")
public class MembershipController {

    private final MembershipService service;

    public MembershipController(MembershipService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<MembershipResponse> createMembership(@Valid @RequestBody MembershipCreationRequest request) {
        return service.createMembership(request.getRoleId(), request.getUserId(), request.getTeamId())
                .doOnRequest(unused -> log.debug("[PROCESSING] Membership creation. {}", request))
                .map(entity -> MembershipResponse.builder()
                        .membershipId(entity.getMembershipId())
                        .build()
                )
                .doOnError(error -> log.error("[FAILED] Membership creation.", error))
                .doOnSuccess(response -> log.info("[SUCCESS] Membership creation. Id: {}", response.getMembershipId()));
    }

    @GetMapping
    public Mono<MembershipResponse> fetchMembership(@NotBlank @RequestParam String userId,
                                                    @NotBlank @RequestParam String teamId) {
        return service.fetchMembership(userId, teamId)
                .doOnRequest(unused -> log.debug("[PROCESSING] Membership fetch. Params: {}, {}", userId, teamId))
                .map(entity -> MembershipResponse.builder()
                        .membershipId(entity.getMembershipId())
                        .roleId(entity.getRoleId())
                        .userId(entity.getUserId())
                        .teamId(entity.getTeamId())
                        .build()
                )
                .doOnError(error -> log.error("[FAILED] Membership fetch.", error))
                .doOnSuccess(response -> log.info("[SUCCESS] Membership fetch. Id: {}", response.getMembershipId()));
    }

    @GetMapping("/search")
    public Flux<MembershipResponse> searchMembership(@RequestParam(required = false) String userId,
                                                     @RequestParam(required = false) String teamId,
                                                     @RequestParam(required = false) String roleType)
    {
        return service.searchMemberships(userId, teamId, roleType)
                .doOnRequest(unused -> log.debug("[PROCESSING] Membership search. Params: {}, {}. {}", userId, teamId, roleType))
                .map(entity -> MembershipResponse.builder()
                        .membershipId(entity.getMembershipId())
                        .roleId(entity.getRoleId())
                        .userId(entity.getUserId())
                        .teamId(entity.getTeamId())
                        .build()
                )
                //.defaultIfEmpty(Flux.just())
                .doOnError(error -> log.error("[FAILED] Membership search.", error))
                .doOnNext(response -> log.info("[SUCCESS] Membership search. Params: {}, {}. {}", userId, teamId, roleType));
    }
}
