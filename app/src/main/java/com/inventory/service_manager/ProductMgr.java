package com.inventory.service_manager;

import com.inventory.domain.Product;
import com.inventory.repositories.ProductRepository;
import com.inventory.service.ProductService;
import com.inventory.utils.handleValidateFields;

import java.util.List;

// calls the repo
public class ProductMgr implements ProductService {

    private final ProductRepository productRepo;

    public ProductMgr() {
        this.productRepo = new ProductRepository();
    }

    public String createProduct(Product product)
    {
        if(new handleValidateFields().validateFields(product) == null)
        {
            int update_res = productRepo.createProduct(product);
            if (update_res == 200) {
                System.out.println("Updated Product successfully for: " + product.getProductId());
            }
            else {
                System.out.println("Product updating failed for: " + product.getProductId() + ". Error: " + update_res);
            }
            return "200";
        }
        else {
            return "401a";
        }
    }

    public List<Product> fetchAllProducts() {
        return productRepo.fetchAllProducts();
    }

    public String updateProductDetails(Product product)
    {
        if(new handleValidateFields().validateFields(product) == null) {
            int update_res = productRepo.updateProductDetails(product);
            if (update_res == 200) {
                System.out.println("Updated Product successfully for: " + product.getProductId());
            }
            else {
                System.out.println("Product updating failed for: " + product.getProductId() + ". Error: " + update_res);
            }
            return "200";
        }
        else {
            return "401a";
        }
    }

    public String deleteProduct(Product product)
    {
        if(new handleValidateFields().validateFields(product) == null) {
            int update_res = productRepo.deleteProduct(product);
            if (update_res == 200) {
                System.out.println("Deleted Product successfully for: " + product.getProductId());
            }
            else {
                System.out.println("Product deletion failed for: " + product.getProductId() + ". Error: " + update_res);
            }
            return "200";
        }
        else {
            return "401a";
        }
    }

}
