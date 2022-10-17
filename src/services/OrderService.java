package services;

import models.Order;
import utils.CSVUtils;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class OrderService implements IOrderService{
    public final static String path ="data/orders.csv";

    private static OrderService instanceOrder;
    public OrderService() {};
    public static OrderService getInstanceOrder() {
        if (instanceOrder == null) {
            instanceOrder = new OrderService();
        }
        return instanceOrder;
    }
    @Override
    public void add(Order newOrder) {
//        newOrder.setTimeCreatOrders(Instant.now().toString());
        List<Order> orders = findAllOrder();
        orders.add(newOrder);
        CSVUtils.write(path, orders);
    }

    @Override
    public List<Order> findAllOrder() {
        List<Order> orders = new ArrayList<>();
        List<String> records = CSVUtils.read(path);
        for (String record : records) {
            orders.add(Order.parseOrder(record));
        }
        return orders;
    }
}
