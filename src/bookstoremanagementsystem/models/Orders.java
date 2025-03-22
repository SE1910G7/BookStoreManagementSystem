/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstoremanagementsystem.models;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

/**
 *
 * @author mummykiara
 */
public class Orders implements Serializable {

    private String orderId;
    private String customerId;
    private LocalDate orderDate;
    private String booksOrder;
    private String statusId;

    public Orders(String orderId, String customerId, LocalDate orderDate, String booksOrder, String statusId) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.orderDate = orderDate;
        this.booksOrder = booksOrder;
        this.statusId = statusId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getBooksOrder() {
        return booksOrder;
    }

    public void setBooksOrder(String booksOrder) {
        this.booksOrder = booksOrder;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public String getStatusId() {
        return statusId;
    }

    public void setStatusId(String statusId) {
        this.statusId = statusId;
    }
}
