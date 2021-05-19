package com.example.cosmetic2.service;

import com.example.cosmetic2.Exceptions.ExceptionsResponse;
import com.example.cosmetic2.Exceptions.ProductsExceptions;
import com.example.cosmetic2.model.Product;
import com.example.cosmetic2.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    @Autowired
    PhotoService photoService;

    public List<Product> getAllProduct(){
        return productRepository.findAll();
    }

    public void deleteProduct(String id){
        try{
                productRepository.deleteById(id);
                productRepository.findById(id).map(product -> {
                photoService.deleteFile(product.getProductImage());
                productRepository.deleteById(id);
                return null;
            });

        }catch (EmptyResultDataAccessException e){
            throw new ProductsExceptions(ExceptionsResponse.ERROR_CODE.PRODUCT_DOES_NOT_EXIST,"product "+id+" doesn't exists");

        }}

    public Product findById(String id){
        try{
            return productRepository.findById(id).get();
        }catch(NoSuchElementException e){
            throw new ProductsExceptions(ExceptionsResponse.ERROR_CODE.PRODUCT_DOES_NOT_EXIST,"product "+id+" doesn't exists");
        }

    }

    public Product addProduct(Product newProduct){
            if (newProduct.getProductId() == "" || newProduct.getProductName() == "" || newProduct.getPrice() <= 0.0 ||
                    newProduct.getDescription() == "" || newProduct.getBrandId() == null || newProduct.getProductImage() == ""||
                    newProduct.getColors() == null){
                throw new ProductsExceptions(ExceptionsResponse.ERROR_CODE.PRODUCT_DOES_NOT_EXIST,"Product some data not null");
            }
            List<Product> productList = productRepository.findAll();
            Product[] products = new Product[productList.size()];
            productList.toArray(products);
            for (Product product : products) {
                if (product.getProductId().equals(newProduct.getProductId())) {
                    throw new ProductsExceptions(ExceptionsResponse.ERROR_CODE.PRODUCT_ALREADY_EXIST, "Product id : " + newProduct.getProductId() + " already exists");
                }if(product.getProductName().equals(newProduct.getProductName())){
                    throw new ProductsExceptions(ExceptionsResponse.ERROR_CODE.PRODUCT_ALREADY_EXIST,"Product name : " + newProduct.getProductName() + " already exists");
                }
            }
            newProduct.setProductName(newProduct.getProductName());
            newProduct.setProductImage(newProduct.getProductImage());
            newProduct.setPrice(newProduct.getPrice());
            newProduct.setLaunchDate(newProduct.getLaunchDate());
            newProduct.setDescription(newProduct.getDescription());
            newProduct.setBrandId(newProduct.getBrandId());
            return productRepository.save(newProduct);

    }

    public Product editProduct(Product newProduct, String id){
            return productRepository.findById(id).map(product -> {
            product.setProductName(newProduct.getProductName());
            product.setProductImage(newProduct.getProductImage());
            product.setPrice(newProduct.getPrice());
            product.setColors(newProduct.getColors());
            product.setBrandId(newProduct.getBrandId());
            product.setDescription(newProduct.getDescription());
            product.setLaunchDate(newProduct.getLaunchDate());
            return productRepository.save(product);
        }).get();
    }

    public Product editProductAndPicture(MultipartFile file, Product newProduct, String id){
        try{
            System.out.println(newProduct.getProductName());
            List<Product> productList = productRepository.findAll();
            Product[] products = new Product[productList.size()];
            productList.toArray(products);
            for (Product product : products){
                if (product.getProductName().equals(newProduct.getProductName())){
                    System.out.println("This name has already");
                }
            }
            productRepository.findById(id).map(product -> {
                product.setProductName(newProduct.getProductName());
                product.setPrice(newProduct.getPrice());
                product.setDescription(newProduct.getDescription());
                product.setLaunchDate(newProduct.getLaunchDate());
                product.setBrandId(newProduct.getBrandId());
                product.setColors(newProduct.getColors());
                photoService.deleteFile(product.getProductImage());
                try {
                    String productImage = photoService.imageUpload(file);
                    product.setProductImage(productImage);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return productRepository.save(product);
            }).get();
        }catch (NoSuchElementException e){
            throw new ProductsExceptions(ExceptionsResponse.ERROR_CODE.PRODUCT_DOES_NOT_EXIST,"Not have ID product : " + newProduct.getProductId());
        }
        return null;
    }

    public Product addProductWithPicture(MultipartFile file, Product newProduct) throws IOException {
        newProduct.setProductImage(photoService.imageUpload(file));
        return productRepository.save(newProduct);
    }
}
