package com.example.store3.data;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class Item {

    @NotNull
    @Size(min=3, max=20, message = "{errors.strlen}")
    private String name;

    @NotNull
    @DecimalMin(value = "0.1", message = "{errors.dectoosmall}")
    @DecimalMax(value = "1000", message = "{errors.dectoobig}")
    private float price;

    @NotNull
    @Valid
    private ItemCategory category;

    public Item() {

    }

    public Item(String name, float price, ItemCategory category) {
        this.name = name;
        this.price = price;
        this.category = category;
    }
}
