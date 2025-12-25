package com.szu.admin.common.context;

public class AdminContext {

    private static final ThreadLocal<Integer> USER_ID = new ThreadLocal<>();
    private static final ThreadLocal<Integer> ROOT_PRIV = new ThreadLocal<>();

    public static void setUserId(Integer userId) {
        USER_ID.set(userId);
    }

    public static Integer getUserId() {
        return USER_ID.get();
    }

    public static void setRootPriv(Integer rootPriv) {
        ROOT_PRIV.set(rootPriv);
    }

    public static Integer getRootPriv() {
        return ROOT_PRIV.get();
    }

    public static void clear() {
        USER_ID.remove();
        ROOT_PRIV.remove();
    }
}
