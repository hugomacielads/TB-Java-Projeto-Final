package com.olabi.babylonbank.exception;

public class ClienteException extends RuntimeException {

    public ClienteException(String message) {
        super(message);
    }

    public static class DuplicateCpfException extends ClienteException{
        public DuplicateCpfException(){
            super("CPF já cadastrado");
        }
    }
}
