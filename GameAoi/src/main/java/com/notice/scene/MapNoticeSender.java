package com.notice.scene;

import com.notice.NoticeSender;
import com.notice.ProcessNotice;
import com.notice.time.TimeOutHandler;
import com.notice.time.TimeProcessNotice;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MapNoticeSender implements NoticeSender {
    @Override
    public void sendNotice(byte processId, ProcessNotice notice, long id) {
            for (String hostId : notice.getHosts()) {
                try{
                    ServerSocket server = new ServerSocket(8888);

                    // 阻塞式的等待连接
                    Socket client = server.accept();
                    DataOutputStream writer = new DataOutputStream(client.getOutputStream());
                    System.out.println(notice.encode().length);
                    writer.write(notice.encode());
                    writer.flush();
                    writer.close();
                    client.close();
                    server.close();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
    }

    @Override
    public void sendNotice(byte processId, TimeProcessNotice notice, long id, TimeOutHandler handler, long timeout) {

    }
}
