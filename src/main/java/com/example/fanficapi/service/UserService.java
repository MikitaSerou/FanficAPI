package com.example.fanficapi.service;

import com.example.fanficapi.dto.user.UserDto;
import com.example.fanficapi.dto.user.UserShortInfoDto;
import com.example.fanficapi.enums.RoleName;
import com.example.fanficapi.exception.UserException;
import com.example.fanficapi.mapper.Mapper;
import com.example.fanficapi.model.User;
import com.example.fanficapi.payload.SignUpRequest;
import com.example.fanficapi.repository.UserRepository;
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
public class UserService extends AbstractService<User, Long, UserShortInfoDto, UserDto> {

    private final UserRepository userRepository;
    private final RoleService roleService;
    private final Mapper mapper;

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
    public List<UserDto> getAllDto() {
        return null;
    }

    @Override
    public UserShortInfoDto getSimpleDtoById(Long id) {
        return null;
    }

    public UserDto getDtoById(Long id) {
        return mapper.userToDto(userRepository.findById(id).orElse(null));
    }

    @Override
    public User findByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Can not find user with this username: " + username));
    }

    @Transactional
    public String getUsernameByEmail(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        return user.map(User::getUsername).orElse(null);
    }

    public User findByEmail(String email) throws UserException {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UserException("Can not find user with this email: " + email));
    }

    @Override
    public User update(User user) {
        return userRepository.save(user);
    }

    public UserDto updateUserFromDto(UserDto dto) {
        try {
            User user = findById(dto.getId());
            if (checkUserDtoForUpdate(dto, user)) {
                mapper.updateUserFromUserDto(dto, user);
                return mapper.userToDto(update(user));
            }
        } catch (UserException exception) {
            exception.printStackTrace();
        }
        return null;
    }

    public Boolean checkUserDtoForUpdate(UserDto dto, User user) {
        if (existsByUsername(dto.getUsername()) & !user.getUsername().equals(dto.getUsername())
                | existsByEmail(dto.getEmail()) & !user.getEmail().equals(dto.getEmail())) {
            log.error("Username (" + dto.getUsername() + " or email (" + dto.getEmail() + ") already used");
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

    public Boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    public Boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    public User getUserFromSignUpRequest(SignUpRequest request) {
        User user = new User(request.getUsername(), request.getEmail(), request.getPassword(), request.getBirthDate());
        user.setRoles(new HashSet<>(Collections.singleton(roleService.findByRoleName(RoleName.ROLE_USER))));
        return user;
    }
}
