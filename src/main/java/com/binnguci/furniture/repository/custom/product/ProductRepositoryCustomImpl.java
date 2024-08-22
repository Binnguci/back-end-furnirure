package com.binnguci.furniture.repository.custom.product;

import com.binnguci.furniture.domain.request.ProductSearchRequest;
import com.binnguci.furniture.entity.CategoryEntity;
import com.binnguci.furniture.entity.ProductEntity;
import com.binnguci.furniture.entity.SupplierEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductRepositoryCustomImpl implements IProductRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    private List<Predicate> buildPredicates(ProductSearchRequest productSearchRequest, CriteriaBuilder cb,
                                            Root<ProductEntity> product,
                                            Join<ProductEntity, CategoryEntity> categoryJoin,
                                            Join<ProductEntity, SupplierEntity> supplierJoin) {

        List<Predicate> predicates = new ArrayList<>();

        if (productSearchRequest.getName() != null) {
            predicates.add(cb.like(product.get("name"), "%" + productSearchRequest.getName() + "%"));
        }

        if (productSearchRequest.getCategory() != null) {
            predicates.add(cb.equal(categoryJoin.get("name"), productSearchRequest.getCategory()));
        }

        if (productSearchRequest.getSupplier() != null) {
            predicates.add(cb.equal(supplierJoin.get("name"), productSearchRequest.getSupplier()));
        }

        if (productSearchRequest.getMinPrice() != null) {
            predicates.add(cb.greaterThanOrEqualTo(product.get("price"), productSearchRequest.getMinPrice()));
        }

        if (productSearchRequest.getMaxPrice() != null) {
            predicates.add(cb.lessThanOrEqualTo(product.get("price"), productSearchRequest.getMaxPrice()));
        }

        return predicates;
    }

    @Override
    public List<ProductEntity> findAllMultiField(ProductSearchRequest productSearchRequest) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<ProductEntity> criteriaQuery = cb.createQuery(ProductEntity.class);
        Root<ProductEntity> root = criteriaQuery.from(ProductEntity.class);

        Join<ProductEntity, CategoryEntity> categoryJoin = root.join("category");
        Join<ProductEntity, SupplierEntity> supplierJoin = root.join("supplier");

        List<Predicate> predicates = buildPredicates(productSearchRequest, cb, root, categoryJoin, supplierJoin);

        criteriaQuery.where(predicates.toArray(new Predicate[0]));

        TypedQuery<ProductEntity> typedQuery = entityManager.createQuery(criteriaQuery);
        List<ProductEntity> result = typedQuery.getResultList();
        return result;
    }

}

