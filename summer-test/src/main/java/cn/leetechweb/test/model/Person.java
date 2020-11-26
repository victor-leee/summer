package cn.leetechweb.test.model;

/**
 * Project Name: summer
 * Create Time: 2020/11/26 10:29
 *
 * @author junyu lee
 **/
public class Person {

    private int age;

    private String name;

    public Person() {
    }

    public Person(int age, String name) {
        this.age = age;
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
