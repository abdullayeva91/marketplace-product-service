package com.marketplace.marketplaceproductservice.service;
import com.marketplace.grpc.ProductGrpcServiceGrpc;
import com.marketplace.grpc.ProductRequest;
import com.marketplace.grpc.ProductResponse;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

import com.marketplace.grpc.*;

@GrpcService
public class ProductGrpcServiceImpl extends ProductGrpcServiceGrpc.ProductGrpcServiceImplBase {

    @Override
    public void validateProduct(ProductRequest request, StreamObserver<ProductResponse> responseObserver) {
        String productId = request.getProductId();
        int requestedQty = request.getQuantity();


        boolean exists = true;
        double price = 120.50;


        ProductResponse response = ProductResponse.newBuilder()
                .setIsAvailable(exists)
                .setPrice(price)
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}