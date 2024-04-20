package com.myproject.shoesstore.model;

/**
 *
 * @author Tadaboh;
 */
public class Product implements ExcelExportable{
    private String productCode;
    private String productName;
    private byte[] image;
    private double price;
    private int quantity;
    private String supplierCode;
    private String descript;

    public Product() {
    }

    public Product(String productCode, String productName, byte[] image, double price, int quantity, String supplierCode, String descript) {
        this.productCode = productCode;
        this.productName = productName;
        this.image = image;
        this.price = price;
        this.quantity = quantity;
        this.supplierCode = supplierCode;
        this.descript = descript;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getSupplierCode() {
        return supplierCode;
    }

    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
    }

    public String getDescript() {
        return descript;
    }

    public void setDescript(String descript) {
        this.descript = descript;
    }

    @Override
    public String toString() {
        return "Product name " + productName;
    }
    
    @Override
    public Object[] toExcelRow() {
        return new Object[]{productCode, productName,image, price,quantity,supplierCode,descript};
    }

    @Override
    public String[] getColumnHeaders() {
        return new String[]{"Mã sản phẩm","Tên sản phẩm","Ảnh","Giá","Số lượng","Mã cung cấp","Mô tả"};
        
        
    }
    
    
    
}
