package com.example.fanficapi.service.impl;

import com.example.fanficapi.exception.UserException;
import com.example.fanficapi.model.User;
import com.example.fanficapi.repository.UserRepository;
import com.example.fanficapi.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;


    @Override
    @Transactional
    public void saveToDB(User user) {
        if (isNewBeeTookUsernameOrEmail(user)) {
            throw new UserException("Can not save new user with this username or email");
        }
        userRepository.save(user);
    }

    private boolean isNewBeeTookUsernameOrEmail(User user) {
        if (user.getId() == null) {
            return userRepository.existsByUsername(user.getUsername()) || userRepository.existsByEmail(user.getEmail());
        } else {
            return false;
        }
    }

    @Override
    public User findById(Long id) throws UserException {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserException("Can not find user with this id: " + id));
    }

    @Override
    public User findByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Can not find user with this username: " + username));
    }

    @Override
    public User findByEmail(String email) throws UserException {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UserException("Can not find user with this email: " + email));
    }

    @Override
    public User update(User user) {
        if (user.getId() == null) {
            throw new UserException("User id is null");
        } else if (userRepository.existsById(user.getId())) {
            return userRepository.save(user);
        }
        throw new UserException("User with this id is not exist");
    }


    @Override
    public void deleteById(Long id) {
        log.info("Delete user with id: " + id);
        userRepository.deleteById(id); //TODO don't forget cascade delete
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Boolean existsByUsername(String username) {
        if (userRepository.existsByUsername(username)) {
            return true;
        }
        log.error("User with username: " + username + " is not exist");
        return false;
    }

    @Override
    public Boolean existsByEmail(String email) {
        if (userRepository.existsByEmail(email)) {
            return true;
        }
        log.error("User with email: " + email + " is not exist");
        return false;
    }

}
