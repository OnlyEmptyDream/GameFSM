package com.notice.time;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Getter
@Setter
@Slf4j
public class TimeNoticeHolder {
    /**
     * 唯一序号
     */
    private int index;

    /**
     * 开始时间
     */
    private long startTime;

    /**
     * 超时时间
     */
    private long waitTime;

    /**
     * 超时处理器
     */
    private TimeOutHandler handler;

    /**
     * 当前notice
     */
    private TimeProcessNotice notice;


    public boolean checkTimeout(long curTime) {
        if (curTime - startTime > waitTime) {
            log.info("notice请求超时：{}", notice.getClass().getName());
            handler.timeout(notice);
            return true;
        }
        return false;
    }
}
