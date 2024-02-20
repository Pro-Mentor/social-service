package com.github.promentor.exceptions.custom;

import com.github.promentor.exceptions.CustomException;
import com.github.promentor.exceptions.ErrorCode;
import jakarta.ws.rs.core.Response.Status;

public class InvalidUUID extends CustomException {

    public InvalidUUID() {
        super(ErrorCode.INVALID_OBJECT, ErrorCode.INVALID_OBJECT.toString(), Status.BAD_REQUEST);
    }

}
