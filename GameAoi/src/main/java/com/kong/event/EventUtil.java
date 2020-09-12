package com.kong.event;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@Slf4j
public class EventUtil {
    /**
     * 游戏启动就初始化的监听者列表
     */
    private final static Map<EventType, List<IListener<?>>> PREPARED_LISTENERS = new HashMap<>();

    public static <T> void addListener(IListener<T> listener, EventType type){
        //computeIfAbsent 不覆盖
        List<IListener<?>> listenerList = PREPARED_LISTENERS.computeIfAbsent(type, k -> new ArrayList<>());
        listenerList.add(listener);
    }


    public static void fireEvent(EventType type, Object obj){
        List<IListener<?>> listenerList = PREPARED_LISTENERS.get(type);
        if(listenerList != null){
            for(IListener listener : listenerList){
                try{
                    listener.update(type, obj);
                } catch (Exception e){
                    log.info(e.getMessage());
                }
            }
        }
    }

    public static void fireEvent(EventType type) {
        fireEvent(type, null);
    }
}
