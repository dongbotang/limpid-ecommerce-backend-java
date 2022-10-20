package com.square.shoppingcart;

import com.square.shoppingcart.controller.ShoppingCartController;
import com.square.shoppingcart.model.dto.ShoppingCartItem;
import com.square.shoppingcart.model.entity.Order;
import com.square.shoppingcart.model.entity.OrderProduct;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ShoppingCartControllerTests {
    private static final Logger log = LoggerFactory.getLogger(ShoppingCartControllerTests.class);
    private final long PRODUCT_ID_GOOGLE_HOMES = 1L;
    private final long PRODUCT_ID_MACBOOK_PRO = 2L;
    private final long PRODUCT_ID_ALEXA_SPEAKER = 3L;
    private final long PRODUCT_ID_RASPBERRY_PI = 4L;
    private final BigDecimal PRODUCT_PRICE_GOOGLE_HOMES = BigDecimal.valueOf(49.99);
    private final BigDecimal PRODUCT_PRICE_MACBOOK_PRO = BigDecimal.valueOf(5399.99);
    private final BigDecimal PRODUCT_PRICE_ALEXA_SPEAKER  = BigDecimal.valueOf(109.50);
    private final BigDecimal PRODUCT_PRICE_RASPBERRY_PI = BigDecimal.valueOf(30.00);

    @Autowired
    private ShoppingCartController shoppingCartController;

    @Test
    public void trigger2ProductGet1FreePromotionShouldSuccess() {
        int quantity = 2;
        ShoppingCartItem item = new ShoppingCartItem(11L, PRODUCT_ID_GOOGLE_HOMES, quantity,
                "Test Buy 3 Google Homes for the price of 2");
        Order order = shoppingCartController.addItem(item);
        assertNotNull(order);
        List<OrderProduct> list = shoppingCartController.listItems(order.getId());
        assertEquals(2, list.size());
        assertEquals(PRODUCT_ID_GOOGLE_HOMES, list.get(0).getProduct().getId());
        assertEquals(list.get(0).getProduct().getPrice(), list.get(0).getPrice());
        assertEquals(quantity, list.get(0).getQuantity());
        assertEquals(PRODUCT_ID_GOOGLE_HOMES, list.get(1).getProduct().getId());
        assertEquals(0, list.get(1).getPrice().intValue());
        assertEquals(1, list.get(1).getQuantity());
    }

    @Test
    public void trigger4ProductGet2FreePromotionShouldSuccess() {
        int quantity = 4;
        ShoppingCartItem item = new ShoppingCartItem(2L, PRODUCT_ID_GOOGLE_HOMES, quantity,
                "Test Buy 6 Google Homes for the price of 4");
        Order order = shoppingCartController.addItem(item);
        assertNotNull(order);
        List<OrderProduct> list = shoppingCartController.listItems(order.getId());
        assertEquals(2, list.size());
        assertEquals(PRODUCT_ID_GOOGLE_HOMES, list.get(0).getProduct().getId());
        assertEquals(list.get(0).getProduct().getPrice(), list.get(0).getPrice());
        assertEquals(quantity, list.get(0).getQuantity());
        assertEquals(PRODUCT_ID_GOOGLE_HOMES, list.get(1).getProduct().getId());
        assertEquals(0, list.get(1).getPrice().intValue());
        assertEquals(2, list.get(1).getQuantity());
    }

    @Test
    public void trigger5ProductGet2FreePromotionShouldSuccess() {
        int quantity = 5;
        ShoppingCartItem item = new ShoppingCartItem(3L, PRODUCT_ID_GOOGLE_HOMES, quantity,
                "Test Buy 7 Google Homes for the price of 5");
        Order order = shoppingCartController.addItem(item);
        assertNotNull(order);
        List<OrderProduct> list = shoppingCartController.listItems(order.getId());
        assertEquals(2, list.size());
        assertEquals(PRODUCT_ID_GOOGLE_HOMES, list.get(0).getProduct().getId());
        assertEquals(list.get(0).getProduct().getPrice(), list.get(0).getPrice());
        assertEquals(quantity, list.get(0).getQuantity());
        assertEquals(PRODUCT_ID_GOOGLE_HOMES, list.get(1).getProduct().getId());
        assertEquals(0, list.get(1).getPrice().intValue());
        assertEquals(2, list.get(1).getQuantity());
    }
//
    @Test
    public void trigger1ProductGet1FreePromotionShouldFail() {
        int quantity = 1;
        ShoppingCartItem item = new ShoppingCartItem(4L, PRODUCT_ID_GOOGLE_HOMES, quantity,
                "Test Buy 2 Google Homes no promotion");
        Order order = shoppingCartController.addItem(item);
        assertNotNull(order);
        List<OrderProduct> list = shoppingCartController.listItems(order.getId());
        assertEquals(1, list.size());
        assertEquals(PRODUCT_ID_GOOGLE_HOMES, list.get(0).getProduct().getId());
        assertEquals(list.get(0).getProduct().getPrice(), list.get(0).getPrice());
        assertEquals(quantity, list.get(0).getQuantity());
    }

    @Test
    public void trigger1ProductAGet1ProductBFreePromotionShouldSuccess() {
        int quantity = 1;
        ShoppingCartItem item = new ShoppingCartItem(5L, PRODUCT_ID_MACBOOK_PRO, quantity,
                "Test Each sale of a MacBook Pro comes with a free Raspberry Pi B");
        Order order = shoppingCartController.addItem(item);
        assertNotNull(order);
        List<OrderProduct> list = shoppingCartController.listItems(order.getId());
        assertEquals(2, list.size());
        assertEquals(PRODUCT_ID_MACBOOK_PRO, list.get(0).getProduct().getId());
        assertEquals(list.get(0).getProduct().getPrice(), list.get(0).getPrice());
        assertEquals(quantity, list.get(0).getQuantity());
        assertEquals(PRODUCT_ID_RASPBERRY_PI, list.get(1).getProduct().getId());
        assertEquals(0, list.get(1).getPrice().intValue());
        assertEquals(quantity, list.get(1).getQuantity());
    }

    @Test
    public void trigger2ProductAGet2ProductBFreePromotionShouldSuccess() {
        int quantity = 2;
        ShoppingCartItem item = new ShoppingCartItem(16L, PRODUCT_ID_MACBOOK_PRO, quantity,
                "Test Each sale of a MacBook Pro comes with a free Raspberry Pi B");
        Order order = shoppingCartController.addItem(item);
        assertNotNull(order);
        List<OrderProduct> list = shoppingCartController.listItems(order.getId());
        assertEquals(2, list.size());
        assertEquals(PRODUCT_ID_MACBOOK_PRO, list.get(0).getProduct().getId());
        assertEquals(list.get(0).getProduct().getPrice(), list.get(0).getPrice());
        assertEquals(quantity, list.get(0).getQuantity());
        assertEquals(PRODUCT_ID_RASPBERRY_PI, list.get(1).getProduct().getId());
        assertEquals(0, list.get(1).getPrice().intValue());
        assertEquals(quantity, list.get(1).getQuantity());
    }

    @Test
    public void trigger3ProductGet10PercentOffOnAllPromotionShouldSuccess() {
        int quantity = 3;
        ShoppingCartItem item = new ShoppingCartItem(7L, PRODUCT_ID_ALEXA_SPEAKER, quantity,
                "Test Buying more than 3 Alexa Speakers will have a 10% discount on all Alexa speakers");
        Order order = shoppingCartController.addItem(item);
        assertNotNull(order);
        List<OrderProduct> list = shoppingCartController.listItems(order.getId());
        assertEquals(2, list.size());
        assertEquals(PRODUCT_ID_ALEXA_SPEAKER, list.get(0).getProduct().getId());
        assertEquals(list.get(0).getProduct().getPrice(), list.get(0).getPrice());
        assertEquals(quantity, list.get(0).getQuantity());
        assertEquals(PRODUCT_ID_ALEXA_SPEAKER, list.get(1).getProduct().getId());
        assertEquals(BigDecimal.valueOf(-10.95), list.get(1).getPrice());
        assertEquals(quantity, list.get(1).getQuantity());
    }

    @Test
    public void trigger4ProductGet10PercentOffOnAllPromotionShouldSuccess() {
        int quantity = 4;
        ShoppingCartItem item = new ShoppingCartItem(18L, PRODUCT_ID_ALEXA_SPEAKER, quantity,
                "Test Buying more than 3 Alexa Speakers will have a 10% discount on all Alexa speakers");
        Order order = shoppingCartController.addItem(item);
        assertNotNull(order);
        List<OrderProduct> list = shoppingCartController.listItems(order.getId());
       // assertEquals(2, list.size());
        assertEquals(PRODUCT_ID_ALEXA_SPEAKER, list.get(0).getProduct().getId());
        assertEquals(list.get(0).getProduct().getPrice(), list.get(0).getPrice());
        assertEquals(quantity, list.get(0).getQuantity());
        assertEquals(PRODUCT_ID_ALEXA_SPEAKER, list.get(1).getProduct().getId());
        assertEquals(BigDecimal.valueOf(-10.95), list.get(1).getPrice());
        assertEquals(quantity, list.get(1).getQuantity());
    }

    @Test
    public void trigger2ProductGet10PercentOffOnAllPromotionShouldFail() {
        int quantity = 2;
        ShoppingCartItem item = new ShoppingCartItem(19L, PRODUCT_ID_ALEXA_SPEAKER, quantity,
                "Test Buying more than 3 Alexa Speakers will have a 10% discount on all Alexa speakers");
        Order order = shoppingCartController.addItem(item);
        assertNotNull(order);
        List<OrderProduct> list = shoppingCartController.listItems(order.getId());
        assertEquals(1, list.size());
        assertEquals(PRODUCT_ID_ALEXA_SPEAKER, list.get(0).getProduct().getId());
        assertEquals(list.get(0).getProduct().getPrice(), list.get(0).getPrice());
        assertEquals(quantity, list.get(0).getQuantity());
    }
}
