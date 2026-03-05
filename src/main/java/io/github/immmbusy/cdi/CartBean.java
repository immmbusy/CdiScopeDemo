package io.github.immmbusy.cdi;

import jakarta.enterprise.context.SessionScoped; // Back to Session
import java.io.Serializable; // Back to Serializable

@SessionScoped
public class CartBean implements Serializable {
    private int orderList = 0;

    public void addItem() { orderList++; }
    public void removeItem() { orderList--; }

    public int getOrderList() { return orderList; }
    public void setOrderList(int orderList) { this.orderList = orderList; }
}