/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstoremanagementsystem.interfaces;
import bookstoremanagementsystem.models.CartItems;
import java.io.IOException;
/**
 *
 * @author DELL
 */
public interface ICartItems {
    void addCartItem(CartItems item);
    void displayCartItems();
    void updateCartItem(String cartItemId, int newQuantity, double newPrice);
    void deleteCartItem(String cartItemId);
    void saveCartItemsToFile() throws IOException;
    void loadCartItemsFromFile() throws IOException;
    void searchCartItems(String keyword);
}

    

