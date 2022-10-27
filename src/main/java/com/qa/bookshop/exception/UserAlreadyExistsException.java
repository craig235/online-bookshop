package com.qa.bookshop.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT, reason = "User already exists with these details")
public class UserAlreadyExistsException extends Exception {

}