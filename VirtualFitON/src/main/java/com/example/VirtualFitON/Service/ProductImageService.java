package com.example.VirtualFitON.Service;

import com.example.VirtualFitON.Exceptions.BrandNotFoundException;
import com.example.VirtualFitON.Exceptions.ProductImageNotFoundException;
import com.example.VirtualFitON.Models.Brand;
import com.example.VirtualFitON.Models.Product;
import com.example.VirtualFitON.Models.ProductImage;
import com.example.VirtualFitON.Repositories.ProductImageRepository;
import com.example.VirtualFitON.Repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service

public class ProductImageService {

    private final ProductImageRepository productImageRepository;
    private final ProductRepository productRepository;

    public ProductImage saveProductImage(ProductImage productImage){
        return productImageRepository.save(productImage);
    }

    public List<ProductImage> saveProductImages(List<ProductImage> productImages){
        return productImageRepository.saveAll(productImages);
    }

    public List<ProductImage> getAllProductImages(){
        return productImageRepository.findAll();
    }

    public ProductImage findProductImageById(long id)
    {
        if(productImageRepository.findById(id).isEmpty())
            throw new BrandNotFoundException("Requested brand doesn't exist!");
        return productImageRepository.findById(id).get();
    }


    public String deleteProductImage(long id){
        productImageRepository.deleteById(id);
        return "Product Image delete Successful!!" +id;
    }

    public ProductImage updateProductImage(ProductImage productImage){
        ProductImage existingProductImage = productImageRepository.findById(productImage.getId()).orElse(null);
        existingProductImage.setImageData(productImage.getImageData());
        existingProductImage.setColour(productImage.getColour());
        return productImageRepository.save(existingProductImage);
    }



    @Autowired
    public ProductImageService(ProductImageRepository productImageRepository, ProductRepository productRepository) {
        this.productImageRepository = productImageRepository;
        this.productRepository = productRepository;
    }

    public List<byte[]> getImageDataListByProductId(String Id) {
        try {
            List<ProductImage> productImages = productImageRepository.findByProductProductId(Id);
            List<byte[]> imageDataList = new ArrayList<>();
            for (ProductImage productImage : productImages) {
                imageDataList.add(productImage.getImageData());
            }
            return imageDataList;
        } catch (ProductImageNotFoundException ex) {
            throw new ProductImageNotFoundException("No product images found for product ID: " + Id);
        }
    }




    private byte[] convertToJPEG(byte[] imageData) {
        try {
            ByteArrayInputStream bis = new ByteArrayInputStream(imageData);
            BufferedImage bImage = ImageIO.read(bis);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ImageIO.write(bImage, "jpg", bos);
            return bos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void saveProductImage(String productId, String colour, MultipartFile image) throws IOException {

        Product product=productRepository.findByProductId(productId);
        ProductImage productImage=new ProductImage();
        productImage.setImageData(image.getBytes());
        productImage.setColour(colour);
        productImage.setProduct(product);
        productImageRepository.save(productImage);

    }
}
