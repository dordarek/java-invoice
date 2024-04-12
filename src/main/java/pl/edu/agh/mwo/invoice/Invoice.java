package pl.edu.agh.mwo.invoice;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;

import pl.edu.agh.mwo.invoice.product.Product;

public class Invoice {
    private Map<Product, Integer> products = new LinkedHashMap<>();
    private static int invoiceCount;
    private final int number;

    public Invoice() {
        this.number = ++invoiceCount;
    }

    public void addProduct(Product product) {
        addProduct(product, 1);
    }

    public void addProduct(Product product, Integer quantity) {
        if (product == null || quantity <= 0) {
            throw new IllegalArgumentException("Product cannot be null.");
        }
        products.merge(product, quantity, Integer::sum);
    }

    public BigDecimal getNetTotal() {
        BigDecimal totalNet = BigDecimal.ZERO;
        for (Product product : products.keySet()) {
            BigDecimal quantity = new BigDecimal(products.get(product));
            totalNet = totalNet.add(product.getPrice().multiply(quantity));
        }
        return totalNet;
    }

    public BigDecimal getTaxTotal() {
        return getGrossTotal().subtract(getNetTotal());
    }

    public BigDecimal getGrossTotal() {
        BigDecimal totalGross = BigDecimal.ZERO;
        for (Product product : products.keySet()) {
            BigDecimal quantity = new BigDecimal(products.get(product));
            totalGross = totalGross.add(product.getPriceWithTax().multiply(quantity));
        }
        return totalGross;
    }

    public int getNumber() {
        return number;
    }

    public String getProductList() {
        String invoice = "";
        if (!products.isEmpty()) {
            invoice = invoice.concat("Invoice Number: " + getNumber()).concat("\n");
            for (Product product : products.keySet()) {
                invoice = invoice.concat(product.getName() + ", Quantity: "
                        + products.get(product) + ", Price: " + product.getPrice() + "\n");
            }
            invoice = invoice.concat("Total Items: " + products.keySet().size());
        }
        return invoice;
    }
}
