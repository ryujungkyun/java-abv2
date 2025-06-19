package reflection;

import java.lang.reflect.Constructor;

public class ConstructV1 {

    public static void main(String[] args) throws ClassNotFoundException {
        Class<?> aClass = Class.forName("reflection.data.BasicData");

        System.out.println("==== constructors() ======");

        //생성자는 다른 필드, 메서드와 달리 상속 되지 않는다.
        Constructor<?>[] constructors = aClass.getConstructors();
        for (Constructor<?> constructor : constructors) {
            System.out.println(constructor);
        }

        System.out.println("==== constructors() ======");
        Constructor<?>[] declaredConstructors = aClass.getDeclaredConstructors();
        for (Constructor<?> constructor : declaredConstructors) {
            System.out.println(constructor);
        }
    }
}
