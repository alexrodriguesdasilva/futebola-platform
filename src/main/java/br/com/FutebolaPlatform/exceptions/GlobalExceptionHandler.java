package br.com.FutebolaPlatform.exceptions;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // 🟡 Erros de validação do DTO (@Valid)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationException(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> 
            errors.put(error.getField(), error.getDefaultMessage())
        );
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    // 🔴 playerId não encontrado
    @ExceptionHandler(PlayerNotFoundException.class)
    public ResponseEntity<Map<String, String>> handlePlayerNotFound(PlayerNotFoundException ex) {
        Map<String, String> error = Map.of("error", ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    // 🔴 UUID malformado ou erro manual no service (inclui groupId inválido ou grupo não encontrado)
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, String>> handleIllegalArgument(IllegalArgumentException ex) {
        Map<String, String> error = Map.of("error", ex.getMessage());
        return ResponseEntity.badRequest().body(error);
    }

    // 🔴 Usuário não encontrado
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleUserNotFound(UserNotFoundException ex) {
        Map<String, String> error = Map.of("error", ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    // 🔴 Usuário já vinculado a um jogador
    @ExceptionHandler(UserAlreadyLinkedToPlayerException.class)
    public ResponseEntity<Map<String, String>> handleUserAlreadyLinked(UserAlreadyLinkedToPlayerException ex) {
        Map<String, String> error = Map.of("error", ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    // ⚠️ Tratamento de violação de integridade (chaves únicas, etc)
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Map<String, String>> handleDataIntegrityViolation(DataIntegrityViolationException ex) {
        Map<String, String> error = new HashMap<>();
        Throwable rootCause = ex.getRootCause();
        String causeMessage = rootCause != null && rootCause.getMessage() != null ? rootCause.getMessage().toLowerCase() : "";

        if (causeMessage.contains("email")) {
            error.put("error", "E-mail já está em uso.");
        } else if (causeMessage.contains("phone")) {
            error.put("error", "Telefone já está em uso.");
        } else {
            error.put("error", "Violação de integridade nos dados.");
        }

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    // 🔴 Tratamento para ResponseStatusException (ex: 404, 403 etc customizados)
    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<Map<String, String>> handleResponseStatusException(ResponseStatusException ex) {
        Map<String, String> error = Map.of("error", ex.getReason() != null ? ex.getReason() : "Erro não especificado");
        return new ResponseEntity<>(error, ex.getStatusCode());
    }

    // ⚠️ Fallback para qualquer exceção não tratada
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> handleGenericException(Exception ex) {
        Map<String, String> error = Map.of("error", "Erro interno do servidor: " + ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // 🔴 Match já finalizada
    @ExceptionHandler(MatchAlreadyFinishedException.class)
    public ResponseEntity<Map<String, String>> handleMatchAlreadyFinished(MatchAlreadyFinishedException ex) {
        Map<String, String> error = Map.of("error", ex.getMessage());
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(error);
    }

    // 🔴 groupId não encontrado (PlayerGroupNotFoundException)
    @ExceptionHandler(PlayerGroupNotFoundException.class)
    public ResponseEntity<Map<String, String>> handlePlayerGroupNotFound(PlayerGroupNotFoundException ex) {
        Map<String, String> error = Map.of("error", ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

}
