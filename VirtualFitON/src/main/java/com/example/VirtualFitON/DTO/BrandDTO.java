package com.example.VirtualFitON.DTO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class BrandDTO {

    private String brandId;
    private String brandName;
    private byte[] productMedia;

}
