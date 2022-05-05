package com.sijan.userservice.broadcast;

import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class BroadcastRecord {
    private String source;
    private EventType eventType;
    private ObjectNode record;
    private String timestamp;
}
