package com.golzstore.springstore.mappers;


import com.golzstore.springstore.dtos.ProductDto;
import com.golzstore.springstore.entities.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    @Mapping(source = "category.id", target = "categoryId")
    ProductDto toDto(Product product);
}
