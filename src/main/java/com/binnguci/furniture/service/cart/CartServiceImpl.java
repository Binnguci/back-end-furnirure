package com.binnguci.furniture.service.cart;

import com.binnguci.furniture.dto.CartDTO;
import com.binnguci.furniture.entity.CartEntity;
import com.binnguci.furniture.mapper.CartMapper;
import com.binnguci.furniture.repository.ICartRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class CartServiceImpl implements ICartService{
    private final ICartRepository cartRepository;
    private final CartMapper cartMapper;
    @Override
    public CartDTO findByUserId(Integer userId) {
        CartEntity cart = cartRepository.findByUserId(userId).get();
        return cartMapper.toDTO(cart);
    }
}
