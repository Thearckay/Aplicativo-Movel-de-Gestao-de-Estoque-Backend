package com.thearckay.amge.dto.response;

import java.util.List;

public record DashboardResponse(
    Double totalInventoryValue,
    Double inventoryChangePercentage,
    List<ActivityResponse> recentActivities
) {
}
