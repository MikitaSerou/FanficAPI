package com.example.fanficapi.service;

import com.example.fanficapi.enums.RoleName;
import com.example.fanficapi.exception.UserNotFoundException;
import com.example.fanficapi.model.Role;
import com.example.fanficapi.model.User;
import com.example.fanficapi.pojo.SignUpRequest;
import com.example.fanficapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleService roleService;

    public void saveToStorage(User user) {
        userRepository.save(user);
    }

    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User with this id (" + id + ") was not found"));
    }

    public User findByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Can not find user with this username: " + username));
    }

    public User updateUser(User user) {
        //crutch but idk how
        User userForUpdate = findById(user.getId());
        if (user.getUsername() != null &
                !userForUpdate.getUsername().equals(user.getUsername()) & !existsByUsername(user.getUsername())) {
            userForUpdate.setUsername(user.getUsername());
        }
        if (user.getEmail() != null
                & !userForUpdate.getEmail().equals(user.getEmail()) & !existsByEmail(user.getEmail())) {
            userForUpdate.setEmail(user.getEmail());
        }
        return userRepository.save(userForUpdate);
    }

    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public Boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    public Boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    public User getUserFromSignUpRequest(SignUpRequest request) {
        User user = new User(request.getUsername(), request.getEmail());
        Set<String> requestRoleNames = request.getRoles();
        Set<Role> roles = new HashSet<>();
        if (requestRoleNames == null) {
            roles.add(roleService.findByName(RoleName.ROLE_USER));
        } else {
            requestRoleNames.forEach(role -> {
                if (role.equals("admin")) {
                    roles.add(roleService.findByName(RoleName.ROLE_ADMIN));
                } else {
                    roles.add(roleService.findByName(RoleName.ROLE_USER));
                }
            });
        }
        user.setRoles(roles);
        return user;
    }
}
