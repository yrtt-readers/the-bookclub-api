package com.readers.thebookclub;

import java.util.Map;
import java.util.HashMap;

public final class ErrorHandler{
    public final static Map<Integer,String> message = new HashMap<Integer,String>();

    static{
        message.put(400,"Invalid Input - isbn");
        message.put(401,"Invalid Input - json");
        message.put(503,"Service Unavailable");
    }
}