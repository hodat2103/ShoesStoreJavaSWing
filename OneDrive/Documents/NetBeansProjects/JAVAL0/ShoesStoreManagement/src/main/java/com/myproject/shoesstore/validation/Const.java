package com.myproject.shoesstore.validation;

import java.time.LocalDate;

/**
 *
 * @author Tadaboh;
 */
public class Const {
    
    public static final LocalDate localDate = LocalDate.of(1900, 1, 1);
    public static final String CHECK_DATE = "Ngày phải được tạo sau 1/1/1900!";
    public static final String CHECK_EMPTY= "Vui lòng nhập đầy đủ giá trị !";
    
    
    public static final String ONLY_NUMBER_REGEX = "[0-9]+";
    public static final String EMAIL_REGEX = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,}$";
    public static final String INVALID = " Bị lỗi ! ";
    public static final String INVALID_LENGTH = " Bị lỗi vượt quá độ dài! ";
    
    public static final String CODE_EMPLOYEE_EXISTS = "Mã nhân viên đã tổn tại!";
    public static final String CODE_CUSTOMER_EXISTS = "Mã khách hàng đã tổn tại!";
    public static final String CODE_PRODUCT_EXISTS = "Mã sản phẩm đã tổn tại!";
    public static final String CODE_SALARY_EXISTS = "Mã lương đã tổn tại!";
    public static final String CODE_INVOICE_EXISTS = "Mã hóa đơn đã tổn tại!";
    public static final String CODE_DISCOUNT_EXISTS = "Mã khuyến mãi đã tổn tại!";
    public static final String CODE_SUPPLIER_EXISTS = "Mã nhà cung cấp đã tổn tại!";
    
    
    public static final String NAME = "Lỗi tên!";
    public static final String PHONE = "Điện thoại phải 10 số !!";
    public static final String EMAIL = "Vui lòng nhập đúng email !!";
    public static final String AGE = "Vui lòng nhập tuổi (1-100) !!";
    public static final String ADDRESS = "Vui lòng điền chính xác địa chỉ !!";
    
    
    
    
    
}
