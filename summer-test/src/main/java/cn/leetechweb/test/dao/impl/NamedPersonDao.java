package cn.leetechweb.test.dao.impl;

import cn.leetechweb.summer.bean.annotation.Component;
import cn.leetechweb.test.model.Person;

/**
 * Project Name: summer
 * Create Time: 2020/11/26 10:33
 *
 * @author junyu lee
 **/
@Component
public class NamedPersonDao extends AbstractPersonDao {

    @Override
    public Person find(String name) {
        return personMap.get(name);
    }

}
