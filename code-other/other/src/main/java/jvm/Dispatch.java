package jvm;

/**
 * Description:
 *
 * @author:edgarding
 * @date:2020/11/7
 **/
interface Human {

}

class Woman implements Human {

}

class Man implements Human {

}

public class Dispatch {
    private void say(Human human) {
        System.out.println("this is human");
    }

    private void say(Woman woman) {
        System.out.println("this is woman");
    }

    private void say(Man man) {
        System.out.println("this is man");
    }

    public static void main(String[] args) {
        Dispatch dispatch = new Dispatch();
        Woman woman = new Woman();
        Human man = new Man();
        dispatch.say(woman);
        dispatch.say(man);
    }
}
