package com.qa.bookshop.service;

import com.qa.bookshop.entity.User;
import com.qa.bookshop.exception.InvalidCredentialsException;
import com.qa.bookshop.exception.UserAlreadyExistsException;

public interface UserService {
    User login(User user) throws InvalidCredentialsException;
    User save(User user) throws UserAlreadyExistsException;
}