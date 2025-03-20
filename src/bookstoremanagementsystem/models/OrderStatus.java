/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstoremanagementsystem.models;

import java.io.Serializable;

/**
 *
 * @author mummykiara
 */
public class OrderStatus implements Serializable {
    private String statusId;
    private String statusName;
    
    public OrderStatus(String statusId, String statusName) {
        this.statusId = statusId;
        this.statusName = statusName;
    }
    
    public String getStatusId() { return statusId; }
    public void setStatusId(String statusId) { this.statusId = statusId; }
    
    public String getStatusName() { return statusName; }
    public void setStatusName(String statusName) { this.statusName = statusName; }
    
    @Override
    public String toString() {
        return "OrderStatus{" + "statusId='" + statusId + '\'' + ", statusName='" + statusName + '\'' + '}';
    }
}