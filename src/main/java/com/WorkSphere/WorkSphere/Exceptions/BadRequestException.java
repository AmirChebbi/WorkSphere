package com.WorkSphere.WorkSphere.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class BadRequestException extends RuntimeException{
    public BadRequestException(String msg){super(msg);}
}
