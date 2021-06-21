package com.example.fanficapi.service;

import com.example.fanficapi.dto.UserDto;
import com.example.fanficapi.dto.simple.UserShortInfoDto;
import com.example.fanficapi.enums.RoleName;
import com.example.fanficapi.exception.UserNotFoundException;
import com.example.fanficapi.mapper.Mapper;
import com.example.fanficapi.model.Role;
import com.example.fanficapi.model.User;
import com.example.fanficapi.pojo.SignUpRequest;
import com.example.fanficapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService extends AbstractService<User, Long, UserShortInfoDto, UserDto> {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleService roleService;

    @Autowired
    private Mapper mapper;


    @Override
    public void saveToDB(User user) {
        userRepository.save(user);
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Can not find user with this id: " + id));
    }

    @Override
    public List<UserDto> getAllDto() {
        return null;
    }

    @Override
    public UserShortInfoDto getSimpleDtoById(Long id) {
        return null;
    }

    public UserDto getDtoById(Long id) {
        Optional<User> user = userRepository.findById(id);
        System.err.println(user.get().toString());
        return mapper.userToDto(userRepository.findById(id).get());
    }

    @Override
    public User findByName(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Can not find user with this username: " + username));
    }

    @Override
    public User update(User user) {
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

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
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
            roles.add(roleService.findByRoleName(RoleName.ROLE_USER));
        } else {
            requestRoleNames.forEach(role -> {
                if (role.equals("admin")) {
                    roles.add(roleService.findByRoleName(RoleName.ROLE_ADMIN));
                } else {
                    roles.add(roleService.findByRoleName(RoleName.ROLE_USER));
                }
            });
        }
        user.setRoles(roles);
        return user;
    }
}
