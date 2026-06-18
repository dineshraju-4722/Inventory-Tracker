
package com.inventory_tracker.backend.service;

import com.inventory_tracker.backend.model.User;
import com.inventory_tracker.backend.payload.UserDTO;
import com.inventory_tracker.backend.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final ModelMapper modelMapper;
    private final UserRepository userRepository;

    public UserServiceImpl(ModelMapper modelMapper, UserRepository userRepository) {
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
    }

    @Override
    public UserDTO addUser(UserDTO userDTO) {
        User user = modelMapper.map(userDTO, User.class);
        User saved = userRepository.save(user);
        return modelMapper.map(saved, UserDTO.class);
    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map( user -> modelMapper.map(user, UserDTO.class) ).toList();
    }
}
