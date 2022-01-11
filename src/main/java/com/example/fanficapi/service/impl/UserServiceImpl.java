package com.example.fanficapi.service.impl;

import com.example.fanficapi.enums.RoleName;
import com.example.fanficapi.exception.UserException;
import com.example.fanficapi.model.User;
import com.example.fanficapi.payload.SignUpRequest;
import com.example.fanficapi.repository.UserRepository;
import com.example.fanficapi.service.RoleService;
import com.example.fanficapi.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleService roleService;

    @Override
    public void saveToDB(User user) {
        userRepository.save(user);
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

    @Transactional
    @Override
    public String getUsernameByEmail(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        return user.map(User::getUsername).orElse(null);
    }

    @Override
    public User findByEmail(String email) throws UserException {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UserException("Can not find user with this email: " + email));
    }

    @Override
    public User update(User user) {
        return userRepository.save(user);
    }

    @Override
    public Boolean checkUserDtoForUpdate(User updatableUser, User user) {
        if (existsByUsername(updatableUser.getUsername()) & !user.getUsername().equals(updatableUser.getUsername())
                | existsByEmail(updatableUser.getEmail()) & !user.getEmail().equals(updatableUser.getEmail())) {
            log.error("Username (" + updatableUser.getUsername() + " or email (" + updatableUser.getEmail() + ") already used");
            return false;
        }
        return true;
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public Boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public User getUserFromSignUpRequest(SignUpRequest request) {
        User user = new User(request.getUsername(), request.getEmail(), request.getPassword(), request.getBirthDate());
        user.setRoles(new HashSet<>(Collections.singleton(roleService.findByRoleName(RoleName.ROLE_USER))));
        return user;
    }
}
