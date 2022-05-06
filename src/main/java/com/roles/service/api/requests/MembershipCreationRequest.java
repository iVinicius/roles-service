package com.roles.service.api.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MembershipCreationRequest {

    @NotNull(message = "The roleId name cannot be null or blank.")
    @NotBlank
    private String roleId;
    @NotNull(message = "The userId name cannot be null or blank.")
    @NotBlank
    private String userId;
    @NotNull(message = "The teamId name cannot be null or blank.")
    @NotBlank
    private String teamId;
}
