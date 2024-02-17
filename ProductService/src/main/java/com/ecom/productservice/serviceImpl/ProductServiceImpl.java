package com.ecom.productservice.serviceImpl;

import com.ecom.productservice.exceptions.ProductNotFoundException;
import com.ecom.productservice.models.Category;
import com.ecom.productservice.models.Product;
import com.ecom.productservice.repo.CategoryRepo;
import com.ecom.productservice.repo.ProductRepo;
import com.ecom.productservice.services.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service("SelfProductServiceImpl")
public class ProductServiceImpl implements ProductService {
    private ProductRepo productRepo;
    private CategoryRepo categoryRepo;
    Logger logger= LoggerFactory.getLogger(ProductServiceImpl.class);
    @Autowired
    public ProductServiceImpl(ProductRepo productRepo,CategoryRepo categoryRepo) {
        this.productRepo = productRepo;
        this.categoryRepo=categoryRepo;
    }

    @Override
    public ResponseEntity<Product> getProductById(Long id) throws ProductNotFoundException {
        Optional<Product> productOptional=productRepo.findById(id);
        if(!productOptional.isPresent()){
         throw  new ProductNotFoundException("Product not found with id : "+id);
        }
        return new ResponseEntity<>(productOptional.get(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Product>> getAllProducts() {

        return new ResponseEntity<>(productRepo.findAll(),HttpStatus.OK);
    }

    @Override
    public Product deleteProductById(long id) throws ProductNotFoundException {
        Optional<Product> productOptional=productRepo.findById(id);
        if(!productOptional.isPresent()){
            throw  new ProductNotFoundException("Product not found with id : "+id);
        }
        productRepo.delete(productOptional.get());
    return  productOptional.get();
    }

    @Override
    public Product addProduct(Product product) {
      Optional<Category> categoryOptional=categoryRepo.findByName(product.getCategory().getName());
      if (!categoryOptional.isPresent()){
          Category savedCategory= categoryRepo.save(product.getCategory());
          product.setCategory(savedCategory);
      }
      else{
          product.setCategory(categoryOptional.get());
      }

        return productRepo.save(product);
    }

    @Override
    public Product updateProductById(long id,Product product) throws ProductNotFoundException {
        Optional<Product> productOptional=
                productRepo.findById(id);
        if(!productOptional.isPresent()){
            logger.error("Product not found with id:"+id);
            throw  new ProductNotFoundException("Product not found with id:"+id);
        }
        Product actualProduct=productOptional.get();
       /*Checking if category is provided by the client for updation or not*/
        if(product.getCategory()!=null&&!product.getCategory().getName().equalsIgnoreCase(actualProduct.getCategory().getName())){
        Optional<Category> categoryOptional= categoryRepo.findByName(product.getCategory().getName());
        /*Check if provided category already exists in the database or not. if yes then update the category of the product otherwise
        * first create new category and add it into the database and then save it with product*/
        if(!categoryOptional.isPresent()){
            Category savedCategory=categoryRepo.save(product.getCategory());
            logger.info("new Category added:"+savedCategory.getName());
            actualProduct.setCategory(savedCategory);
        }
        else{
            actualProduct.setCategory(product.getCategory());
        }
        }
        actualProduct.setTitle(product.getTitle());
        actualProduct.setDescription(product.getDescription());
        actualProduct.setPrice(product.getPrice());
        return  productRepo.save(actualProduct);
    }

    @Override
    public List<String> getAllCategory() {
        List<Product> products=productRepo.findAll();
        List<String> categories=new ArrayList<>();
        products.stream().forEach(product -> categories.add(product.getCategory().getName()));
        return categories;
    }

    @Override
    public ResponseEntity<List<Product>> getInCategory(String category) throws ProductNotFoundException {
        Optional<Category> categoryOptional=categoryRepo.findByName(category);
        if(!categoryOptional.isPresent()){
            throw  new ProductNotFoundException("No such category exists:"+category);
        }
        List<Product> productList=productRepo.findByCategory(categoryOptional.get());
        return new ResponseEntity<>(productList,HttpStatus.OK);
    }
}
