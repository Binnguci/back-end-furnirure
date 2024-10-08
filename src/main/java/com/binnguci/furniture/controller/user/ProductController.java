package com.binnguci.furniture.controller.user;

import com.binnguci.furniture.constant.StringConstant;
import com.binnguci.furniture.dto.ProductDTO;
import com.binnguci.furniture.domain.request.ProductSearchRequest;
import com.binnguci.furniture.domain.response.APIResponse;
import com.binnguci.furniture.enums.ErrorCode;
import com.binnguci.furniture.exception.AppException;
import com.binnguci.furniture.service.product.IProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/products")
public class ProductController {
    private final IProductService productService;

    @GetMapping
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


    @GetMapping("/search")
    public ResponseEntity<APIResponse<List<ProductDTO>>> findProduct(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String supplier,
            @RequestParam(required = false) Double minPrice,
            @RequestParam(required = false) Double maxPrice) {

        log.info("Request to search product: name={}, category={}, supplier={}, minPrice={}, maxPrice={}",
                name, category, supplier, minPrice, maxPrice);
        try {
            ProductSearchRequest productSearchRequest = new ProductSearchRequest();
            productSearchRequest.setName(name);
            productSearchRequest.setCategory(category);
            productSearchRequest.setSupplier(supplier);
            productSearchRequest.setMinPrice(minPrice);
            productSearchRequest.setMaxPrice(maxPrice);

            List<ProductDTO> products = productService.findByMultiFields(productSearchRequest);
            return buildResponse(products, StringConstant.PRODUCT_NOT_FOUND);
        } catch (Exception ex) {
            throw new AppException(ErrorCode.INVALID_REQUEST);
        }
    }

    @GetMapping("/description/{id}")
    public ResponseEntity<APIResponse<ProductDTO>> findById(@PathVariable Integer id) {
        log.info("Request to get product by id: {}", id);
        try {
            ProductDTO product = productService.findById(id);
            APIResponse<ProductDTO> response = APIResponse.<ProductDTO>builder()
                    .code(ErrorCode.SUCCESS.getCode())
                    .message(ErrorCode.SUCCESS.getMessage())
                    .result(product)
                    .build();
            return ResponseEntity.ok(response);
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
