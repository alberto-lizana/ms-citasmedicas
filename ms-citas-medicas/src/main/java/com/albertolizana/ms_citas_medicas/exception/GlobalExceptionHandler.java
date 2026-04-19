package com.albertolizana.ms_citas_medicas.exception;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.albertolizana.ms_citas_medicas.dto.ErrorResponseDTO;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseDTO> handleValidation(MethodArgumentNotValidException ex) {

        List<String> errors = ex.getBindingResult()
                            .getFieldErrors()
                            .stream()
                            .map(err -> err.getField() + ": " + err.getDefaultMessage())
                            .toList();

        ErrorResponseDTO errorResponse = ErrorResponseDTO.builder()
                                            .mensaje("Error de validación")
                                            .error(errors.toString())
                                            .status(HttpStatus.BAD_REQUEST.value())
                                            .timestamp(LocalDateTime.now())
                                            .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }
    

    @ExceptionHandler({ResourceNotFoundException.class})
    public ResponseEntity<ErrorResponseDTO> handleResourceNotFound(ResourceNotFoundException ex) {

    ErrorResponseDTO errorResponse = ErrorResponseDTO.builder()
                                        .mensaje("Recurso no encontrado")
                                        .error(ex.getMessage())
                                        .status(HttpStatus.NOT_FOUND.value())
                                        .timestamp(LocalDateTime.now())
                                        .build();

    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
}

    @ExceptionHandler({NoHandlerFoundException.class})
    public ResponseEntity<ErrorResponseDTO> handleNotFound(NoHandlerFoundException ex) {

        ErrorResponseDTO errorResponse = ErrorResponseDTO.builder()
                                            .mensaje("Recurso no encontrado")
                                            .error(ex.getMessage())
                                            .status(HttpStatus.NOT_FOUND.value())
                                            .timestamp(LocalDateTime.now())
                                            .build();

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDTO> handleGeneralError(Exception ex) {

        ErrorResponseDTO errorResponse = ErrorResponseDTO.builder()
                                            .mensaje("Error interno del servidor")
                                            .error(ex.getMessage())
                                            .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                                            .timestamp(LocalDateTime.now())
                                            .build();

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }
}
