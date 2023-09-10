package dev.example.productservice.service;

import dev.example.productservice.dtos.FakeStoreProductDto;
import dev.example.productservice.dtos.GenericProductDto;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Primary;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class FakeStoreProductService implements ProductService {

    private RestTemplateBuilder restTemplateBuilder;
    private String getProductRequestUrl = "https://fakestoreapi.com/products/{id}";
    private String getAllProductUrl = "https://fakestoreapi.com/products";

    public FakeStoreProductService(RestTemplateBuilder restTemplateBuilder){
        this.restTemplateBuilder = restTemplateBuilder;
    }



    @Override
    public GenericProductDto  getProductById(Long id) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> responseEntity =
            restTemplate.getForEntity(getProductRequestUrl, FakeStoreProductDto.class,id);
        FakeStoreProductDto fakeStoreProductDto = responseEntity.getBody();

        GenericProductDto genericProductDto = new GenericProductDto();

        genericProductDto.setImage(fakeStoreProductDto.getImage());
        genericProductDto.setCategory(fakeStoreProductDto.getCategory());
        genericProductDto.setTitle(fakeStoreProductDto.getTitle());
        genericProductDto.setPrice(fakeStoreProductDto.getPrice());
        genericProductDto.setDescription(fakeStoreProductDto.getDescription());

        return genericProductDto;

    }

    @Override
    public List<GenericProductDto> getAllProduct() {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto[]> responseEntity =
                restTemplate.getForEntity(getAllProductUrl,FakeStoreProductDto[].class);

        FakeStoreProductDto[] listOfFakeStoreProducts = responseEntity.getBody();

        ArrayList<GenericProductDto> listOfGenericProducts =
                convertFakeStoreProductToGenericProduct(listOfFakeStoreProducts);

        return listOfGenericProducts;
    }

    public ArrayList<GenericProductDto> convertFakeStoreProductToGenericProduct(FakeStoreProductDto[] list){

        ArrayList<GenericProductDto> listOfGenericProducts = new ArrayList<>();

        for(FakeStoreProductDto l : list){
            GenericProductDto genericProductDto = new GenericProductDto();
            genericProductDto.setId(l.getId());
            genericProductDto.setDescription(l.getDescription());
            genericProductDto.setCategory(l.getCategory());
            genericProductDto.setPrice(l.getPrice());
            genericProductDto.setTitle(l.getTitle());
            genericProductDto.setImage(l.getImage());

            listOfGenericProducts.add(genericProductDto);

        }
        return listOfGenericProducts;
    }
}
