/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstoremanagementsystem.models;

import java.time.LocalDate;

/**
 *
 * @author mummykiara
 */
public class Orders {

    String orderId;
    String accountId;
    LocalDate orderDate;
    OrderStatus status;
    String cartId;
    double totalPrice;

    public Orders(String orderId, String accountId, LocalDate orderDate, OrderStatus status, String cartId,
            double totalPrice) {
        this.orderId = orderId;
        this.accountId = accountId;
        this.orderDate = orderDate;
        this.status = status;
        this.cartId = cartId;
        this.totalPrice = totalPrice;
    }
}
