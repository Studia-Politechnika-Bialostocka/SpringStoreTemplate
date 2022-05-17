package com.example.store3.data;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.Size;

@Data
@RequiredArgsConstructor
public class ItemCategory {

    @Size(min=3, max=20, message = "{errors.strlen}")
    private final String name;
}
