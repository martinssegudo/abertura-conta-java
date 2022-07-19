package br.com.gerenciadorclientesjava.services.exceptions.handlers.impl;

import br.com.gerenciadorclientesjava.services.exceptions.*;
import br.com.gerenciadorclientesjava.services.exceptions.handlers.CustomizedResponseEntityExceptionHandler;
import br.com.gerenciadorclientesjava.services.exceptions.response.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandlerImpl implements CustomizedResponseEntityExceptionHandler {

    @ExceptionHandler({CNPJException.class,
            ContaDuplicadaException.class,
            CPFException.class,
            DataMenorException.class,
            DataParseException.class,
            DocumentoException.class,
            DocumentoNuloException.class,
            NomeException.class,
            RgDiferenteNoveCaracteresException.class,
            RgInvalidoException.class,
            RgNuloException.class,
            SenhaInvalidaException.class,
            SenhaMenorSeisCaracteresException.class,
            ValidaDocumentoPessoaContaException.class
    })
    public ResponseEntity<ExceptionResponse> handleBadRequestException(Exception ex, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
                request.getDescription(false), HttpStatus.BAD_REQUEST.getReasonPhrase());

        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({
            ContaInexistenteException.class,
            LoginException.class
    })
    public ResponseEntity<ExceptionResponse> handleNotFoundException(Exception ex, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
                request.getDescription(false), HttpStatus.NOT_FOUND.getReasonPhrase());

        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
    }

}
