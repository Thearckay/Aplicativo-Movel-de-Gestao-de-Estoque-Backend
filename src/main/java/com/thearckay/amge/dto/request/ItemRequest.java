package com.thearckay.amge.dto.request;

public record ItemRequest(
    String name,
    String itemCode,
    Integer quantity,
    Double price,
    String type
) {
}
