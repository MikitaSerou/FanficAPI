package com.example.fanficapi.controller;

import com.example.fanficapi.dto.UserDto;
import com.example.fanficapi.model.User;
import com.example.fanficapi.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/all")
    // @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.findAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/page/{id}")
    public ResponseEntity<UserDto> getUserDtoById(@PathVariable("id") Long id) {
        UserDto user = userService.getDtoById(id);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(userService.getDtoById(id), HttpStatus.OK);
    }


    @PutMapping("/update")
    public ResponseEntity<?> updateUser(@RequestBody UserDto userDto) {
        UserDto updatedUser = userService.updateUserFromDto(userDto);
        if (updatedUser != null) {
            return new ResponseEntity<>(userService.updateUserFromDto(userDto), HttpStatus.OK);
        }
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body("Username (" + userDto.getUsername() + " or email (" + userDto.getEmail() + ") already used");
    }

    @DeleteMapping("/delete/{id}")
    //@PreAuthorize("hasRole('ADMIN')")
    public void delete(@PathVariable("id") Long id) {
        userService.deleteById(id);
    }
}
