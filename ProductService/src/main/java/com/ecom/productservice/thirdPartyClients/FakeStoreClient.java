package com.ecom.productservice.thirdPartyClients;

import com.ecom.productservice.dtos.FakeProductDto;
import com.ecom.productservice.exceptions.ProductNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpMessageConverterExtractor;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Component
public class FakeStoreClient {

    RestTemplateBuilder restTemplateBuilder;
    private String genericUrl;

    @Autowired
    public FakeStoreClient(RestTemplateBuilder restTemplateBuilder, @Value("${fakestore.api.url}") String genericUrl) {
        this.restTemplateBuilder = restTemplateBuilder;
        this.genericUrl = genericUrl;
    }


    public FakeProductDto getProductById(Long id) throws ProductNotFoundException {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeProductDto> responseEntity = restTemplate.getForEntity(genericUrl + "/" + id, FakeProductDto.class);
        if (responseEntity.getBody() == null) {
            throw new ProductNotFoundException("Product not found with id:" + id);
        }
        return responseEntity.getBody();
    }


    public List<FakeProductDto> getAllProducts() {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeProductDto[]> fakeProductDtoResponseEntity =
                restTemplate.getForEntity(genericUrl, FakeProductDto[].class);
        List<FakeProductDto> fakeProductDtos = Arrays.asList(fakeProductDtoResponseEntity.getBody());
        return fakeProductDtos;
    }


    public FakeProductDto deleteProductById(long id) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        RequestCallback requestCallback = restTemplate.acceptHeaderRequestCallback(FakeProductDto.class);
        HttpMessageConverterExtractor<FakeProductDto> responseExtractor = new HttpMessageConverterExtractor(FakeProductDto.class, restTemplate.getMessageConverters());
        FakeProductDto fakeProductDto =
                restTemplate.execute(genericUrl, HttpMethod.DELETE, requestCallback, responseExtractor, id);
        return fakeProductDto;
        //restTemplate.delete(deleteProductUrl,id);
    }


    public FakeProductDto addProduct(FakeProductDto fakeProductDto) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeProductDto> fakeProductDtoResponseEntity = restTemplate.postForEntity(genericUrl, fakeProductDto, FakeProductDto.class);
        return fakeProductDtoResponseEntity.getBody();
    }


    public FakeProductDto updateProductById(long id, FakeProductDto fakeProductDto) throws ProductNotFoundException {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeProductDto> responseEntity = restTemplate.getForEntity(genericUrl + "/" + id, FakeProductDto.class);
        if (responseEntity.getBody() == null) {
            throw new ProductNotFoundException("Product not found with id:" + id);
        }
        FakeProductDto responseEntityBody = responseEntity.getBody();
        BeanUtils.copyProperties(fakeProductDto, responseEntityBody);
        RequestCallback requestCallback = restTemplate.httpEntityCallback(fakeProductDto, FakeProductDto.class);
        ResponseExtractor<ResponseEntity<FakeProductDto>> responseExtractor = restTemplate.responseEntityExtractor(FakeProductDto.class);
        ResponseEntity fakeProductDtoResponseEntity = restTemplate.execute(genericUrl + "/" + id, HttpMethod.PUT, requestCallback, responseExtractor, id);
        return (FakeProductDto) fakeProductDtoResponseEntity.getBody();


    }
}
