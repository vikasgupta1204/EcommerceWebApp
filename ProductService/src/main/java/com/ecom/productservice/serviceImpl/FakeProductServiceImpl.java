package com.ecom.productservice.serviceImpl;

import com.ecom.productservice.dtos.FakeProductDto;
import com.ecom.productservice.exceptions.ProductNotFoundException;
import com.ecom.productservice.models.Category;
import com.ecom.productservice.models.Product;
import com.ecom.productservice.services.ProductService;
import com.ecom.productservice.thirdPartyClients.FakeStoreClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("fakeProductServiceImpl")
public class FakeProductServiceImpl implements ProductService {

    private FakeStoreClient fakeStoreClient;

    @Autowired
    public FakeProductServiceImpl(FakeStoreClient fakeStoreClient) {
        this.fakeStoreClient = fakeStoreClient;
    }

    @Override
    public ResponseEntity<Product> getProductById(Long id) throws ProductNotFoundException {

        Product product = fakeProductDtoToProduct(fakeStoreClient.getProductById(id));
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    private Product fakeProductDtoToProduct(FakeProductDto body) {
        Product product = new Product();
        product.setId(body.getId());
        product.setDescription(body.getDescription());
        Category category = new Category();
        category.setName(body.getCategory());
        product.setCategory(category);
        product.setPrice(body.getPrice());
        product.setTitle(body.getTitle());
        return product;

    }

    @Override
    public ResponseEntity<Page<Product>> getAllProducts(int page, int size, String sortBy) {
/*        List<FakeProductDto> fakeProductDtos = fakeStoreClient.getAllProducts();
        List<Product> prodLists = new ArrayList<>();
        fakeProductDtos.stream().forEach(fakeProductDto -> prodLists.add(fakeProductDtoToProduct(fakeProductDto)));
        return new ResponseEntity<>(prodLists, HttpStatus.OK); */
        return null;
    }


    @Override
    public Product deleteProductById(long id) {
        Product product = fakeProductDtoToProduct(fakeStoreClient.deleteProductById(id));
        return product;
    }

    @Override
    public Product addProduct(Product product) {

        FakeProductDto fakeProductDto = productToFakeProductDto(product);
        FakeProductDto savedFakeProduct = fakeStoreClient.addProduct(fakeProductDto);
        return fakeProductDtoToProduct(savedFakeProduct);
    }

    private FakeProductDto productToFakeProductDto(Product product) {
        FakeProductDto fakeProductDto = new FakeProductDto();
        fakeProductDto.setPrice(product.getPrice());
        fakeProductDto.setTitle(product.getTitle());
        fakeProductDto.setDescription(product.getDescription());
        fakeProductDto.setCategory(product.getCategory().getName());
        return fakeProductDto;
    }

    @Override
    public Product updateProductById(long id, Product product) throws ProductNotFoundException {
        FakeProductDto fakeProductDto = fakeStoreClient.updateProductById(id, productToFakeProductDto(product));
        return fakeProductDtoToProduct(fakeProductDto);
    }

    @Override
    public List<String> getAllCategory() {
        return null;
    }

    @Override
    public ResponseEntity<Page<Product>> getInCategory(String category, int page, int size, String sortBy) throws ProductNotFoundException {
        return null;
    }


}
