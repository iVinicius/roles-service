package com.roles.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.UNPROCESSABLE_ENTITY)
public class MembershipAlreadyExistsException extends Exception{
    public MembershipAlreadyExistsException(){
        super("Membership already exists.");
    }
}
