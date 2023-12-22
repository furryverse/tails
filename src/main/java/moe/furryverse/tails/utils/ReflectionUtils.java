package moe.furryverse.tails.utils;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Set;

public class ReflectionUtils {
    public static Set<String> getAllStaticPublicStrings(Class<?> clazz) throws IllegalAccessException {
        Set<String> staticStrings = new HashSet<>();

        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            if (isStaticPublicString(field)) {
                String value = (String) field.get(null);
                staticStrings.add(value);
            }
        }

        return staticStrings;
    }

    private static boolean isStaticPublicString(Field field) {
        int modifiers = field.getModifiers();
        return java.lang.reflect.Modifier.isStatic(modifiers)
                && java.lang.reflect.Modifier.isPublic(modifiers)
                && field.getType() == String.class;
    }
}
