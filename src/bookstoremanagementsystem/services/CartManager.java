package bookstoremanagementsystem.services;

import bookstoremanagementsystem.interfaces.ICarts;
import bookstoremanagementsystem.models.CartItems;
import bookstoremanagementsystem.models.Carts;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CartManager implements ICarts {
    private final List<Carts> cartList;
    private static final String CART_FILE = "cart.txt";
    private static final String ITEM_FILE = "cart_items.txt";

    public CartManager() {
        this.cartList = new ArrayList<>();
        loadCartsFromFile();
        loadItemsFromFile();
    }

    @Override
    public void viewCarts() {
        if (cartList.isEmpty()) {
            System.out.println("No carts available.");
            return;
        }
        cartList.forEach((cart) -> {
            System.out.println("Cart ID: " + cart.getCartId() + ", Account ID: " + cart.getAccountId());
        });
    }

    @Override
    public void addCart(Carts cart) {
        cartList.add(cart);
        saveCartsToFile();
        System.out.println("Cart added successfully.");
    }

    @Override
    public void updateCart(String cartId, Carts newCart) {
        for (Carts cart : cartList) {
            if (cart.getCartId().equals(cartId)) {
                cart.setAccountId(newCart.getAccountId());
                saveCartsToFile();
                System.out.println("Cart updated successfully.");
                return;
            }
        }
        System.out.println("Cart not found.");
    }

    @Override
    public boolean isCartExists(String cartId) {
        return cartList.stream().anyMatch(cart -> cart.getCartId().equals(cartId));
    }

    @Override
public void addItemToCart(String cartId, CartItems cartItem) {
    for (Carts cart : cartList) {
        if (cart.getCartId().equals(cartId)) {
            // Thêm item vào danh sách
            cart.addItem(cartItem);
            saveItemsToFile(); // Lưu thay đổi vào file
            System.out.println("Item added to cart successfully.");
            return;
        }
    }
    System.out.println("Cart not found.");
}

    @Override
    public void viewItemsInCart(String cartId) {
        for (Carts cart : cartList) {
            if (cart.getCartId().equals(cartId)) {
                if (cart.getItems().isEmpty()) {
                    System.out.println("No items in this cart.");
                } else {
                    cart.getItems().forEach((item) -> {
                        System.out.println("Item ID: " + item.getCartItemId() + ", Book ID: " + item.getBookId() +
                                ", Quantity: " + item.getQuantity() + ", Price: " + item.getPrice());
                    });
                }
                return;
            }
        }
        System.out.println("Cart not found.");
    }

    @Override
public void updateCartItem(String cartId, String itemId, int quantity, double price) {
    for (Carts cart : cartList) {
        if (cart.getCartId().equals(cartId)) {
            for (CartItems item : cart.getItems()) {
                if (item.getCartItemId().equals(itemId)) {
                    // Cập nhật thông tin của item
                    item.setQuantity(quantity);
                    item.setPrice(price);
                    saveItemsToFile(); // Lưu thay đổi vào file
                    System.out.println("Item updated successfully.");
                    return;
                }
            }
            System.out.println("Item not found in the cart.");
            return;
        }
    }
    System.out.println("Cart not found.");
}


@Override
public void removeItemFromCart(String cartId, String itemId) {
    for (Carts cart : cartList) {
        if (cart.getCartId().equals(cartId)) {
            boolean removed = cart.getItems().removeIf(item -> item.getCartItemId().equals(itemId));
            if (removed) {
                saveItemsToFile(); // Lưu thay đổi vào file
                System.out.println("Item removed from cart successfully.");
            } else {
                System.out.println("Item not found in the cart.");
            }
            return;
        }
    }
    System.out.println("Cart not found.");
}

    @Override
    public void deleteCart(String cartId) {
        cartList.removeIf(cart -> cart.getCartId().equals(cartId));
        saveCartsToFile();
        saveItemsToFile();
        System.out.println("Cart deleted successfully.");
    }

    public void viewAllCarts() {
        if (cartList.isEmpty()) {
            System.out.println("No carts available.");
        } else {
            viewCarts();
        }
    }

    private void saveCartsToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(CART_FILE))) {
            for (Carts cart : cartList) {
                writer.write(cart.getCartId() + "," + cart.getAccountId());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving carts: " + e.getMessage());
        }
    }

    private void saveItemsToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ITEM_FILE))) {
            for (Carts cart : cartList) {
                for (CartItems item : cart.getItems()) {
                    writer.write(cart.getCartId() + "," + item.getCartItemId() + "," + item.getBookId() +
                            "," + item.getQuantity() + "," + item.getPrice());
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            System.out.println("Error saving cart items: " + e.getMessage());
        }
    }

    private void loadCartsFromFile() {
        File file = new File(CART_FILE);
        if (!file.exists()) return;

        try (BufferedReader reader = new BufferedReader(new FileReader(CART_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    Carts cart = new Carts(parts[0], parts[1]);
                    cartList.add(cart);
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading carts: " + e.getMessage());
        }
    }

    private void loadItemsFromFile() {
        File file = new File(ITEM_FILE);
        if (!file.exists()) return;

        try (BufferedReader reader = new BufferedReader(new FileReader(ITEM_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 5) {
                    String cartId = parts[0];
                    String itemId = parts[1];
                    String bookId = parts[2];
                    int quantity = Integer.parseInt(parts[3]);
                    double price = Double.parseDouble(parts[4]);

                    Carts cart = cartList.stream()
                            .filter(c -> c.getCartId().equals(cartId))
                            .findFirst()
                            .orElse(null);

                    if (cart != null) {
                        cart.addItem(new CartItems(itemId, bookId, quantity, price));
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading cart items: " + e.getMessage());
        }
    }
}
