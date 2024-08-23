package com.binnguci.furniture.service.cart;

import com.binnguci.furniture.dto.CartItemDTO;

public interface ICartItemService {
    CartItemDTO saveCartItem(CartItemDTO cartItemDTO);
    CartItemDTO updateCartItem(Integer quantity, String id);
    void deleteCartItem(String id);
    CartItemDTO getCartItemByCartId(String id);
}
