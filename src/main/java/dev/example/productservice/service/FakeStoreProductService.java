package dev.example.productservice.service;

import dev.example.productservice.dtos.FakeStoreProductDto;
import dev.example.productservice.dtos.GenericProductDto;
import dev.example.productservice.exception.NotFoundException;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class FakeStoreProductService implements ProductService {

    private RestTemplateBuilder restTemplateBuilder;
    private String specificProductRequestUrl = "https://fakestoreapi.com/products/{id}";
    private String productRequestBaseUrl = "https://fakestoreapi.com/products";

    private GenericProductDto genericProductDto = new GenericProductDto();

    public FakeStoreProductService(RestTemplateBuilder restTemplateBuilder){
        this.restTemplateBuilder = restTemplateBuilder;
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
        RestTemplate restTemplate = restTemplateBuilder.build();
        RequestCallback requestCallback = restTemplate.acceptHeaderRequestCallback(FakeStoreProductDto.class);
        ResponseExtractor<ResponseEntity<FakeStoreProductDto>> responseExtractor =
                restTemplate.responseEntityExtractor(FakeStoreProductDto.class);

        ResponseEntity<FakeStoreProductDto> responseEntity =
                restTemplate.execute(specificProductRequestUrl, HttpMethod.DELETE, requestCallback, responseExtractor, id);

        FakeStoreProductDto fakeStoreProductDto =responseEntity.getBody();
        // We should handle this exception..

        if(fakeStoreProductDto == null){
            throw new NotFoundException("Product with id " + id + " doesn't exist");
        }

        return convertFakeStoreProductToGenericProduct(fakeStoreProductDto);

    }

    @Override
    public GenericProductDto  getProductById(Long id) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> responseEntity =
            restTemplate.getForEntity(specificProductRequestUrl, FakeStoreProductDto.class,id);
        FakeStoreProductDto fakeStoreProductDto = responseEntity.getBody();

        return convertFakeStoreProductToGenericProduct(fakeStoreProductDto);

    }

    @Override
    public List<GenericProductDto> getAllProduct() {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto[]> responseEntity =
                restTemplate.getForEntity(productRequestBaseUrl,FakeStoreProductDto[].class);

        FakeStoreProductDto[] listOfFakeStoreProducts = responseEntity.getBody();

        ArrayList<GenericProductDto> listOfGenericProducts =
                convertFakeStoreProductToGenericProduct(listOfFakeStoreProducts);

        return listOfGenericProducts;
    }

    public ArrayList<GenericProductDto> convertFakeStoreProductToGenericProduct(FakeStoreProductDto[] list){

        ArrayList<GenericProductDto> listOfGenericProducts = new ArrayList<>();

        for(FakeStoreProductDto l : list){
            listOfGenericProducts.add(convertFakeStoreProductToGenericProduct(l));
        }
        return listOfGenericProducts;
    }
}
