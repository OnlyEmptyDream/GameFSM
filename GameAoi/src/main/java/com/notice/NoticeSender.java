package com.notice;

import com.notice.time.TimeOutHandler;
import com.notice.time.TimeProcessNotice;

import java.io.IOException;

public interface NoticeSender {
    void sendNotice(byte processId, ProcessNotice notice, long id) throws IOException;

    void sendNotice(byte processId, TimeProcessNotice notice, long id, TimeOutHandler handler, long timeout);
}
