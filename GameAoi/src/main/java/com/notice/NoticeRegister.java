package com.notice;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public abstract class NoticeRegister {
    private Map<Integer, Class<? extends ProcessNotice>> noticeMap = new HashMap<>();

    private Map<Integer, Class<? extends NoticeAction>> actionMap = new HashMap<>();

    public NoticeRegister(){registerNotice();}

    public void register(NoticeRegister other){
        other.noticeMap.forEach((k, v)->{
            NoticeAction action = other.getAction(k);
            ProcessNotice notice = other.getNotice(k);
            register(k, notice, action);
        });
    }

    protected  ProcessNotice getNotice(int id){
        Class<? extends ProcessNotice> clazz = noticeMap.get(id);
        if(clazz == null){
            return null;
        }
        ProcessNotice notice = null;
        try {
            notice = clazz.newInstance();
        } catch (Exception e) {
            log.error("创建notice失败：{}", clazz.getName());
            e.printStackTrace();
        }
        return notice;
    }

    protected  NoticeAction getAction(int id){
        Class<? extends NoticeAction> clazz = actionMap.get(id);
        if(clazz == null){
            return null;
        }
        NoticeAction noticeAction = null;
        try {
            noticeAction = clazz.newInstance();
        } catch (Exception e) {
            log.error("创建action失败：{}", clazz.getName());
            e.printStackTrace();
        }
        return noticeAction;
    }

    public void register(int id, ProcessNotice notice, NoticeAction action){
        if(noticeMap.containsKey(id)){
            throw new RuntimeException("notice注册重复");
        }
        if(notice.id() != id){
            throw new RuntimeException("notice注册id和自身id不一致：" + notice.getClass().getName());
        }
        noticeMap.put(id, notice.getClass());
        actionMap.put(id, action.getClass());
    }

    public abstract void registerNotice();
}
