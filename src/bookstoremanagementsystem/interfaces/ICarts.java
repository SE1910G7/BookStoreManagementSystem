package bookstoremanagementsystem.interfaces;

import bookstoremanagementsystem.models.CartItems;
import bookstoremanagementsystem.models.Carts;


public interface ICarts {
    void viewCarts();
    void addCart(Carts cart);
    void updateCart(String cartId, Carts newCart);
    void deleteCart(String cartId);
    boolean isCartExists(String cartId);
    void addItemToCart(String cartId, CartItems cartItem);
    void viewItemsInCart(String cartId);
    void updateCartItem(String cartId, String itemId, int quantity, double price);
    void removeItemFromCart(String cartId, String itemId);
}
