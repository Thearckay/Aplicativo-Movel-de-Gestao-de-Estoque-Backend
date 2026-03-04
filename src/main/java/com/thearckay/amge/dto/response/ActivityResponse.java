package com.thearckay.amge.dto.response;

public record ActivityResponse(
    String itemName,
    String statusDescription,
    String timeAgo,
    String type
) {
}
