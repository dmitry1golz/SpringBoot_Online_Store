package com.golzstore.springstore.repositories;

import com.golzstore.springstore.dtos.ProductSummaryDTO;
import com.golzstore.springstore.entities.Category;
import com.golzstore.springstore.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long>, ProductCriteriaRepository, JpaSpecificationExecutor<Product> {

    //String

    List<Product> findByName(String name);

    List<Product> findByNameLike(String name);

    List<Product> findByNameNotLike(String name);

    List<Product> findByNameContains(String name);

    List<Product> findByNameStartingWith(String name);

    List<Product> findByNameEndingWith(String name);

    // Numbers
    List<Product> findByNameContainsIgnoreCase(String name);

    List<Product> findByPrice(BigDecimal price);

    List<Product> findByPriceGreaterThan(BigDecimal price);

    List<Product> findByPriceGreaterThanEqual(BigDecimal price);

    List<Product> findByPriceLessThanEqual(BigDecimal price);

    List<Product> findByPriceBetween(BigDecimal min, BigDecimal max);

    //Null
    List<Product> findByDescriptionNull();

    List<Product> findByDescriptionNotNull();

    // Multiply Conditions
    List<Product> findByDescriptionNullAndNameNull();

    //Sort (OrderBy)
    List<Product> findByNameOrderByPriceAsc(String name);

    // Limits (Top/First)
    List<Product> findTop5ByNameOrderByPrice(String name);

    List<Product> findTop5ByNameLikeOrderByPriceAsc(String name);

    @Procedure("findProductsByPrice")
    List<Product> findProducts(BigDecimal min, BigDecimal max);

    @Query("select count(*) from Product p where p.price between :min and :max")
    long countProducts(@Param("min") BigDecimal min, @Param("max") BigDecimal max);


    @Modifying
    @Query("update Product p set p.price = :newPrice where p.category.id = :categoryId")
    void updatePriceByCategory(BigDecimal newPrice, Byte categoryId);

    @Query("select new com.golzstore.springstore.dtos.ProductSummaryDTO(p.id, p.name) from Product p where p.category = :category")
    List<ProductSummaryDTO> findByCategory(@Param("category") Category category);
}