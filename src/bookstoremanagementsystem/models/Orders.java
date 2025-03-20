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
    private Date orderDate;
    private String statusId;
    
    public Orders(String orderId, String customerId, Date orderDate, String statusId) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.orderDate = orderDate;
        this.statusId = statusId;
    }
    
    public String getOrderId() { return orderId; }
    public void setOrderId(String orderId) { this.orderId = orderId; }
    
    public String getCustomerId() { return customerId; }
    public void setCustomerId(String customerId) { this.customerId = customerId; }
    
    public Date getOrderDate() { return orderDate; }
    public void setOrderDate(Date orderDate) { this.orderDate = orderDate; }
    
    public String getStatusId() { return statusId; }
    public void setStatusId(String statusId) { this.statusId = statusId; }
    
    @Override
    public String toString() {
        return "Orders{" + "orderId='" + orderId + '\'' + ", customerId='" + customerId + '\'' + ", orderDate=" + orderDate + ", statusId='" + statusId + '\'' + '}';
    }
}
