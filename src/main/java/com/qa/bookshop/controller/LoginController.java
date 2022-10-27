package com.qa.bookshop.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.qa.bookshop.entity.User;
import com.qa.bookshop.exception.InvalidCredentialsException;
import com.qa.bookshop.exception.UserAlreadyExistsException;
import com.qa.bookshop.service.LoginService;
import com.qa.bookshop.service.LoginServiceImpl;

@RestController
@RequestMapping("api/v1")
public class LoginController {

    @Autowired
    //@Qualifier("LoginServiceImpl")
    LoginService loginService;

    ResponseEntity<?> responseEntity;

    @PostMapping("/user/login")
    public ResponseEntity<?> login(@Valid @RequestBody User user) throws InvalidCredentialsException {
        try {
            User userRecord = this.loginService.login(user);
            responseEntity = new ResponseEntity<>(userRecord, HttpStatus.OK);
        } catch (InvalidCredentialsException e) {
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            responseEntity = new ResponseEntity<>("An internal error occurred. Please try again.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @PostMapping("/user/signup")
    public ResponseEntity<?> signup(@Valid @RequestBody User user) throws UserAlreadyExistsException {
        try {
            User userRecord = this.loginService.signup(user);
            System.out.println("Added user: " + userRecord);
            responseEntity = new ResponseEntity<>(userRecord, HttpStatus.CREATED);
        } catch (UserAlreadyExistsException e) {
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            responseEntity = new ResponseEntity<>("An internal error occurred. Please try again.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }
}