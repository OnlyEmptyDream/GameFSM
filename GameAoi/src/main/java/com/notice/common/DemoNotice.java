package com.notice.common;

import com.notice.ProcessNotice;
import lombok.Data;

@Data
public class DemoNotice extends ProcessNotice {
    String word;

    @Override
    public int id() {
        return 1;
    }

    @Override
    public byte[] encode() {
        return SerializerUtil.encode(this, DemoNotice.class);
    }

    @Override
    public void decode(byte[] bytes) {
        SerializerUtil.decode(bytes, DemoNotice.class, this);
    }
}
