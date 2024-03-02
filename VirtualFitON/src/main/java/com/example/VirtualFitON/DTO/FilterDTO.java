package com.example.VirtualFitON.DTO;

import java.math.BigDecimal;

public class FilterDTO {
    private String categories;
    private String size;
    private PriceRange price;
    private String color;
    private String gender;
    private String brand;

    public FilterDTO(String categories, String size, PriceRange price, String color, String gender, String brand) {
        this.categories = categories;
        this.size = size;
        this.price = price;
        this.color = color;
        this.gender = gender;
        this.brand = brand;
    }

    public FilterDTO() {

    }

    public String getCategories() {
        return categories;
    }

    public void setCategories(String categories) {
        this.categories = categories;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public PriceRange getPrice() {
        return price;
    }

    public void setPrice(PriceRange price) {
        this.price = price;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public static class PriceRange {
        private BigDecimal min;
        private BigDecimal max;

        public PriceRange(BigDecimal min, BigDecimal max) {
            this.min = min;
            this.max = max;
        }

        public BigDecimal getMin() {
            return min;
        }

        public void setMin(BigDecimal min) {
            this.min = min;
        }

        public BigDecimal getMax() {
            return max;
        }

        public void setMax(BigDecimal max) {
            this.max = max;
        }
    }
}
