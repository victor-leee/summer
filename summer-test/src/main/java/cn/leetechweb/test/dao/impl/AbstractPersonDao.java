package cn.leetechweb.test.dao.impl;

import cn.leetechweb.test.dao.PersonDao;
import cn.leetechweb.test.model.Person;

import java.util.HashMap;
import java.util.Map;

/**
 * Project Name: summer
 * Create Time: 2020/11/26 10:31
 *
 * @author junyu lee
 **/
public abstract class AbstractPersonDao implements PersonDao {

    protected final Map<String, Person> personMap = new HashMap<>(256);

    @Override
    public void insert(Person person) {
        if (person == null) {
            throw new NullPointerException("Person不应为null");
        }
        if (person.getName() == null) {
            throw new NullPointerException("Person名称不应为null");
        }
        this.personMap.put(person.getName(), person);
    }
}
