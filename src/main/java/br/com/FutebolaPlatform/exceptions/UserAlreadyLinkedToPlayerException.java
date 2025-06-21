package br.com.FutebolaPlatform.exceptions;

public class UserAlreadyLinkedToPlayerException extends RuntimeException {
    public UserAlreadyLinkedToPlayerException(String message) {
        super(message);
    }
}