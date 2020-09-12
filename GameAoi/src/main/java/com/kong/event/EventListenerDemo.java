package com.kong.event;

public class EventListenerDemo implements IListener<Void>{
    @Override
    public void update(EventType type, Void param) {
        System.out.println("receive Listener");
    }
}
