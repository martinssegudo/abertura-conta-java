package br.com.gerenciadorclientesjava.services.exceptions.handlers;

import br.com.gerenciadorclientesjava.services.exceptions.response.ExceptionResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.WebRequest;


public interface CustomizedResponseEntityExceptionHandler {

    ResponseEntity<ExceptionResponse> handleBadRequestException(Exception ex, WebRequest request);

    ResponseEntity<ExceptionResponse> handleNotFoundException(Exception ex, WebRequest request);
}
