package com.roles.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.UNPROCESSABLE_ENTITY)
public class RoleAlreadyExistsException extends Exception{
    public RoleAlreadyExistsException() {
        super("Role already exists.");
    }
}
