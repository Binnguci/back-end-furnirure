package com.binnguci.furniture.constant;

public class StringConstant {
    // Product
    public static final String PRODUCT_NOT_FOUND = "Product not found";
    public static final String PRODUCT_NAME_REQUIRED = "Product name is required";
    public static final String PRODUCT_PRICE_REQUIRED = "Product price is required";
    public static final String PRODUCT_STOCK_REQUIRED = "Product stock is required";
    public static final String PRODUCT_CATEGORY_REQUIRED = "Product category is required";
    public static final String PRODUCT_DESCRIPTION_REQUIRED = "Product description is required";
    public static final String PRODUCT_IMAGE_REQUIRED = "Product image is required";
    public static final String PRODUCT_PRICE_INVALID = "Product price is invalid";
    public static final String PRODUCT_STOCK_INVALID = "Product stock is invalid";
    public static final String PRODUCT_CATEGORY_INVALID = "Product category is invalid";
    public static final String PRODUCT_DESCRIPTION_INVALID = "Product description is invalid";
    public static final String PRODUCT_IMAGE_INVALID = "Product image is invalid";
    public static final String PRODUCT_CREATED = "Product created";
    public static final String PRODUCT_UPDATED = "Product updated";
    public static final String PRODUCT_DELETED = "Product deleted";
    public static final String PRODUCT_NAME_EXIST = "Product name already exists";

    // User
    public static final String USER_NOT_FOUND = "User not found";
    public static final String USER_NAME_REQUIRED = "User name is required";
    public static final String USER_EMAIL_REQUIRED = "User email is required";
    public static final String USER_PASSWORD_REQUIRED = "User password is required";
    public static final String USER_ROLE_REQUIRED = "User role is required";
    public static final String USER_EMAIL_INVALID = "User email is invalid";
    public static final String USER_PASSWORD_INVALID = "User password is invalid";
    public static final String USER_ROLE_INVALID = "User role is invalid";
    public static final String USER_CREATED = "User created";
    public static final String USER_UPDATED = "User updated";
    public static final String USER_DELETED = "User deleted";
    public static final String USER_EMAIL_EXISTS = "User email already exists";

    // Cart
    public static final String CART_NOT_FOUND = "Cart not found";
    public static final String CART_ITEM_NOT_FOUND = "Cart item not found";
    public static final String CART_ITEM_ADDED = "Item added to cart";
    public static final String CART_ITEM_REMOVED = "Item removed from cart";
    public static final String CART_UPDATED = "Cart updated";

    // Order
    public static final String ORDER_NOT_FOUND = "Order not found";
    public static final String ORDER_CREATED = "Order created";
    public static final String ORDER_UPDATED = "Order updated";
    public static final String ORDER_DELETED = "Order deleted";

    // Order Item
    public static final String ORDER_ITEM_NOT_FOUND = "Order item not found";
    public static final String ORDER_ITEM_ADDED = "Order item added";
    public static final String ORDER_ITEM_REMOVED = "Order item removed";

    // Supplier
    public static final String SUPPLIER_NOT_FOUND = "Supplier not found";
    public static final String SUPPLIER_NAME_REQUIRED = "Supplier name is required";
    public static final String SUPPLIER_CREATED = "Supplier created";
    public static final String SUPPLIER_UPDATED = "Supplier updated";
    public static final String SUPPLIER_DELETED = "Supplier deleted";

    // Category
    public static final String CATEGORY_NOT_FOUND = "Category not found";
    public static final String CATEGORY_NAME_REQUIRED = "Category name is required";
    public static final String CATEGORY_CREATED = "Category created";
    public static final String CATEGORY_UPDATED = "Category updated";
    public static final String CATEGORY_DELETED = "Category deleted";

    // Wishlist
    public static final String WISHLIST_NOT_FOUND = "Wishlist not found";
    public static final String WISHLIST_ITEM_ADDED = "Item added to wishlist";
    public static final String WISHLIST_ITEM_REMOVED = "Item removed from wishlist";

    // Review
    public static final String REVIEW_NOT_FOUND = "Review not found";
    public static final String REVIEW_CREATED = "Review created";
    public static final String REVIEW_UPDATED = "Review updated";
    public static final String REVIEW_DELETED = "Review deleted";

    // Log
    public static final String LOG_NOT_FOUND = "Log not found";
    public static final String LOG_CREATED = "Log created";
    public static final String LOG_UPDATED = "Log updated";
    public static final String LOG_DELETED = "Log deleted";

    // Role
    public static final String ROLE_NOT_FOUND = "Role not found";
    public static final String ROLE_CREATED = "Role created";
    public static final String ROLE_UPDATED = "Role updated";
    public static final String ROLE_DELETED = "Role deleted";

    //Mail
    public static final String MAIL_SENT = "Mail sent";
    public static final String MAIL_FAILED = "Mail failed";
    public static final String OTP_NUll = "OTP cannot be empty or blank";
    public static final String TEMPLATE_OTP_EMAIL = """
            <div style="font-family: Arial, sans-serif; color: #333;">
                <p style="font-size: 16px;">Chào bạn,</p>
                <p style="font-size: 16px;">
                    Bạn đã yêu cầu lấy lại mật khẩu cho tài khoản của mình. Dưới đây là mã OTP để xác nhận:
                </p>                        
                <p style="font-size: 18px; font-weight: bold; color: #D19C97; text-align: center;">
                    Mã OTP: %s
                </p>
                <p style="font-size: 16px;">
                    Vui lòng nhập mã này vào ứng dụng để tiếp tục quá trình lấy lại mật khẩu.
                </p>
                <p style="font-size: 16px;">
                    Nếu bạn không yêu cầu lấy lại mật khẩu, vui lòng bỏ qua email này.
                </p>
                <p style="font-size: 16px;">Trân trọng,</p>
                <p style="font-size: 16px; font-style: italic;">Đội ngũ hỗ trợ</p>
            </div>
            """;
}

