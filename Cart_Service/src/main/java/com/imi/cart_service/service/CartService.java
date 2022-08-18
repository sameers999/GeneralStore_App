package com.imi.cart_service.service;

import com.imi.cart_service.dto.CartDTO;
import com.imi.cart_service.exception.CartException;
import com.imi.cart_service.model.Cart;
import com.imi.cart_service.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Service
public class CartService implements ICartService{
    @Autowired
    CartRepository repo;

    @Autowired
    RestTemplate restTemplate;

    @Override
    public Cart insertToCart(@Valid CartDTO dto) {
        Cart cart = new Cart(dto);
        repo.save(cart);
        return cart;
    }

    @Override
    public List<Cart> getAllCarts() {
        List<Cart> list = repo.findAll();
        if(list.isEmpty()) {
            throw new NullPointerException("there are no Carts inserted yet");
        }
        return list;
    }

    @Override
    public Cart getCartByID(Integer cartId) {
        Optional<Cart> cart = repo.findById(cartId);
        if(cart.isEmpty()) {
            throw new CartException("There are no carts with given id");
        }
        return cart.get();
    }

    @Override
    public Cart updateById(Integer cartId, @Valid CartDTO dto) {
        Optional<Cart> cart = repo.findById(cartId);
        if(cart.isPresent()) {
            Cart newCart = new Cart(cartId,dto);
            repo.save(newCart);
            return newCart;
        }
        else {
            throw new CartException("cart not found");
        }
    }

    @Override
    public Cart deleteById(Integer cartId) {
        Optional<Cart> cart = repo.findById(cartId);
        if(cart.isEmpty()) {
            throw new CartException("Invalid CartID..please input valid Id");
        }
        repo.deleteById(cartId);
        return cart.get();
    }
}
