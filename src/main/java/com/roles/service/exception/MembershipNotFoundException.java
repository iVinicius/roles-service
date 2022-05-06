package com.roles.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class MembershipNotFoundException extends Exception{
    public MembershipNotFoundException(){
        super("Membership doesn't exists.");
    }
}
