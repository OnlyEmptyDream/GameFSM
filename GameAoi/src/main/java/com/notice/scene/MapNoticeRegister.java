package com.notice.scene;

import com.notice.NoticeRegister;
import com.notice.common.DemoNotice;

public class MapNoticeRegister extends NoticeRegister {

    @Override
    public void registerNotice() {
        register(1, new DemoNotice(), new DemoAction());
    }
}
