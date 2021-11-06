package com.mao.sleeve.anno;


import org.junit.Test;

import java.lang.annotation.*;

/**
 * @ClassName: TestAnno
 * @Description: TODO
 * @Author 毛毛
 * @CreateDate 2021/11/01/周一 12:58
 * @Version: v1.0
 */
public class TestAnno {
    @Test
    public void test(){
        Class<Student> studentClass = Student.class;
        MyAnnotations annotations = studentClass.getAnnotation(MyAnnotations.class);
        for (MyAnnotation myAnnotation : annotations.value()) {
            System.out.println(myAnnotation.description());
        }
    }

}

@Inherited
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(MyAnnotations.class)
@interface MyAnnotation {
    String description();
}
@Inherited
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@interface MyAnnotations {
    MyAnnotation[] value();
}

@MyAnnotation(description = "person1")
@MyAnnotation(description = "person2")
class Person {
}

@MyAnnotation(description = "student")
@MyAnnotation(description = "person2")
class Student extends Person {
}

