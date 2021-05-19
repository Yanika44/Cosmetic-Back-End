package com.example.cosmetic2.controller;

import com.example.cosmetic2.model.Product;
import com.example.cosmetic2.service.PhotoService;
import com.example.cosmetic2.service.ProductColorService;
import com.example.cosmetic2.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@CrossOrigin(origins = "*")
@RestController
public class ProductController {
    @Autowired
    ProductService productService;

    @Autowired
    PhotoService photoService;

    @GetMapping("/product")
    public List<Product> getAllProduct(){
        return productService.getAllProduct();
    }

    @GetMapping("/product/{id}")
    public Product findById(@PathVariable String id){
        return productService.findById(id);
    }

    @GetMapping("/product/photo/{file}")
    public ResponseEntity<byte[]> getPhoto(@PathVariable String file) throws IOException {
        return photoService.getPhoto(file);
    }

    @PostMapping("/product/add")
    public Product addProductWithPhoto(@RequestParam("file")MultipartFile file, @RequestPart Product product) throws IOException {
        product.setProductId(UUID.randomUUID().toString());
        return productService.addProductWithPicture(file, product); 
    }

    @PostMapping("/product/add/photo")
    public String imageUpload(@RequestParam("file")MultipartFile file) throws IOException {
        return photoService.imageUpload(file);
    }

    @PostMapping("/product/add/data")
    public Product addProduct(@RequestBody Product newProduct){
        return productService.addProduct(newProduct);
    }

    @PutMapping("/product/edit/data/{id}")
    public Product editProduct(@RequestBody Product newProduct, @PathVariable String id) {
        return productService.editProduct(newProduct, id);
    }

    @PutMapping("/product/edit/{id}")
    public Product editProductAndPicture(@RequestParam("file")MultipartFile file, @RequestPart Product newProduct, @PathVariable String id){
        return productService.editProductAndPicture(file, newProduct, id);
    }

    @DeleteMapping("/product/{id}")
    public void deleteProduct(@PathVariable String id){
        productService.deleteProduct(id);
    }

    @DeleteMapping("/product/photo/{file}")
    public void deleteFile(@PathVariable String file) {
        photoService.deleteFile(file);
    }
}
