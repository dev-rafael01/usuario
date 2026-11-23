package io.dev_rafael01.usuario.infrastructure.exceptions;

public class ResourceNotFoudException  extends RuntimeException{

    public ResourceNotFoudException(String mensagem){
        super(mensagem);
    }

    public ResourceNotFoudException(String email, Throwable throwable){
        super(email, throwable);
    }
}
