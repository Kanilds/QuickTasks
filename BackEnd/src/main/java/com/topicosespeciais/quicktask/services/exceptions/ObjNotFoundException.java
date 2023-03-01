package com.topicosespeciais.quicktask.services.exceptions;

import java.io.Serial;

public class ObjNotFoundException extends RuntimeException{

    @Serial
    private static final long serialVersionUID = 1L;

    public ObjNotFoundException(String message) {
        super(message);
    }
}
