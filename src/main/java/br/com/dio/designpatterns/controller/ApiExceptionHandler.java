package br.com.dio.designpatterns.controller;

import br.com.dio.designpatterns.service.ClientService.ClientNotFoundException;
import java.util.Collections;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class ApiExceptionHandler {
    @ExceptionHandler(ClientNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Object notFound(RuntimeException error) { return Collections.singletonMap("error", error.getMessage()); }
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Object invalid(IllegalArgumentException error) { return Collections.singletonMap("error", error.getMessage()); }
}

