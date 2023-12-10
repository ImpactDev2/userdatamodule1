package com.shopping.userDataModule1.exception;

import com.shopping.userDataModule1.response.Response;
import com.shopping.userDataModule1.response.ValidationErrorResponse;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UsernameNotFoundException1.class)
    public ResponseEntity<Response> handleUsernameNotFoundException(UsernameNotFoundException1 ex){
        Response res=new Response();
        res.setStatusCode(404);
        res.setResponseMessage("Username Not Found");
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }
    @ExceptionHandler(InvalidCredentials.class)
    public ResponseEntity<Response> handleInvalidCredentials(InvalidCredentials ex){
        Response res=new Response();
        res.setStatusCode(101);
        res.setResponseMessage("Invalid Credentials");
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }
    @ExceptionHandler(InvalidTokenException.class)
    public ResponseEntity<Response> handleInvalidTokenException(InvalidTokenException ex){
        System.out.println("invalid from exception");
        Response responseToken =new Response();
        responseToken.setResponseMessage("Invalid Token");
        responseToken.setStatusCode(1001);
        return ResponseEntity.status(HttpStatus.OK).body(responseToken);
    }
    @ExceptionHandler(TokenExpiredException.class)
    public ResponseEntity<Response> handleTokenExpiredException(TokenExpiredException ex){
        System.out.println("invalid from exception");
        Response responseToken =new Response();
        responseToken.setResponseMessage("Invalid Token");
        responseToken.setStatusCode(1001);
        return ResponseEntity.status(HttpStatus.OK).body(responseToken);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationErrorResponse> handleInvalidRequestException(MethodArgumentNotValidException ex){
    	Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
            errors.put(error.getField(), error.getDefaultMessage())
        );
        System.out.println(errors);
        ValidationErrorResponse res = new ValidationErrorResponse();
        res.setErrorCode(400);
        res.setErrorMessage("Invalid Request");
        res.setErrorData(errors);
        return ResponseEntity.badRequest().body(res);
    }

}
