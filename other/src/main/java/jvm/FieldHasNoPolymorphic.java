package jvm;

/**
 * Description:
 * 字段无多态性
 *
 * @author:edgarding
 * @date:2020/11/7
 **/
public class FieldHasNoPolymorphic {
    static class Father {
        public int money = 1;

        public Father() {
            money = 2;
            showMoney();
        }

        public void showMoney() {
            System.out.println("Father's money:" + money);
        }

        public int getMoney() {
            return money;
        }
    }

    static class Son extends Father {
        public int money = 3;

        public Son() {
            money = 4;
            showMoney();
        }

        @Override
        public void showMoney() {
            System.out.println("Son's money:" + money);
        }

        @Override
        public int getMoney() {
            return money;
        }
    }

    public static void main(String[] args) {
        Father guy = new Son();
        System.out.println("this guy's money:" + guy.money);
    }
}
