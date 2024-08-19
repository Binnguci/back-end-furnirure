package com.binnguci.furniture.controller.admin;

import com.binnguci.furniture.dto.ProductDTO;
import com.binnguci.furniture.dto.response.APIResponse;
import com.binnguci.furniture.enums.ErrorCode;
import com.binnguci.furniture.exception.AppException;
import com.binnguci.furniture.service.product.IProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController(value = "productControllerOfAdmin")
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/admin/products")
public class ProductController {
    private final IProductService productService;

    @GetMapping()
    public ResponseEntity<APIResponse<Page<ProductDTO>>> findAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "9") int size) {
        try {
            log.info("Request to get all products");
            Pageable pageable = PageRequest.of(page, size);
            Page<ProductDTO> productPage = productService.findAll(pageable);
            APIResponse<Page<ProductDTO>> response = APIResponse.<Page<ProductDTO>>builder()
                    .code(ErrorCode.SUCCESS.getCode())
                    .message(ErrorCode.SUCCESS.getMessage())
                    .result(productPage)
                    .build();
            return ResponseEntity.ok(response);
        } catch (Exception ex) {
            throw new AppException(ErrorCode.INVALID_REQUEST);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<APIResponse<ProductDTO>> create(@Valid @RequestBody ProductDTO productDTO) {
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

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<APIResponse<ProductDTO>> deleteProduct(@PathVariable Integer id) {
        log.info("Request to delete product with id: {}", id);
        try {
            ProductDTO deletedProduct = productService.delete(id);
            APIResponse<ProductDTO> response = APIResponse.<ProductDTO>builder()
                    .code(ErrorCode.DELETE_SUCCESS.getCode())
                    .message(ErrorCode.DELETE_SUCCESS.getMessage())
                    .result(deletedProduct)
                    .build();
            return ResponseEntity.ok(response);
        } catch (AppException ex) {
            log.error("Failed to delete product with id: {}. Error: {}", id, ex.getMessage());
            APIResponse<ProductDTO> response = APIResponse.<ProductDTO>builder()
                    .code(ErrorCode.NOT_FOUND.getCode())
                    .message(ErrorCode.NOT_FOUND.getMessage())
                    .build();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

}
