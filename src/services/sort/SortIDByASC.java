package services.sort;

import models.Product;

import java.util.Comparator;

public class SortIDByASC implements Comparator<Product> {

    @Override
    public int compare(Product p1, Product p2) {
       return (int)(p1.getIdProduct() - p2.getIdProduct());
    }
}
