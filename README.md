# summer
A micro java framework to study the Spring Framework

### 简短的教程

主入口：
```java
@Summer(value = "cn.leetechweb.summer.bean.annotation")
public class TestBBB {
}
```

```java
        // 构建使用注解的summer容器
        Context context = new AnnotationConfigContext(TestBBB.class);
        // 获取bean名称为service的bean
        Service service = context.getBean("service", Service.class);
```

### 将JavaBean交由Summer管理
1. 使用@Component注解，如果不指定name则默认beanName为类名称
 ```java
 @Component(name = "service")
public class Service {
}
 ```
 
2. 在由Summer容器管理的实例中，使用@Bean注解创建新的Bean
 ```java
@Component
public class FuckDao {

    @Value("123")
    private Integer fuck;

    @Override
    public String toString() {
        return "FuckDao{" +
                "fuck=" + fuck +
                '}';
    }


    @Bean(name = "inner")
    public InnerBean getInnerBean(@Resource(name = "Dao") BaseDao baseDao) {
        return new InnerBean(baseDao);
    }

    @Bean(name = "wtf")
    public WTFDao getWtfDao(InnerBean innerBean) {
        return new WTFDao(innerBean);
    }
}
 ```
 如果在@Bean注解上使用了name属性，则返回的bean在容器中的名称为name，否则为返回类型的名称
 
 ---
 ### 构建依赖注入
 #### 构建bean之间的依赖注入关系
 1. 通过构造函数实现依赖注入关系：
 ```java
 @Component
public class SecondService {

    Service service;

    @Override
    public String toString() {
        return "SecondService{" +
                "service=" + service +
                ", fuckDao=" + fuckDao +
                ", baseDao=" + baseDao +
                '}';
    }

    public SecondService(Service service) {
        this.service = service;
    }

}
 ```
 将需要实现依赖注入的依赖双方交由Summer管理，然后指定**一个**构造函数注入需要的实例，Summer会替你注入对应的Bean
 
 2. 通过setter实现依赖注入关系：
 ```java
@Component(name = "service")
public class Service {
    Dao dao;

    FuckDao fuckDao;

    @Override
    public String toString() {
        return "Service{" +
                "dao=" + dao +
                ", fuckDao=" + fuckDao +
                ", b=" + b +
                ", isError=" + isError +
                '}';
    }

    @Autowired
    public void fuckfuckDao(FuckDao fuckDao, Dao dao) {
        this.fuckDao = fuckDao;
        this.dao = dao;
    }
}
 ```
在类方法中使用@Autowired注解标记该方法，Summer也会自动完成依赖注入的工作

3. 通过字段注入：
```java
@Component(name = "service")
public class Service {
    @Autowired
    Dao dao;

    @Autowired
    FuckDao fuckDao;

    @Override
    public String toString() {
        return "Service{" +
                "dao=" + dao +
                ", fuckDao=" + fuckDao +
                ", b=" + b +
                ", isError=" + isError +
                '}';
    }
}
```
在类字段上标记@Autowired，Summer也会自动完成依赖注入工作

##### 解决注入冲突
如果定义了一个接口，有多个实现类，则使用该接口注入时就会发生多个bean注入的冲突，引发容器报错退出
要解决这个冲突，使用@Resource(name="xx")指定某一个bean名称为xx的bean进行装配，下面给一个示例

接口：
```java
public interface BaseDao {
}
```
实现类1：
```java
@Component
public class Dao implements BaseDao{

    private String haha = "123";

    public Dao() {

    }

    @Override
    public String toString() {
        return "Dao{" +
                "haha='" + haha + '\'' +
                '}';
    }
}
```
实现类2：
```java
@Component(name = "daodao")
public class DaoDao implements BaseDao {

    Integer Hi = 123;

    @Override
    public String toString() {
        return "DaoDao{" +
                "Hi=" + Hi +
                '}';
    }
}
```
要在某个类中注入BaseDao的实例：
```java
public class InnerBean {

    Float shit = 1234.5f;

    BaseDao baseDao;

    @Override
    public String toString() {
        return "InnerBean{" +
                "shit=" + shit +
                ", baseDao=" + baseDao +
                '}';
    }

    public InnerBean(@Resource(name = "daodao") BaseDao baseDao) {
        this.baseDao = baseDao;
    }
}
```
由于BaseDao具有多个实现类，因此必须显示指定实现类的beanName实现装配，否则容器报错退出
#### 实现常量注入
在字段(Primitive Types)上使用@Value即可实现常量注入，注意暂时不支持从配置文件读取数据