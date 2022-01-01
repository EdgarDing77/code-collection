package jvm;

import java.io.Serializable;

/**
 * Description:
 *
 * @author:edgarding
 * @date:2020/11/7
 **/
public class OverLoad {
    public static void say(Character character) {
        System.out.println("Hello character");
    }

    public static void say(char arg) {
        System.out.println("Hello char");
    }

    public static void say(int arg) {
        System.out.println("Hello int arg");
    }

    public static void say(long arg) {
        System.out.println("Hello long arg");
    }

    public static void say(char... arg) {
        System.out.println("Hello char...");
    }

    public static void say(Serializable serializable) {
        System.out.println("Hello serializable");
    }

    public static void main(String[] args) {
        say('a');
    }
}
