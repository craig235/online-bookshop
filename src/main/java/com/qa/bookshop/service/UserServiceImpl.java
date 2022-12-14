package com.qa.bookshop.service;

import com.qa.bookshop.entity.User;
import com.qa.bookshop.exception.InvalidCredentialsException;
import com.qa.bookshop.exception.UserAlreadyExistsException;
import com.qa.bookshop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//@Component("LoginServiceImpl")
@Service("UserServiceImpl")
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public User login(User user) throws InvalidCredentialsException {
        User userRecord = this.userRepository.findByUsername(user.getUsername());
        if(userRecord == null) {
            throw new InvalidCredentialsException();
        }

        if (!userRecord.getPassword().equals(user.getPassword().trim())) {
            throw new InvalidCredentialsException();
        }

        userRecord.setPassword("LoginOK");
        return userRecord;
    }

    @Override
    public User save(User user) throws UserAlreadyExistsException {
        User userRecord = this.userRepository.findByUsername(user.getUsername());
        if(userRecord != null) {
            throw new UserAlreadyExistsException();
        }

        userRecord = this.userRepository.save(user);
        userRecord.setPassword("");
        return userRecord;
    }
}