package com.zxl.studybugly;

/**
 * @Author wyb
 * @Date
 * @Describe
 */
public class BugClass {

    public static BugClass init = new BugClass();


    public String getBugMes() {
        //tring mesNull = null;
        String mesNull = "对不起，我又来了测更新";
        return "这是一个bug" + mesNull;
    }
}
