package de.techgamez.pleezon.utils;

import java.lang.reflect.Field;

public class ReflectionUtils {

    public static Object getDeclaredField(Object obj, String name) {
        try {
            Field f = obj.getClass().getDeclaredField(name);
            f.setAccessible(true);
            return f.get(obj);
        }catch(Exception x) {}
        return null;
    }

}