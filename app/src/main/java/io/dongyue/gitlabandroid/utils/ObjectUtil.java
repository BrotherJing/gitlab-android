package io.dongyue.gitlabandroid.utils;

import java.util.Arrays;

public final class ObjectUtil {
    private ObjectUtil() {}

    public static boolean equals(Object a, Object b) {
        return (a == null) ? (b == null) : a.equals(b);
    }

    public static int hash(Object... values) {
        return Arrays.hashCode(values);
    }
}
