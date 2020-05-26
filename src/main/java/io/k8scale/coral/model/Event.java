package io.k8scale.coral.model;

import java.util.HashMap;
import java.util.Map;

import lombok.Data;
import lombok.ToString;

/**
 * Created by @karmveer on 2020-May-17
 */
@Data
@ToString
public class Event {
    private String name;
    private long eventTime;
    private Map<String, String> additionalData;

    public static Event createEvent(String name) {
        Event event = new Event();
        event.setName(name);
        event.setEventTime(System.currentTimeMillis());
        return event;
    }

    public static Event createEvent(String name, String dataKey, String dataValue) {
        Event event = new Event();
        event.setName(name);
        event.setEventTime(System.currentTimeMillis());
        Map<String, String> data = new HashMap<>();
        data.put(dataKey, dataValue);
        event.setAdditionalData(data);
        return event;
    }
}
