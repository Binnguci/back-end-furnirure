package com.binnguci.furniture.service.cart;

import com.binnguci.furniture.dto.CartItemDTO;
import com.binnguci.furniture.entity.CartItemEntity;
import com.binnguci.furniture.enums.ErrorCode;
import com.binnguci.furniture.exception.AppException;
import com.binnguci.furniture.mapper.CartItemMapper;
import com.binnguci.furniture.repository.ICartItemRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CartItemServiceImpl implements ICartItemService{
    CartItemMapper cartItemMapper;
    ICartItemRepository cartItemRepository;

    @Override
    public CartItemDTO saveCartItem(CartItemDTO cartItemDTO) {
        log.info("Saving cart item: {}", cartItemDTO);
        CartItemEntity cartItemEntity = cartItemMapper.toEntity(cartItemDTO);
        cartItemEntity.setId(UUID.randomUUID().toString());
        cartItemEntity = cartItemRepository.save(cartItemEntity);
        log.info("Cart item saved: {}", cartItemEntity);
        return cartItemMapper.toDTO(cartItemEntity);
    }

    @Override
    public CartItemDTO updateCartItem(Integer quantity, String id) {
        log.info("Updating cart item: {}", id);
        CartItemEntity cartItemEntity = cartItemRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.CART_ITEM_NOT_FOUND));
        cartItemEntity.setQuantity(quantity);
        cartItemEntity = cartItemRepository.save(cartItemEntity);
        log.info("Cart item updated: {}", cartItemEntity);
        return cartItemMapper.toDTO(cartItemEntity);
    }

    @Override
    public void deleteCartItem(String id) {
        log.info("Deleting cart item: {}", id);
        cartItemRepository.deleteById(id);
        log.info("Cart item deleted: {}", id);
    }

    @Override
    public CartItemDTO getCartItemByCartId(String id) {
        log.info("Fetching cart item by cart id: {}", id);
        CartItemEntity cartItemEntity = cartItemRepository.findByCartId(id)
                .orElseThrow(() -> new AppException(ErrorCode.CART_NOT_FOUND));
        return cartItemMapper.toDTO(cartItemEntity);
    }
}
