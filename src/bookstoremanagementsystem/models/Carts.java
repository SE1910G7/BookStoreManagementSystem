package bookstoremanagementsystem.models;

import java.util.ArrayList;
import java.util.List;

public class Carts {

    String cartId;
    String accountId;
    List<CartItems> items;

    public Carts(String cartId, String accountId) {
        this.cartId = cartId;
        this.accountId = accountId;
        this.items = new ArrayList<>();
    }

    public String getCartId() {
        return cartId;
    }

    public void setCartId(String cartId) {
        this.cartId = cartId;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public List<CartItems> getItems() {
        return items;
    }

    public void addItem(CartItems item) {
        items.add(item);
    }

    public void removeItem(String cartItemId) {
        boolean removeIf = items.removeIf(item -> item.cartItemId.equals(cartItemId));
    }

    public double calculateTotalCost() {
        return items.stream().mapToDouble(item -> item.getQuantity() * item.getPrice()).sum();
    }

    public void setItems(List<CartItems> items) {
        this.items = items;
    }

    

    

}
