package com.thearckay.amge.dto.response;

import com.thearckay.amge.entity.Item;

import java.util.List;

public record LoginResponse(
        String name,
        String email,
        List<Item> itemList
) {


}
