package ru.leontev.springcource;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestSpring {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                "applicationContext.xml"
        );
        TestBean singletonBean1 = context.getBean("singletonBean", TestBean.class);
        TestBean singletonBean2 = context.getBean("singletonBean", TestBean.class);
        System.out.println(singletonBean2==singletonBean1);
        TestBean prototypeBean1 = context.getBean("prototypeBean", TestBean.class);
        TestBean prototypeBean2 = context.getBean("prototypeBean", TestBean.class);
        System.out.println(prototypeBean1==prototypeBean2);
        context.close();
    }
}
