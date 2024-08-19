package com.binnguci.furniture.controller.admin;

import com.binnguci.furniture.dto.ProductDTO;
import com.binnguci.furniture.dto.response.APIResponse;
import com.binnguci.furniture.enums.ErrorCode;
import com.binnguci.furniture.exception.AppException;
import com.binnguci.furniture.service.product.IProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController(value = "productControllerOfAdmin")
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/admin/products")
public class ProductController {
    private final IProductService productService;

    @GetMapping(    )
    public ResponseEntity<APIResponse<List<ProductDTO>>> findAll() {
        log.info("Request to get all products");
        try {
            List<ProductDTO> products = productService.findAll();
            APIResponse<List<ProductDTO>> response = APIResponse.<List<ProductDTO>>builder()
                    .code(ErrorCode.SUCCESS.getCode())
                    .message(ErrorCode.SUCCESS.getMessage())
                    .result(products)
                    .build();
            return ResponseEntity.ok(response);
        } catch (Exception ex) {
            throw new AppException(ErrorCode.NOT_FOUND);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<APIResponse<ProductDTO>> create(@RequestBody ProductDTO productDTO) {
        log.info("Request to create product");
        try {
            ProductDTO product = productService.updateAndSave(productDTO);
            APIResponse<ProductDTO> response = APIResponse.<ProductDTO>builder()
                    .code(ErrorCode.CREATE_SUCCESS.getCode())
                    .message(ErrorCode.CREATE_SUCCESS.getMessage())
                    .result(product)
                    .build();
            return ResponseEntity.ok(response);
        } catch (Exception ex) {
            throw new AppException(ErrorCode.CREATE_FAILED);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<APIResponse<ProductDTO>> update(@RequestBody ProductDTO productDTO) {
        log.info("Request to update product with id: {}", productDTO.getId());
        try {
            ProductDTO product = productService.updateAndSave(productDTO);
            APIResponse<ProductDTO> response = APIResponse.<ProductDTO>builder()
                    .code(ErrorCode.UPDATE_SUCCESS.getCode())
                    .message(ErrorCode.UPDATE_SUCCESS.getMessage())
                    .result(product)
                    .build();
            return ResponseEntity.ok(response);
        } catch (Exception ex) {
            throw new AppException(ErrorCode.UPDATE_FAILED);
        }
    }
}
