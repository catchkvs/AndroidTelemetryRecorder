package io.k8scale.coral.websocket;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.TimeUnit;

public class WebsocketClient {
    private OkHttpClient client;
    private WebSocket webSocket;
    public WebsocketClient() {
        client = new OkHttpClient.Builder()
                .readTimeout(0,  TimeUnit.MILLISECONDS)
                .build();
        Request request = new Request.Builder()
                .url("ws://localhost:4040/telemetry")
                .build();

        WebSocket webSocket = client.newWebSocket(request, new WebSocketListener() {
            @Override
            public void onClosed(@NotNull WebSocket webSocket, int code, @NotNull String reason) {
                super.onClosed(webSocket, code, reason);
            }
        });
    }
    
    public boolean sendMessage(String message) {
        return webSocket.send(message);
    }
}
