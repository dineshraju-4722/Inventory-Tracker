package com.inventory_tracker.backend.controller;

import com.inventory_tracker.backend.payload.UserCartRequestDTO;
import com.inventory_tracker.backend.payload.UserCartResponseDTO;
import com.inventory_tracker.backend.service.UserCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserCartController {

    @Autowired
    private UserCartService userCartService;

    @PostMapping("/usercart")
    public ResponseEntity<UserCartResponseDTO> addUserCart(@RequestBody UserCartRequestDTO userCartRequestDTO){
         UserCartResponseDTO userCartResponseDTO = userCartService.addUserCart(userCartRequestDTO);
        return new ResponseEntity<>(userCartResponseDTO , HttpStatus.CREATED);
    }

    @GetMapping("/userscart")
    public ResponseEntity<List<UserCartResponseDTO>> getUsersCart(){
        List<UserCartResponseDTO> userCartResponseDTO = userCartService.getUsersCart();
        return new ResponseEntity<>(userCartResponseDTO , HttpStatus.OK);
    }

    @GetMapping("/usercart/{userId}")
    public ResponseEntity<List<UserCartResponseDTO>> getUserCartById(@PathVariable Long userId){
        List<UserCartResponseDTO> userCartResponseDTO = userCartService.getUserCartById(userId);
        return new ResponseEntity<>(userCartResponseDTO , HttpStatus.OK);
    }

}
