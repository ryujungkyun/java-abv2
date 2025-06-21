package annotation.java;

import java.util.ArrayList;
import java.util.List;

public class SuppressWarningCase {

    @SuppressWarnings("unused")
    public void unusedWarning() {
        // 사용되지 않는 변수 경고 억제
        int unusedVariable = 10;
    }

    @SuppressWarnings("deprecation")
    public void deprecatedMethod() {
        // 더 이상 사용되지 않는 메서드 호출
        java.util.Date data = new java.util.Date();
        int date1 = data.getDate();
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    public void uncheckedCast() {
        // 제네릭 타입 캐스팅 경고 억제, raw type 사용 경고
        List list = new ArrayList();

        // 제너릭
        List<String> stringList = (List<String>) list;
    }

    @SuppressWarnings("all")
    public void suppressAllWarning() {
        //모든 경고 허용
        java.util.Date data = new java.util.Date();
        data.getDate();
        List list = new ArrayList();
        List<String> stringList = (List<String>) list;
    }
}
