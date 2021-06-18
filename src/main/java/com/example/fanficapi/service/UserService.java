package com.example.fanficapi.service;

import com.example.fanficapi.enums.RoleName;
import com.example.fanficapi.model.Role;
import com.example.fanficapi.model.User;
import com.example.fanficapi.pojo.SignUpRequest;
import com.example.fanficapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
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

    public User findByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Can not find user with this username: " + username));
    }

    public Boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    public Boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    public User getUserFromSignUpRequest(SignUpRequest request) {
        User user =  new User(request.getUsername(), request.getEmail());
        Set<String> requestRoleNames = request.getRoles();
        Set<Role> roles = new HashSet<>();
        if (requestRoleNames == null) {
            roles.add(roleService.findByName(RoleName.ROLE_USER));
        } else {
            requestRoleNames.forEach(role -> {
                if (role.equals("admin")) {
                    roles.add(roleService.findByName(RoleName.ROLE_ADMIN));
                }else{
                    roles.add(roleService.findByName(RoleName.ROLE_USER));
                }
            });
        }
        user.setRoles(roles);
        return user;
    }
}
