package com.myproject.shoesstore.bean;

/**
 *
 * @author Tadaboh;
 */
public class BeanProduct {
    private String dateSell;
    private int quantityProduct;

    public BeanProduct() {
    }

    public BeanProduct(String dateSell, int quantityProduct) {
        this.dateSell = dateSell;
        this.quantityProduct = quantityProduct;
    }

    public String getDateSell() {
        return dateSell;
    }

    public void setDateSell(String dateSell) {
        this.dateSell = dateSell;
    }

    public int getQuantityProduct() {
        return quantityProduct;
    }

    public void setQuantityProduct(int quantityProduct) {
        this.quantityProduct = quantityProduct;
    }
    
    
    
}
