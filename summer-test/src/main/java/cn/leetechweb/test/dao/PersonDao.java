package cn.leetechweb.test.dao;

import cn.leetechweb.test.model.Person;

import java.util.List;

/**
 * Project Name: summer
 * Create Time: 2020/11/26 10:28
 *
 * @author junyu lee
 **/
public interface PersonDao {

    /**
     * 插入一个人
     * @param person 人
     */
    void insert(Person person);

    /**
     * 根据名称查找人
     * @param name 名称
     * @return 人
     */
    default Person find(String name) {
        return null;
    }

    /**
     * 根据target年龄查找所有人
     * @param age 年龄
     * @return 目标列表
     */
    default List<Person> find(int age) {
        return null;
    }

}
