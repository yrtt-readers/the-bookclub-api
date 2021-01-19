package com.readers.thebookclub;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import java.util.Map;
import java.util.HashMap;
 
public class TestOnConnect {
 
    @Test
    public void checkConnection() {
        Map<String, Object> input = new HashMap<String, Object>();
        Response r = new Response("Hello World",input);
        assertEquals("Hello World","Hello World");
    }
}