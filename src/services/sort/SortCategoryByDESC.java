package services.sort;

import models.Product;

import java.util.Comparator;

public class SortCategoryByDESC implements Comparator<Product> {
    @Override
    public int compare(Product p1, Product p2) {
        return p2.getCategory().compareTo(p1.getCategory());
    }
}
