package com.roles.service.api;

import com.roles.service.api.requests.RoleCreationRequest;
import com.roles.service.api.responses.RoleResponse;
import com.roles.service.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/roles")
public class RoleController {

    private final RoleService service;

    public RoleController(RoleService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<RoleResponse> createRole(@Valid @RequestBody RoleCreationRequest request) {
        return service.createRole(request.getRoleTypeName())
                .doOnRequest(unused -> log.debug("[PROCESSING] Role creation. {}", request))
                .map(entity -> RoleResponse.builder()
                        .roleId(entity.getRoleId())
                        .build())
                .doOnError(error -> log.error("[FAILED] Role creation.", error))
                .doOnSuccess(response -> log.info("[SUCCESS] Role creation. Id: {}", response.getRoleId()));
    }
}
