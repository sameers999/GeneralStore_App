package com.imi.cart_service.controller;

import com.imi.cart_service.dto.CartDTO;
import com.imi.cart_service.dto.ResponseDTO;
import com.imi.cart_service.model.Cart;
import com.imi.cart_service.service.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    ICartService cartService;

    @PostMapping("/insert")
    public ResponseEntity<ResponseDTO> insertGeneralStoreData(@Valid @RequestBody CartDTO dto){
        Cart cart = cartService.insertToCart(dto);
        ResponseDTO response = new ResponseDTO("Cart Added to General Store", cart);
        return new ResponseEntity<ResponseDTO>(response, HttpStatus.OK);
    }
    @GetMapping("/getAll")
    public ResponseEntity<ResponseDTO> getAllCarts(){
        List<Cart> list = cartService.getAllCarts();
        ResponseDTO response = new ResponseDTO("All carts in General Store",list );
        return new ResponseEntity<ResponseDTO>(response,HttpStatus.OK);
    }
    @GetMapping("/findbyid/{cartId}")
    public ResponseEntity<ResponseDTO> getbookByID(@PathVariable Integer cartId){
        Cart cart = cartService.getCartByID(cartId);
        ResponseDTO response = new ResponseDTO("Requested Cart : ",cart);
        return new ResponseEntity<ResponseDTO>(response,HttpStatus.OK);
    }
    @PutMapping("/update/{cartId}")
    public ResponseEntity<ResponseDTO> updateById(@PathVariable  Integer cartId,@Valid @RequestBody CartDTO dto){
        Cart cart = cartService.updateById(cartId,dto);
        ResponseDTO response = new ResponseDTO("cart updated : ", cart);
        return new ResponseEntity<ResponseDTO>(response,HttpStatus.OK);
    }

    @DeleteMapping("/delete/{cartId}")
    public ResponseEntity<ResponseDTO> deleteById(@PathVariable  Integer cartId){
        Cart cart = cartService.deleteById(cartId);
        ResponseDTO response = new ResponseDTO("cart deleted successfully",cart);
        return new ResponseEntity<ResponseDTO>(response,HttpStatus.OK);
    }
}
