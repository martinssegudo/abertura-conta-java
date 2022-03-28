package br.com.gerenciadorclientesjava.services.exceptions.response;

import lombok.Getter;

import java.util.Date;

@Getter
public class ExceptionResponse {

    private final Date timestamp;
    private final String message;
    private final String details;
    private final String httpCodeMessage;

    public ExceptionResponse(Date timestamp, String message, String details, String httpCodeMessage) {
        super();
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
        this.httpCodeMessage=httpCodeMessage;
    }
}
