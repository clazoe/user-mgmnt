package com.canvia.usermgmnt.exception;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AccountStatusException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ProblemDetail handleSecurityException(Exception exception) {
        ProblemDetail detalleError = null;

        if (exception instanceof BadCredentialsException) {
            detalleError = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(401), exception.getMessage());
            detalleError.setProperty("description", "El nombre de usuario o password es incorrecto");

            return detalleError;
        }

        if (exception instanceof AccountStatusException) {
            detalleError = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(403), exception.getMessage());
            detalleError.setProperty("description", "La cuenta del usuario esta bloqueada");
        }

        if (exception instanceof AccessDeniedException) {
            detalleError = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(403), exception.getMessage());
            detalleError.setProperty("description", "No esta autorizado para acceder a este recurso");
        }

        if (exception instanceof SignatureException) {
            detalleError = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(403), exception.getMessage());
            detalleError.setProperty("description", "La firma JWT no es valida");
        }

        if (exception instanceof ExpiredJwtException) {
            detalleError = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(403), exception.getMessage());
            detalleError.setProperty("description", "El token JWT ha expirado");
        }


        if (detalleError == null) {
            detalleError = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(500), exception.getMessage());
            detalleError.setProperty("description", "Unknown internal server error.");
        }


        return detalleError;
    }
}
