package com.notice.scene;

import com.notice.NoticeAction;
import com.notice.common.DemoNotice;

public class DemoAction extends NoticeAction<DemoNotice> {
    @Override
    public void doAction(DemoNotice notice) {
        System.out.println(notice.getWord());
    }
}
