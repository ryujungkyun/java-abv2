package annotation.basic;

import util.MyLogger;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface AnnoElement {
    String value();
    int count() default 0;
    String[] tags() default {};

    //MyLogger data(); 직접 만든 타입은 불가능
    Class<? extends MyLogger> annoData() default MyLogger.class; // 클래스 정보는 가능
    //자바는 기본으로 제공하는 타입만 가능하다
}
