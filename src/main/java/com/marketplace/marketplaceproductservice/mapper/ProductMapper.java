package com.marketplace.marketplaceproductservice.mapper;
import com.marketplace.marketplaceproductservice.dto.ProductCreateRequest;
import com.marketplace.marketplaceproductservice.dto.ProductResponse;
import com.marketplace.marketplaceproductservice.dto.ProductUpdateRequest;
import com.marketplace.marketplaceproductservice.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    Product toEntity(ProductCreateRequest createRequest);
    void updateEntityFromDto(
            ProductUpdateRequest request,
            @MappingTarget Product product
    );

    ProductResponse toResponse(Product product);

}
