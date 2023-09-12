package dev.example.productservice.services;

import dev.example.productservice.thirdpartyclients.productservice.fakestore.FakeStoreProductDto;
import dev.example.productservice.dtos.GenericProductDto;
import dev.example.productservice.exceptions.NotFoundException;
import dev.example.productservice.thirdpartyclients.productservice.fakestore.FakeStoreProductServiceClient;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class FakeStoreProductService implements ProductService {

    private GenericProductDto genericProductDto;
    FakeStoreProductServiceClient fakeStoreProductServiceClient;

    public FakeStoreProductService(FakeStoreProductServiceClient fakeStoreProductServiceClient){
        this.fakeStoreProductServiceClient = fakeStoreProductServiceClient;
    }

    private GenericProductDto convertFakeStoreProductToGenericProduct(FakeStoreProductDto fakeStoreProductDto){
        genericProductDto = new GenericProductDto();
        genericProductDto.setImage(fakeStoreProductDto.getImage());
        genericProductDto.setCategory(fakeStoreProductDto.getCategory());
        genericProductDto.setTitle(fakeStoreProductDto.getTitle());
        genericProductDto.setPrice(fakeStoreProductDto.getPrice());
        genericProductDto.setDescription(fakeStoreProductDto.getDescription());
        genericProductDto.setId(fakeStoreProductDto.getId());

        return genericProductDto;
    }

    @Override
    public GenericProductDto deleteProductById(Long id) throws NotFoundException {
        FakeStoreProductDto fakeStoreProductDto = fakeStoreProductServiceClient.deleteProductById(id);
        return convertFakeStoreProductToGenericProduct(fakeStoreProductDto);
    }

    @Override
    public GenericProductDto  getProductById(Long id) {
        FakeStoreProductDto fakeStoreProductDto =  fakeStoreProductServiceClient.getProductById(id);
        return convertFakeStoreProductToGenericProduct(fakeStoreProductDto);
    }

    @Override
    public List<GenericProductDto> getAllProduct() {
        List<FakeStoreProductDto> fakeStoreProductDtos  =  fakeStoreProductServiceClient.getAllProduct();
        List<GenericProductDto> genericProductDtos = new ArrayList<>();
        for(FakeStoreProductDto obj : fakeStoreProductDtos){
            genericProductDtos.add(convertFakeStoreProductToGenericProduct(obj));
        }
        return genericProductDtos;
    }
}
