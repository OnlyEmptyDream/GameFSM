package com.notice;

import com.notice.time.TimeNoticeHolder;
import com.notice.time.TimeOutHandler;
import com.notice.time.TimeProcessNotice;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class NoticeUtil {
    static final Map<Integer, TimeNoticeHolder> timeNoticeHolderMap = new ConcurrentHashMap<>();

    private static NoticeRegister register;
    private static NoticeSender sender;

    public static void init(NoticeRegister register, NoticeSender sender){
        NoticeUtil.register = register;
        NoticeUtil.sender = sender;
    }

    public static ProcessNotice getNotice(int id){
        return register.getNotice(id);
    }

    public static void sendNotice(byte processId, ProcessNotice notice, long id){
        try {
            sender.sendNotice(processId, notice, id);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void sendNotice(byte processId, TimeProcessNotice notice, long id,
                                  TimeOutHandler handler, long timeout) {


        sender.sendNotice(processId, notice, id, handler, timeout);
    }

    public static boolean checkTime(TimeProcessNotice notice){
            return true;
    }

    public static void cleanTimeoutNotice(){
        synchronized (NoticeUtil.timeNoticeHolderMap){
            long curTime = System.currentTimeMillis();
            for(Integer index : NoticeUtil.timeNoticeHolderMap.keySet()){
                TimeNoticeHolder holder = NoticeUtil.timeNoticeHolderMap.get(index);
            }
        }
    }
}
