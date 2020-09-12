package com.kong.event;

public class EventTest {
    public static void main(String[] args) {
        init();

        EventUtil.fireEvent(EventTypeImpl.EVENTDEMO, null);
    }

    private static void init() {
        EventUtil.addListener(new EventListenerDemo(), EventTypeImpl.EVENTDEMO);
    }
}
