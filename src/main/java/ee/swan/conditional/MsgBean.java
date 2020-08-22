package ee.swan.conditional;

public interface MsgBean {
    default void printMsg() {
        System.out.println("My Bean defalut is running!");
    }
}
