/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstoremanagementsystem.models;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mummykiara
 */
public class Carts {

    String cartId;
    String accountId;
    List<CartItems> items;

    public Carts(String cartId, String accountId) {
        this.cartId = cartId;
        this.accountId = accountId;
        this.items = new ArrayList<>();
    }
}
