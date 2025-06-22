package br.com.FutebolaPlatform.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY) // ou BAD_REQUEST
public class MatchAlreadyFinishedException extends RuntimeException {
    public MatchAlreadyFinishedException(String message) {
        super(message);
    }
}