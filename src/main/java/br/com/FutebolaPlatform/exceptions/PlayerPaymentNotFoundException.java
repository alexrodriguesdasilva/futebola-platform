package br.com.FutebolaPlatform.exceptions;

public class PlayerPaymentNotFoundException extends RuntimeException {
    public PlayerPaymentNotFoundException(String message) {
        super(message);
    }
}
