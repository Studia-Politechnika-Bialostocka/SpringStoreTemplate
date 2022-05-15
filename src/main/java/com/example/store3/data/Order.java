package com.example.store3.data;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;

@Data
@RequiredArgsConstructor

public class Order {
    private final Integer id;
    private final HashMap<Item, Integer> items;
    private final boolean isFinished;


}
