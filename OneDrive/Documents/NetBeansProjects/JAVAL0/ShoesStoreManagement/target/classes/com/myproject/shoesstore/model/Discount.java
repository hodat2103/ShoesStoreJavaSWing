package com.myproject.shoesstore.model;

import java.util.Date;

/**
 *
 * @author Tadaboh;
 */
public class Discount implements ExcelExportable{
    private String discountCode;
    private String title;
    private Date dateStart;
    private Date dateEnd;
    private float discountAmount;
    private String descript;

    public Discount() {
    }

    public Discount(String discountCode, String title, Date dateStart, Date dateEnd, float discountAmount, String descript) {
        this.discountCode = discountCode;
        this.title = title;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.discountAmount = discountAmount;
        this.descript = descript;
    }

    public String getDiscountCode() {
        return discountCode;
    }

    public void setDiscountCode(String discountCode) {
        this.discountCode = discountCode;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDateStart() {
        return dateStart;
    }

    public void setDateStart(Date dateStart) {
        this.dateStart = dateStart;
    }

    public Date getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }

    public float getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(float discountAmount) {
        this.discountAmount = discountAmount;
    }

    public String getDescript() {
        return descript;
    }

    public void setDescript(String descript) {
        this.descript = descript;
    }

    @Override
    public String toString() {
        return discountCode;
    }

    @Override
    public Object[] toExcelRow() {
        return new Object[]{discountCode, title,dateStart, dateEnd,discountAmount,descript};
    }

    @Override
    public String[] getColumnHeaders() {
        return new String[]{"Mã khuyến mãi","Tiêu đề","Ngày bắt đầu","Ngày kết thúc","Mức giảm giá (%)","Mô tả"};
        
        
    }
    
    
    
}
