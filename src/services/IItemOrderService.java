package services;

import models.ItemOrder;
import utils.CSVUtils;

import java.util.List;

public interface IItemOrderService {
    public List<ItemOrder> findAllItemOrder();
    void addItemOrder(ItemOrder newItemOrder);

    void update(Long idOrder, Double price, Double grandTotal);
}
