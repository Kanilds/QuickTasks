package com.topicosespeciais.quicktask.exception;

public class UsuarioCadastradoException extends RuntimeException {

    public UsuarioCadastradoException(String login) {
        super("Usuario já cadastrado! " + login);
    }
}
