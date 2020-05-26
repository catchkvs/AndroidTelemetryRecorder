package io.k8scale.coral.model;

import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * Created by @karmveer on 2020-May-17
 */
@Data
public class TelemetryData {
    private String source;
    private String referenceId;
    private List<Event> events;
    private Map<String, String> deviceInfo;
}
