package com.square.shoppingcart.controller;

import com.square.shoppingcart.model.dto.ShoppingCartItem;
import com.square.shoppingcart.model.entity.Order;
import com.square.shoppingcart.model.entity.OrderProduct;
import com.square.shoppingcart.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Rest shopping cart controller for handling client requests on its shopping cart
 * @author Sandra Dongbo Tang
 * @since 2022-10-16
 */

@Scope("request")
@RestController
public class ShoppingCartController {

    @Autowired
    private ShoppingCartService shoppingCartService;

    public ShoppingCartController() {}

    @GetMapping( "/cart/{id}")
    public List<OrderProduct> listItems(@PathVariable Long orderId) {
        return  shoppingCartService.listItems(orderId);
    }

    /**
     * Add an item with quantity > 0 to the shopping cart
     * If shopping cart doesn't exist, backend creates a new order with status 'draft'
     * and add the item (product) the order and increases quantity_reserved by the quantity selected
     *
     * Example Json sent to this method as the post request payload:
     * { orderId: 1, productId: 6, quantity:1, note:"This is note." }
    */
    @PostMapping( "/cart/add-item")
    public Order addItem(@RequestBody ShoppingCartItem item) {
        return  shoppingCartService.addItem(item);
    }
}
