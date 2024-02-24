package com.example.VirtualFitON.Service;

import com.example.VirtualFitON.Exceptions.ProductImageNotFoundException;
import com.example.VirtualFitON.Models.ProductImage;
import com.example.VirtualFitON.Repositories.ProductImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Service

public class ProductImageService {

    @Autowired
    ProductImageRepository productImageRepository;

    public List<ProductImage> getImageListByProductId(String Id) {
        try {
            List <ProductImage> productImages= productImageRepository.findByProductProductId(Id);
            for (ProductImage productImage : productImages) {
                productImage.setImageData(convertToJPEG(productImage.getImageData()));
            }
            return productImages;
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
}
