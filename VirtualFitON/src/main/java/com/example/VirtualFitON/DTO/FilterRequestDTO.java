package com.example.VirtualFitON.DTO;

import java.util.List;

public class FilterRequestDTO {
    private int minPrice;
    private int maxPrice;
    private List<Filter> selectedFilters;

    public FilterRequestDTO(int minPrice, int maxPrice, List<Filter> selectedFilters) {
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
        this.selectedFilters = selectedFilters;
    }

    public FilterRequestDTO() {
    }

    public int getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(int minPrice) {
        this.minPrice = minPrice;
    }

    public int getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(int maxPrice) {
        this.maxPrice = maxPrice;
    }

    public List<Filter> getSelectedFilters() {
        return selectedFilters;
    }

    public void setSelectedFilters(List<Filter> selectedFilters) {
        this.selectedFilters = selectedFilters;
    }
}
