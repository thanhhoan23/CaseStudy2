package services.sort;

import models.Product;

import java.util.Comparator;

public class SortNameByDESC implements Comparator<Product> {

    @Override
    public int compare(Product p1, Product p2) {
        return p2.getNameProduct().compareTo(p1.getNameProduct());
    }
}
