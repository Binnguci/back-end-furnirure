package com.binnguci.furniture.service.cart;

import com.binnguci.furniture.dto.CartDTO;
import com.binnguci.furniture.entity.CartEntity;
import com.binnguci.furniture.enums.ErrorCode;
import com.binnguci.furniture.exception.AppException;
import com.binnguci.furniture.mapper.CartMapper;
import com.binnguci.furniture.repository.ICartRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CartServiceImpl implements ICartService{
    ICartRepository cartRepository;
    CartMapper cartMapper;

    @Override
    public CartDTO findByUserId(Integer userId) {
        CartEntity cart = cartRepository.findByUserId(userId)
                .orElseThrow(() -> new AppException(ErrorCode.CART_NOT_FOUND));
        return cartMapper.toDTO(cart);
    }
}
