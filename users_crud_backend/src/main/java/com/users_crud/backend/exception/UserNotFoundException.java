package com.users_crud.backend.exception;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(Long id){
        super("El usuario no existe: " + id);
    }
}
