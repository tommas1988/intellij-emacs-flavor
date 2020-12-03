package pers.tommas.emacsflavor.utils;

import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Field;

public class ReflectUtil {
    public static <T, R> R getField(@NotNull T target, String fieldName) {
        try {
            Field field = target.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            return (R) field.get(target);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
