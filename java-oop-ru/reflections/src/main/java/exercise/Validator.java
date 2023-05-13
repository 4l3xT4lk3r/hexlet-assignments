package exercise;


import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import java.util.*;

// BEGIN
public class Validator {
    public static List<String> validate(Address address) {
        List<String> validations = new ArrayList<>();
        Class<? extends Address> clazz = address.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(NotNull.class)) {
                field.setAccessible(true);
                try {
                    if (field.get(address) == null) {
                        validations.add(field.getName());
                    }
                } catch (IllegalAccessException exception) {
                    exception.printStackTrace();
                }
            }
        }
        return validations;
    }

    public static Map<String, List<String>> advancedValidate(Address address) {
        Map<String, List<String>> validations = new HashMap<>();
        Class<? extends Address> clazz = address.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            List<String> errors = new ArrayList<>();
            field.setAccessible(true);
            try {
                if (field.isAnnotationPresent(NotNull.class)) {
                    if (field.get(address) == null) {
                        errors.add("can not be null");
                    }
                }
                if (field.isAnnotationPresent(MinLength.class)) {
                    String value = (String) field.get(address);
                    int minLength = field.getAnnotation(MinLength.class).minLength();
                    if (field.get(address) == null || value.length() < minLength) {
                        errors.add("length less than " + minLength);
                    }
                }
                if (errors.size() > 0) {
                    validations.put(field.getName(), errors);
                }
            } catch (IllegalAccessException illegalAccessException) {
                illegalAccessException.printStackTrace();
            }
        }
        return validations;
    }
}

// END
