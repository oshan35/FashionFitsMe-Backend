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
import java.util.ArrayList;
import java.util.List;

@Service

public class ProductImageService {

    @Autowired
    ProductImageRepository productImageRepository;

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
}
