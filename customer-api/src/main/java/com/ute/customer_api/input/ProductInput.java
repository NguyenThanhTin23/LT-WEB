package com.ute.customer_api.input;

import lombok.Data;
import java.util.List;

@Data
public class ProductInput {
    private String title;
    private int quantity;
    private String desc;
    private double price;
    private Long userId;
    private List<Long> categoryIds;
}