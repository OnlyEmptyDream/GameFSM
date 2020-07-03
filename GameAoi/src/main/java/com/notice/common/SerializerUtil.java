package com.notice.common;

import io.protostuff.LinkedBuffer;
import io.protostuff.ProtostuffIOUtil;
import io.protostuff.Schema;
import io.protostuff.runtime.RuntimeSchema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SerializerUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(SerializerUtil.class);

    public SerializerUtil() {
    }

    public static <T> byte[] encode(T object, Class<T> clazz) {
        Schema<T> schema = RuntimeSchema.getSchema(clazz);
        LinkedBuffer buffer = LinkedBuffer.allocate();
        return ProtostuffIOUtil.toByteArray(object, schema, buffer);
    }

    public static <T> T decode(byte[] bytes, Class<T> clazz) {
        Object object;
        try {
            object = clazz.newInstance();
        } catch (Exception var4) {
            LOGGER.error("Protostuff反序列化时创建实例失败,Class:" + clazz.getName(), var4);
            return null;
        }
        return  decode(bytes, clazz, (T)object);
    }

    public static <T> T decode(byte[] bytes, Class<T> clazz, T object) {
        Schema<T> schema = RuntimeSchema.getSchema(clazz);
        ProtostuffIOUtil.mergeFrom(bytes, object, schema);
        return object;
    }
}

