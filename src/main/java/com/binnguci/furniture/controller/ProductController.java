package com.binnguci.furniture.controller;

import com.binnguci.furniture.constant.StringConstant;
import com.binnguci.furniture.dto.ProductDTO;
import com.binnguci.furniture.dto.response.APIResponse;
import com.binnguci.furniture.enums.ErrorCode;
import com.binnguci.furniture.exception.AppException;
import com.binnguci.furniture.service.product.IProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/products")
public class ProductController {
    private final IProductService productService;

    @GetMapping
    public ResponseEntity<APIResponse<List<ProductDTO>>> findAll() {
        log.info("Request to get all products");
        try {
            List<ProductDTO> products = productService.findAll();
            return buildResponse(products, StringConstant.PRODUCT_NAME_NOT_FOUND);
        } catch (Exception ex) {
            throw new AppException(ErrorCode.INVALID_REQUEST);
        }
    }

    @GetMapping("/search")
    public ResponseEntity<APIResponse<List<ProductDTO>>> findByName(@RequestParam String name) {
        log.info("Request to search product by name: {}", name);
        try {
            List<ProductDTO> products = productService.findByName(name);
            return buildResponse(products, StringConstant.PRODUCT_NAME_NOT_FOUND);
        } catch (Exception ex) {
            throw new AppException(ErrorCode.INVALID_REQUEST);
        }
    }

    private ResponseEntity<APIResponse<List<ProductDTO>>> buildResponse(List<ProductDTO> products, String notFoundMessage) {
        if (products.isEmpty()) {
            APIResponse<List<ProductDTO>> response = APIResponse.<List<ProductDTO>>builder()
                    .code(ErrorCode.NOT_FOUND.getCode())
                    .message(notFoundMessage)
                    .build();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        APIResponse<List<ProductDTO>> response = APIResponse.<List<ProductDTO>>builder()
                .code(ErrorCode.SUCCESS.getCode())
                .message(ErrorCode.SUCCESS.getMessage())
                .result(products)
                .build();
        return ResponseEntity.ok(response);
    }

}
