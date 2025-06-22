package br.com.FutebolaPlatform.exceptions;

public class PlayerGroupNotFoundException extends RuntimeException {
    public PlayerGroupNotFoundException(String message) {
        super(message);
    }
}