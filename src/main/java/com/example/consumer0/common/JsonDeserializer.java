package com.example.consumer0.common;

import com.example.consumer0.model.Customer;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Deserializer;

import java.util.Map;

public class JsonDeserializer<T> implements Deserializer<T> {


    private ObjectMapper deserializer;
    private Class<T> deserializeClass;
    public JsonDeserializer(Class<T> deserializeClass) {
        this.deserializeClass = deserializeClass;
        deserializer=new ObjectMapper();
    }
    public JsonDeserializer() {
        //Constructor
    }
    @Override
    @SuppressWarnings("unchecked")
    public void configure(Map<String, ?> map, boolean b) {
        if(deserializeClass == null) {
            deserializeClass = (Class<T>) map.get("serializedClass");
        }
    }
    @Override
    public T deserialize(String s, byte[] bytes) {
        if(bytes == null){
            return null;
        }
        try {
            return deserializer.readValue(bytes, deserializeClass);
        } catch (Exception e) {
            return null;
        }
    }
   @Override
    public void close() {
        // close method
    }
}