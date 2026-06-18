package com.inventory_tracker.backend.controller;

import com.inventory_tracker.backend.payload.UserDTO;
import com.inventory_tracker.backend.service.UserService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/user")
    public ResponseEntity<UserDTO> addUser(@RequestBody UserDTO userDTO){
        UserDTO responseUserDTO = userService.addUser(userDTO);
        return new ResponseEntity<>(responseUserDTO, HttpStatus.OK);
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserDTO>> getAllUsers(){
        List<UserDTO> userDTOs = userService.getAllUsers();
        return new ResponseEntity<>(userDTOs,HttpStatus.OK);
    }
}
