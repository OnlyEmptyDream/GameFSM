package com.notice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public abstract class ProcessNotice {
    protected static final Logger LOGGER = LoggerFactory.getLogger(ProcessNotice.class);
    protected transient List<String> hosts = new ArrayList<>();

    public abstract int id();
    public abstract byte[] encode();
    public abstract void decode(byte[] bytes);
    public int hostId(){return 0;}
    public boolean isTimeOut(){
        return  false;
    }
    public void addHost(String hostId){
        hosts.add(hostId);
    }

    public List<String> getHosts(){
        return hosts;
    }

    public void addHost(List<String> hosts){
        this.hosts.addAll(hosts);
    }

    public void send(byte processId, long queueId){
        NoticeUtil.sendNotice(processId, this, queueId);
    }
}
