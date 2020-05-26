package io.k8scale.coral.telemetry;

import com.google.gson.Gson;
import io.k8scale.coral.model.ClientMessage;
import io.k8scale.coral.model.Event;
import io.k8scale.coral.model.TelemetryData;
import io.k8scale.coral.websocket.WebsocketClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import java.nio.charset.Charset;
import java.util.Base64;
import java.util.List;
import java.util.TimerTask;

@RequiredArgsConstructor
@Log4j2
public class TelemetryPublisher extends TimerTask {

    private TelemetryRecorder recorder;
    private WebsocketClient client;
    private Gson gson = new Gson();

    @Override
    public void run() {
        doWork();
    }

    public void doWork() {
        try {
            List<Event> events = recorder.getEvents(10);
            boolean send = false;
            if(events !=null && !events.isEmpty()) {
                System.out.println("Publishing telemetry data");
                TelemetryData telemetryData = new TelemetryData();
                telemetryData.setReferenceId("refId1");
                telemetryData.setEvents(events);
                String serializedData = gson.toJson(telemetryData);
                String encodedData = Base64.getEncoder().encodeToString(serializedData.getBytes(Charset.defaultCharset()));

                ClientMessage msg = new ClientMessage();
                msg.setAuthToken("dummy_token");
                msg.setCommand("Telemetry");
                msg.setData(encodedData);
                msg.setSessionId("sessionId");
                send = client.sendMessage(gson.toJson(msg));
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}
