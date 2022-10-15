package services.sort;

import models.Product;

import java.util.Comparator;

public class SortPriceByDESC implements Comparator<Product> {
    @Override
    public int compare(Product p1, Product p2) {
        return (int)(p2.getPrice() - p1.getPrice());
    }
}
