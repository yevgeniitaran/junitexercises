package com.junitexercises.ch1_5.others;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashMap;
import java.util.Map;

import static junitparams.JUnitParamsRunner.$;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(JUnitParamsRunner.class)
public class HashMapTest {

    private Map<Object, Object> map;

    @Before
    public void createMap() {
        map = new HashMap<>();
    }

    private Object[] getObject() {
        return $(
                $(new Object(), new Object())
        );
    }

    private Object[] getObjectWithNullKey() {
        return new Object[]{
                new Object[]{null, new Object()}
        };
    }

    @Test
    @Parameters(method = "getObject")
    public void putValueShouldBeReturnedFromGet(Object key, Object value) {
        map.put(key, value);
        assertEquals(map.get(key), value);
    }

    @Test
    @Parameters(method = "getObject")
    public void clearShouldClearMap(Object key, Object value) {
        map.put(key, value);
        map.clear();
        assertTrue(map.isEmpty());
    }

    @Test
    @Parameters(method = "getObjectWithNullKey")
    public void insertNullKeyShouldWork(Object key, Object value) {
        map.put(key, value);
        assertEquals(map.get(key), value);
    }
}
