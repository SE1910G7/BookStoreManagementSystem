/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstoremanagementsystem.interfaces;

import bookstoremanagementsystem.models.Orders;
import java.util.List;

/**
 *
 * @author nhulnt
 */
public interface IOrders {

    void loadOrders();

    void saveToFile();

    void createOrder(Orders order);

    List<Orders> getOrderList();

    Orders getOrderById(String orderId);

    void updateOrderStatus(String orderId, String newStatus);

    void deleteOrder(String orderId);

    void showOrderList();
     void showOrderDetails(Orders order);
}
