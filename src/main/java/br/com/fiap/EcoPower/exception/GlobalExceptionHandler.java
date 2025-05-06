package br.com.fiap.EcoPower.exception;

import br.com.fiap.EcoPower.model.ErrorMessageModel;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // Remove a duplicação e mantemos o tratamento correto para a exceção
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorMessageModel> handleValidationExceptions(MethodArgumentNotValidException ex) {
        // Coleta os erros de validação
        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.toList());

        // Cria um objeto ErrorMessageModel para enviar a resposta
        ErrorMessageModel errorResponse = new ErrorMessageModel("Dados fornecidos estão em formato inválido", errors);

        // Retorna a resposta com status 400 e o modelo de erro
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    // Trata outros tipos de exceções
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, String>> handleIllegalArgument(IllegalArgumentException ex) {
        return ResponseEntity.badRequest().body(Map.of("erro", ex.getMessage()));
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Map<String, String>> handleJsonParseError(HttpMessageNotReadableException ex) {
        Throwable cause = ex.getCause();
        if (cause instanceof InvalidFormatException) {
            return ResponseEntity.badRequest().body(Map.of("erro", "Valor inválido no corpo da requisição."));
        }
        return ResponseEntity.badRequest().body(Map.of("erro", "Requisição malformada."));
    }
}
