package com.example.store3.data;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
//@RequiredArgsConstructor
//@NoArgsConstructor
public class Item {
    private Integer id;
    @NonNull
    @NotNull
    @Size(min = 3, max = 10)
    private String name;
    @NotNull
    @DecimalMin(value = "0.01")
    private float price;
    @NotNull
    private ItemCategory category;

    public Item() {

    }

    public Item(int id, String name, float price, ItemCategory itemCategory) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.category = itemCategory;
    }


}
