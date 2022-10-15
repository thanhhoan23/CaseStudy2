package services.sort;

import models.Product;

import java.util.Comparator;

public class SortCategoryByASC implements Comparator<Product> {

    @Override
    public int compare(Product p1, Product p2) {
        return p1.getCategory().compareTo(p2.getCategory());
    }
}
