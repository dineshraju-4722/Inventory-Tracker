package com.inventory_tracker.backend.service;

import com.inventory_tracker.backend.exceptions.ResourceNotFoundException;
import com.inventory_tracker.backend.model.Product;
import com.inventory_tracker.backend.model.User;
import com.inventory_tracker.backend.model.UserCart;
import com.inventory_tracker.backend.payload.*;
import com.inventory_tracker.backend.repositories.ProductRepository;
import com.inventory_tracker.backend.repositories.UserCartRepository;
import com.inventory_tracker.backend.repositories.UserRepository;
import com.inventory_tracker.backend.util.AuthUtil;
import org.jspecify.annotations.NonNull;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserCartServiceImpl implements  UserCartService {
    private final ModelMapper modelMapper;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final UserCartRepository userCartRepository;
    private final AuthUtil authUtil;

    public UserCartServiceImpl(ModelMapper modelMapper, ProductRepository productRepository, UserRepository userRepository, UserCartRepository userCartRepository, AuthUtil authUtil) {
        this.modelMapper = modelMapper;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
        this.userCartRepository = userCartRepository;
        this.authUtil = authUtil;
    }

    @Override
    public UserCartResponseDTO addUserCart(UserCartRequestDTO userCartRequestDTO) {
        UserCart userCart = modelMapper.map(userCartRequestDTO, UserCart.class);
        Product product = productRepository.findById(userCartRequestDTO.getProductId()).orElseThrow(()->new ResourceNotFoundException("PRODUCT","productId",userCartRequestDTO.getProductId()));
        User user = authUtil.getLoggedInUser();
        userCart.setUser(user);
        userCart.setProduct(product);
        UserCart savedUserCart = userCartRepository.save(userCart);
        return getUserCartResponseDTO(savedUserCart);
    }

   

    @Override
    public List<UserCartResponseDTO> getUsersCart() {
        List<UserCart> usersCart = userCartRepository.findAll();
        return usersCart.stream().map(this::getUserCartResponseDTO).toList();
    }

    @Override
    public List<UserCartResponseDTO> getUserCart() {
        Long userId = authUtil.getLoggedInUserId();
        List<UserCart> userCartProducts = userCartRepository.findByUser_UserId(userId);
        return userCartProducts.stream().map(this::getUserCartResponseDTO).toList();
    }

    @NonNull
    private  UserCartResponseDTO getUserCartResponseDTO(UserCart savedUserCart) {
        Product product = savedUserCart.getProduct();
        UserCartResponseDTO userCartResponse = modelMapper.map(savedUserCart, UserCartResponseDTO.class);
        ProductResponseDTO productResponseDTO = modelMapper.map(product, ProductResponseDTO.class);
        productResponseDTO.setCategory(modelMapper.map(product.getCategory(), CategoryDTO.class));
        productResponseDTO.setSupplier(modelMapper.map(product.getSupplier(), SupplierDTO.class));
        userCartResponse.setProduct(productResponseDTO);
        return userCartResponse;
    }
}
