package com.notice.server;

import com.notice.NoticeUtil;
import com.notice.common.DemoNotice;
import com.notice.scene.MapNoticeRegister;
import com.notice.scene.MapNoticeSender;

import java.io.DataInputStream;
import java.net.Socket;

public class ServerMain {
    public static void main(String[] args) {
        NoticeUtil.init(new MapNoticeRegister(),new MapNoticeSender());
        DemoNotice demoNotice = new DemoNotice();
        demoNotice.addHost("localhost");
        demoNotice.setWord("hello world!");
        demoNotice.send((byte)1, 5);
    }
}
