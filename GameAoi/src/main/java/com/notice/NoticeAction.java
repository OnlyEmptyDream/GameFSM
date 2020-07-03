package com.notice;

import com.notice.common.DemoNotice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class NoticeAction<T extends ProcessNotice> {

    protected static final Logger LOGGER = LoggerFactory.getLogger(ProcessNotice.class);

    protected T notice;

    public void doAction() {
        doAction(notice);
    }

    public abstract void doAction(T notice);

}
