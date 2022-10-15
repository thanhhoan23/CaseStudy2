package services.sort;

import models.Product;

import java.util.Comparator;

public class SortNameByASC implements Comparator<Product> {
    @Override
    public int compare(Product p1, Product p2) {
        return p1.getNameProduct().compareTo(p2.getNameProduct());
    }
}
