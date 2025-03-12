/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstoremanagementsystem.services;

/**
 *
 * @author DELL
 */
import bookstoremanagementsystem.interfaces.ICartItems;
import bookstoremanagementsystem.models.CartItems;
import java.io.*;
import java.util.*;

public class CartItemsManager implements ICartItems {
    private final List<CartItems> cartItemsList = new ArrayList<>();
    private static final String FILE_NAME = "cart_items.txt";

    @Override
    public void addCartItem(CartItems item) {
        cartItemsList.add(item);
        System.out.println("Cart item added successfully!");
    }

    @Override
    public void displayCartItems() {
        if (cartItemsList.isEmpty()) {
            System.out.println("No items in the cart.");
            return;
        }
        System.out.printf("%-15s %-10s %-10s %-10s %-10s\n", "Cart Item ID", "Cart ID", "Book ID", "Quantity", "Price");
        cartItemsList.forEach((item) -> {
            System.out.printf("%-15s %-10s %-10s %-10d %-10.2f\n",
                    item.getCartItemId(), item.getCartId(), item.getBookId(), item.getQuantity(), item.getPrice());
        });
    }

    @Override
    public void updateCartItem(String cartItemId, int newQuantity, double newPrice) {
        for (CartItems item : cartItemsList) {
            if (item.getCartItemId().equals(cartItemId)) {
                item.setQuantity(newQuantity);
                item.setPrice(newPrice);
                System.out.println("Cart item updated successfully!");
                return;
            }
        }
        System.out.println("Cart item not found.");
    }

    @Override
    public void deleteCartItem(String cartItemId) {
        if (cartItemsList.removeIf(item -> item.getCartItemId().equals(cartItemId))) {
            System.out.println("Cart item deleted successfully!");
        } else {
            System.out.println("Cart item not found.");
        }
    }

    public void displayCartItems(int pageSize, int currentPage) {
        int start = (currentPage - 1) * pageSize;
        int end = Math.min(start + pageSize, cartItemsList.size());

        if (start >= cartItemsList.size() || start < 0) {
            System.out.println("Invalid page number.");
            return;
        }

        System.out.printf("%-15s %-10s %-10s %-10s %-10s\n", "Cart Item ID", "Cart ID", "Book ID", "Quantity", "Price");
        for (int i = start; i < end; i++) {
            CartItems item = cartItemsList.get(i);
            System.out.printf("%-15s %-10s %-10s %-10d %-10.2f\n",
                    item.getCartItemId(), item.getCartId(), item.getBookId(), item.getQuantity(), item.getPrice());
        }
    }

    @Override
    public void searchCartItems(String keyword) {
        boolean found = false;
        System.out.println("Search results:");
        System.out.printf("%-15s %-10s %-10s %-10s %-10s\n", "Cart Item ID", "Cart ID", "Book ID", "Quantity", "Price");
        for (CartItems item : cartItemsList) {
            if (item.getCartItemId().contains(keyword) || item.getCartId().contains(keyword) || item.getBookId().contains(keyword)) {
                System.out.printf("%-15s %-10s %-10s %-10d %-10.2f\n",
                        item.getCartItemId(), item.getCartId(), item.getBookId(), item.getQuantity(), item.getPrice());
                found = true;
            }
        }
        if (!found) {
            System.out.println("No matching items found.");
        }
    }

    @Override
    public void saveCartItemsToFile() {
        try (FileWriter writer = new FileWriter(FILE_NAME)) {
            for (CartItems item : cartItemsList) {
                writer.write(String.format("%s,%s,%s,%d,%.2f\n",
                        item.getCartItemId(), item.getCartId(), item.getBookId(), item.getQuantity(), item.getPrice()));
            }
            System.out.println("Cart items saved to file successfully!");
        } catch (IOException e) {
            System.out.println("Error saving cart items to file: " + e.getMessage());
        }
    }

    @Override
    public void loadCartItemsFromFile() {
        cartItemsList.clear(); // Xóa danh sách trước khi tải mới
        try (Scanner scanner = new Scanner(new File(FILE_NAME))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(",");
                if (parts.length == 5) {
                    try {
                        CartItems item = new CartItems(parts[0], parts[1], parts[2],
                                Integer.parseInt(parts[3]), Double.parseDouble(parts[4]));
                        cartItemsList.add(item);
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid number format in file: " + line);
                    }
                } else {
                    System.out.println("Invalid data format in file: " + line);
                }
            }
            System.out.println("Cart items loaded from file successfully!");
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        }
    }

    public boolean isCartItemIdExists(String updateID) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}