package com.kong.fsm.heart;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
public class ExecutorUtil {

    private static final ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(2, new ThreadFactory() {
        AtomicInteger count = new AtomicInteger(0);

        @Override
        public Thread newThread(Runnable r) {
            int curCount = count.incrementAndGet();
            return new Thread(r, "线程池 - " + curCount);
        }
    });

    /**
     * 定时事件
     *
     * @param command      具体要执行的内容
     * @param initialDelay 延迟时间
     * @param period       间隔
     *                     默认单位为毫秒
     * @return ScheduledFuture
     */
    public static ScheduledFuture<?> scheduleAtFixedRate(Runnable command, long initialDelay, long period) {
        return scheduledThreadPool.scheduleAtFixedRate(command, initialDelay, period, TimeUnit.MILLISECONDS);
    }
}
