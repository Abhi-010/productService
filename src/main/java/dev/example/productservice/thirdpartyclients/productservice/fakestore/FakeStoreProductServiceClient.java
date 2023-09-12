package dev.example.productservice.thirdpartyclients.productservice.fakestore;

import dev.example.productservice.dtos.GenericProductDto;
import dev.example.productservice.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/*
 ** Wrapper over FakeStore API
 */
@Service
public class FakeStoreProductServiceClient {
    private RestTemplateBuilder restTemplateBuilder;

//    fakestore.api.url = https://fakestoreapi.com
//    fakestore.api.paths.product = /products
//    private String specificProductRequestUrl = "https://fakestoreapi.com/products/{id}";
//    private String productRequestBaseUrl = "https://fakestoreapi.com/products";


    @Value("${fakestore.api.url}")
    private String fakeStoreApiUrl ;

    @Value("${fakestore.api.paths.product}")
    private String fakeStorePathProduct ;

    private String specificProductRequestUrl ;
    private String productRequestBaseUrl ;


    private GenericProductDto genericProductDto = new GenericProductDto();

    public FakeStoreProductServiceClient(RestTemplateBuilder restTemplateBuilder,
                                         @Value("${fakestore.api.paths.product}")  String fakeStorePathProduct,
                                         @Value("${fakestore.api.url}")  String fakeStoreApiUrl){
        this.restTemplateBuilder = restTemplateBuilder;
        this.fakeStoreApiUrl = fakeStoreApiUrl;
        this.fakeStorePathProduct = fakeStorePathProduct;

        this.specificProductRequestUrl = fakeStoreApiUrl + fakeStorePathProduct + "/{id}" ;
        this.productRequestBaseUrl =  fakeStoreApiUrl + fakeStorePathProduct ;
    }

    public FakeStoreProductDto deleteProductById(Long id) throws NotFoundException {

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
        return fakeStoreProductDto;
    }


    public FakeStoreProductDto  getProductById(Long id) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> responseEntity =
                restTemplate.getForEntity(specificProductRequestUrl, FakeStoreProductDto.class,id);
        FakeStoreProductDto fakeStoreProductDto = responseEntity.getBody();

        return fakeStoreProductDto;
    }


    public List<FakeStoreProductDto> getAllProduct() {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto[]> responseEntity =
                restTemplate.getForEntity(productRequestBaseUrl,FakeStoreProductDto[].class);

        FakeStoreProductDto[] arrayOfFakeStoreProducts = responseEntity.getBody();

        List<FakeStoreProductDto> listOfFakeStoreProducts = new ArrayList<>();
        for(FakeStoreProductDto obj : arrayOfFakeStoreProducts){
            listOfFakeStoreProducts.add(obj);
        }
        return listOfFakeStoreProducts;

    }
}
