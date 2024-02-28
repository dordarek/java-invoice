package pl.edu.agh.mwo.invoice;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;

import pl.edu.agh.mwo.invoice.product.Product;

public class Invoice {
    private Collection<Product> products;

    public void addProduct(Product product) {
        // TODO: implement
        if (product != null) {
            if (products == null) {
                products = new ArrayList<>();
            }
            products.add(product);
        }
        else {
            throw new IllegalArgumentException();
        }
    }

    public void addProduct(Product product, Integer quantity) {
        // TODO: implement
        if (product != null && quantity != null && quantity > 0) {
            for (int i = 0; i < quantity; i++) {
                addProduct(product);
            }
        }
        else {
            throw new IllegalArgumentException();
        }
    }

    public BigDecimal getSubtotal() {
        BigDecimal subtotal = BigDecimal.ZERO;
        if (products != null) {
            for (Product product : products) {
                subtotal = subtotal.add(product.getPrice());
            }
        }
        return subtotal;
    }

    public BigDecimal getTax() {
        BigDecimal tax = BigDecimal.ZERO;
        if (products != null) {
            for (Product product : products) {
                tax = tax.add(product.getTaxPercent().multiply(product.getPrice()));
            }
        }

        return tax;
    }

//    public BigDecimal getTotal() {
//        BigDecimal subtotal = getSubtotal();
//        BigDecimal tax = getTax();
//        return subtotal.add(tax);
//    }
    public BigDecimal getTotal() {
        BigDecimal tax = BigDecimal.ZERO;
        if (products != null) {
            for (Product product : products) {
                tax = tax.add(product.getPriceWithTax());
            }
        }
        if (tax.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Tax amount cannot be negative");
        }
        return tax;
    }
}
