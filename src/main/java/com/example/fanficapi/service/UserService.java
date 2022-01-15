package com.example.fanficapi.service;

import com.example.fanficapi.exception.UserException;
import com.example.fanficapi.model.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public interface UserService {

    void saveToDB(User user);

    User findById(Long id) throws UserException;

    User findByUsername(String username) throws UsernameNotFoundException;

    User findByEmail(String email) throws UserException;

    User update(User user);

    void deleteById(Long id);

    List<User> findAll();

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

}
