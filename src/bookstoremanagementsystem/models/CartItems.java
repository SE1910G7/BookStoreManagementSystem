/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstoremanagementsystem.models;

/**
 *
 * @author mummykiara
 */
public class CartItems {

    String cartItemId;
    String cartId;
    String bookId;
    int quantity;
    double price;

    public CartItems(String cartItemId, String cartId, String bookId, int quantity, double price) {
        this.cartItemId = cartItemId;
        this.cartId = cartId;
        this.bookId = bookId;
        this.quantity = quantity;
        this.price = price;
    }
}
