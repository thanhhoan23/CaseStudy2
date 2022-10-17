package services;

import models.Order;

import java.util.List;

public interface IOrderService {
    List<Order> findAllOrder();
    void add(Order newOrder);
}
