package io.k8scale.coral.model;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ClientMessage {
    private String sessionId;
    private String data;
    private String authToken;
    private String command;

}
