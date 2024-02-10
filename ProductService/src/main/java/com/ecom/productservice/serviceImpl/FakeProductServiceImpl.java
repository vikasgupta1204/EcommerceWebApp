package com.ecom.productservice.serviceImpl;

import com.ecom.productservice.dtos.FakeProductDto;
import com.ecom.productservice.exceptions.ProductNotFoundException;
import com.ecom.productservice.models.Category;
import com.ecom.productservice.models.Product;
import com.ecom.productservice.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.HttpMessageConverterExtractor;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.RestTemplate;

import java.lang.invoke.MethodHandleInfo;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service("fakeProductServiceImpl")
public class FakeProductServiceImpl implements ProductService {
    private String getProductUrl="https://fakestoreapi.com/products/{id}";
    private String getAllProductsUrl="https://fakestoreapi.com/products";
    private String addProductUrl="https://fakestoreapi.com/products";
    private String deleteProductUrl="https://fakestoreapi.com/products/{id}";
    RestTemplateBuilder restTemplateBuilder;

    @Autowired
    public FakeProductServiceImpl(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplateBuilder = restTemplateBuilder;
    }

    @Override
    public ResponseEntity<Product> getProductById(Long id) throws ProductNotFoundException {
        RestTemplate restTemplate=restTemplateBuilder.build();
        ResponseEntity<FakeProductDto> responseEntity= restTemplate.getForEntity(getProductUrl, FakeProductDto.class,id);
        if(responseEntity.getBody()==null){
            throw  new ProductNotFoundException("Product not found with id:"+id);
        }
        Product product= fakeProductDtoToProduct(responseEntity.getBody());
        return new ResponseEntity<>(product,HttpStatus.OK);
    }

    private Product fakeProductDtoToProduct(FakeProductDto body) {
        Product product=new Product();
        product.setId(body.getId());
        product.setDesc(body.getDescription());
        Category category=new Category();
        category.setName(body.getCategory());
        product.setCategory(category);
        product.setPrice(body.getPrice());
        product.setTitle(body.getTitle());
        return product;

    }

    @Override
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> prodLists=new ArrayList<>();
        RestTemplate restTemplate=restTemplateBuilder.build();
        ResponseEntity<FakeProductDto[]> fakeProductDtoResponseEntity=
                restTemplate.getForEntity(getAllProductsUrl,FakeProductDto[].class);
        Arrays.stream(fakeProductDtoResponseEntity.getBody()).forEach(
                fkr->prodLists.add(fakeProductDtoToProduct(fkr))
        );
        return new ResponseEntity<>(prodLists, HttpStatus.OK);
    }


    @Override
    public Product deleteProductById(long id) {
        RestTemplate restTemplate=restTemplateBuilder.build();
        RequestCallback requestCallback = restTemplate.acceptHeaderRequestCallback(FakeProductDto.class);
        HttpMessageConverterExtractor<FakeProductDto> responseExtractor = new HttpMessageConverterExtractor(FakeProductDto.class, restTemplate.getMessageConverters());
        FakeProductDto fakeProductDto=
                restTemplate.execute(deleteProductUrl, HttpMethod.DELETE, requestCallback, responseExtractor,id);
        Product product=fakeProductDtoToProduct(fakeProductDto);
        return product;
        //restTemplate.delete(deleteProductUrl,id);
    }

    @Override
    public Product addProduct(Product product) {
        RestTemplate restTemplate=restTemplateBuilder.build();
        FakeProductDto fakeProductDto=productToFakeProductDto(product);
        ResponseEntity<FakeProductDto> fakeProductDtoResponseEntity= restTemplate.postForEntity(addProductUrl,fakeProductDto,FakeProductDto.class);
        return fakeProductDtoToProduct(fakeProductDtoResponseEntity.getBody());
    }

    private FakeProductDto productToFakeProductDto(Product product) {
        FakeProductDto fakeProductDto=new FakeProductDto();
        fakeProductDto.setPrice(product.getPrice());
        fakeProductDto.setTitle(product.getTitle());
        fakeProductDto.setDescription(product.getDesc());
        fakeProductDto.setCategory(product.getCategory().getName());
        return fakeProductDto;
    }

    @Override
    public void updateProductById() {

    }
}
