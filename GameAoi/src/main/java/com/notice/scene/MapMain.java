package com.notice.scene;

import com.notice.common.DemoNotice;

import java.io.DataInputStream;
import java.net.Socket;

public class MapMain {
    public static void main(String[] args) {
        try {
            //建立连接
            Socket client = new Socket("localhost", 8888);
            DataInputStream reader = new DataInputStream(client.getInputStream());
            byte[] bytes = new byte[14];
            reader.read(bytes);
            DemoNotice demoNotice = new DemoNotice();
            demoNotice.decode(bytes);
            System.out.println(demoNotice.getWord());
            System.out.println(demoNotice.getHosts());
            reader.close();
            client.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
