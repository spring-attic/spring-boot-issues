package com.example;

import java.time.ZonedDateTime;

public class BarResponse {
    private ZonedDateTime createdAt;

    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(final ZonedDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
