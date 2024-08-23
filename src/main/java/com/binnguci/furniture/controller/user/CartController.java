package com.binnguci.furniture.controller.user;

import com.binnguci.furniture.domain.response.APIResponse;
import com.binnguci.furniture.dto.CartDTO;
import com.binnguci.furniture.dto.CartItemDTO;
import com.binnguci.furniture.enums.ErrorCode;
import com.binnguci.furniture.service.cart.ICartItemService;
import com.binnguci.furniture.service.cart.ICartService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/carts")
public class CartController {
    private final ICartService cartService;
    private final ICartItemService cartItemService;

    @GetMapping("/user/{id}")
    public ResponseEntity<APIResponse<CartDTO>> getCartByUserId(@PathVariable Integer id) {
        log.info("Fetching cart by user id: {}", id);
        CartDTO cart = cartService.findByUserId(id);
        return buildResponse(cart, (cart != null) ? ErrorCode.FOUND : ErrorCode.CART_NOT_FOUND);
    }

    @PostMapping("/add-product")
    public ResponseEntity<APIResponse<CartItemDTO>> getCartByUserId(@RequestBody CartItemDTO cartItemDTO) {
        log.info("Adding product to cart: {}", cartItemDTO);
        CartItemDTO cartItem = cartItemService.saveCartItem(cartItemDTO);
        return buildResponse(cartItem, (cartItem != null) ? ErrorCode.CREATE_SUCCESS : ErrorCode.CREATE_FAILED);
    }

    @PutMapping("/update-product/{id}")
    public ResponseEntity<APIResponse<CartItemDTO>> updateCartItem(@PathVariable String id, @RequestParam Integer quantity) {
        log.info("Updating cart item: {}", id);
        CartItemDTO cartItem = cartItemService.updateCartItem(quantity, id);
        return buildResponse(cartItem, (cartItem != null) ? ErrorCode.UPDATE_SUCCESS : ErrorCode.UPDATE_FAILED);
    }

    @DeleteMapping("/delete-product/{id}")
    public ResponseEntity<APIResponse<Void>> deleteCartItem(@PathVariable String id) {
        log.info("Deleting cart item: {}", id);
        cartItemService.deleteCartItem(id);
        return buildResponse(null, ErrorCode.DELETE_SUCCESS);
    }

    private <T> ResponseEntity<APIResponse<T>> buildResponse(T result, ErrorCode errorCode) {
        APIResponse<T> response = APIResponse.<T>builder()
                .code(errorCode.getCode())
                .message(errorCode.getMessage())
                .result(result)
                .build();
        return ResponseEntity.ok(response);
    }


}
