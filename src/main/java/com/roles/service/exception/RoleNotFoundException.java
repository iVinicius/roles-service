package com.roles.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class RoleNotFoundException extends Exception{
    public RoleNotFoundException() {
        super("Role doesn't exists.");
    }
}
