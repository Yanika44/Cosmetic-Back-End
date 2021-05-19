package com.example.cosmetic2.service;

import com.example.cosmetic2.Exceptions.ExceptionsResponse;
import com.example.cosmetic2.Exceptions.ProductsExceptions;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.ProtocolException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;

@Service
public class PhotoService {

    public String imageUpload(MultipartFile file) throws IOException {
        try{
            File imageFile = new File (  "./photo/" + file.getOriginalFilename());
            imageFile.createNewFile();
            FileOutputStream fos = new FileOutputStream(imageFile);
            fos.write(file.getBytes());
            fos.close();
            String test = file.getOriginalFilename();
            return file.getOriginalFilename();
        }catch (FileNotFoundException e){
            throw new ProductsExceptions(ExceptionsResponse.ERROR_CODE.NOT_HAVE_FILE,"Please input your file");
        }
    }

    public ResponseEntity<byte[]> getPhoto(String file) throws IOException {
        try {
            System.out.println("Filename : " + file);
            FileInputStream fileInputStream = new FileInputStream("photo" + "/" + file);
            byte[] image = fileInputStream.readAllBytes();
            return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(image);
        }catch (FileNotFoundException e) {
            throw new ProductsExceptions(ExceptionsResponse.ERROR_CODE.PRODUCT_DOES_NOT_EXIST, "Photo file name : " + file +" doesn't exists");
        }

    }

    public void deleteFile(String file){
        try{
            Files.delete(Path.of("photo"+ "/"+ file));
        }catch (NoSuchFileException e){
            e.printStackTrace();
            throw new ProductsExceptions(ExceptionsResponse.ERROR_CODE.PRODUCT_DOES_NOT_EXIST,"This file doesn't exists");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
