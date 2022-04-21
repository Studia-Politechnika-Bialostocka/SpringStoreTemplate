package com.example.store3.data;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Item {
    private final String name;
    private final float price;
    private final ItemCategory category;
}
