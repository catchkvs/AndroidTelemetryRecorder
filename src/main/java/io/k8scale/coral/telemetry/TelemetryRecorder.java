package io.k8scale.coral.telemetry;

import io.k8scale.coral.model.Event;
import lombok.extern.log4j.Log4j2;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

@Log4j2
public class TelemetryRecorder {

    private final BlockingQueue<Event> blockingQueue = new LinkedBlockingQueue(500);

    public void addEvent(Event event) {
        blockingQueue.add(event);
    }

    public List<Event> getEvents(int limit) {
        int count = 0;
        List<Event> eventList = new ArrayList<>();
        while(count < limit) {
            try {
                Event event = blockingQueue.poll(1, TimeUnit.SECONDS);
                if(event != null) {
                    eventList.add(event);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                count++;
            }
        }
        return eventList;
    }
}
