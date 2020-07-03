package com.notice.time;

import com.notice.NoticeUtil;

public class NoticeTimeOutChecker implements Runnable {
    @Override
    public void run() {
        try {
            NoticeUtil.cleanTimeoutNotice();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
