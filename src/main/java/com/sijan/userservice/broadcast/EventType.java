package com.sijan.userservice.broadcast;

public enum EventType {
    CREATED("CREATED"),
    UPDATED("UPDATED"),
    DELETED("DELETED");

    private final String eventType;

    EventType(String eventType) {
        this.eventType = eventType;
    }

    public String getEventType() {
        return this.eventType;
    }
}
