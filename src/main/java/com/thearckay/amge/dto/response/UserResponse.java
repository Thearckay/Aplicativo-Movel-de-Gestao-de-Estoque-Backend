package com.thearckay.amge.dto.response;

import com.thearckay.amge.entity.Item;

import java.util.List;

public record UserResponse(
        String name,
        List<Item> itemList
) {

    public UserResponse(String name, List<Item> itemList) {
        this.name = name;
        this.itemList = itemList;
    }
}
