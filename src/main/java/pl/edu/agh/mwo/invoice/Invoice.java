package pl.edu.agh.mwo.invoice;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;

import pl.edu.agh.mwo.invoice.product.Product;

public class Invoice {

    private Collection<Product> products = new ArrayList<>();



    public void addProduct(Product product) {
        products.add(product);

    }

    public void addProduct(Product product, Integer quantity) {
        // TODO: implement
    }

    public BigDecimal getNetPrice() {
        BigDecimal result =  BigDecimal.ZERO;
        for (Product product : this.products) {
            result = result.add(product.getPrice());

        }
        return  result;
    }

    public BigDecimal getTax() {
        return BigDecimal.ZERO;
    }

    public BigDecimal getGross() {
        return BigDecimal.ZERO;
    }
}
