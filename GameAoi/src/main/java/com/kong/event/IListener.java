package com.kong.event;

public interface IListener<T> {
    /**
     * 执行事件监听
     */
    void update(EventType type, T param);
}
