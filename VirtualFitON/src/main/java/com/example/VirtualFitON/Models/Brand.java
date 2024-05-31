//package com.example.VirtualFitON.Models;
//import jakarta.persistence.*;
//
//
//@Entity
//@Table(name="brand")
//public class Brand {
//
//    @Id
//    @Column(name = "brand_id")
//
//    private String brandId;
//
//    @Column(name = "brand_name")
//    private String brandName;
//
//    public Brand(String brandId, String brandName) {
//        this.brandId = brandId;
//        this.brandName = brandName;
//    }
//
//    public Brand() {
//    }
//
//    public String getBrandId() {
//        return brandId;
//    }
//
//    public void setBrandId(String brandId) {
//        this.brandId = brandId;
//    }
//
//    public String getBrandName() {
//        return brandName;
//    }
//
//    public void setBrandName(String brandName) {
//        this.brandName = brandName;
//    }
//}

package com.example.VirtualFitON.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Brand {

    @Id
    private String brandId;
    private String name;
    @Lob  // To store large objects
    private byte[] productMedia;
}




