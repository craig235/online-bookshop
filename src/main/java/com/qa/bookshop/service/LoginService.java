package com.qa.bookshop.service;

import com.qa.bookshop.entity.User;
import com.qa.bookshop.exception.InvalidCredentialsException;
import com.qa.bookshop.exception.UserAlreadyExistsException;

public interface LoginService {
    User login(User user) throws InvalidCredentialsException;
    User signup(User user) throws UserAlreadyExistsException;
}