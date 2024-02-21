package com.ecom.productservice;

import com.ecom.productservice.projections.ProductWithIdAndTitle;
import com.ecom.productservice.repo.ProductRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.core.AutoConfigureCache;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.util.List;

@SpringBootTest
class ProductServiceApplicationTests {
	ProductRepo productRepo;

	@Autowired
	public ProductServiceApplicationTests(ProductRepo productRepo) {
		this.productRepo = productRepo;
	}

	@Test
	void contextLoads() {
	}

	@Test
	public void ProjectionTest(){
		List<ProductWithIdAndTitle> product=
				productRepo.getProductIdAndTitle("Jeans");

		Assert.isTrue("Jeans".equalsIgnoreCase(product.get(0).getTitle()),"Jeans is present in products");
	}

}
