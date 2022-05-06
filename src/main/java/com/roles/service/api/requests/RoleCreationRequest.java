package com.roles.service.api.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleCreationRequest {

    @NotNull(message = "The role name cannot be null or blank.")
    @NotBlank
    private String roleTypeName;
}
