### Bean 生命周期
#### 有三种方式在 Bean 初始化后和销毁前添加一些操作。
- Bean 的方法加上@PostConstruct 和@PreDestroy 注解（必须有 component-scan 才有效）
- 在 xml 中定义 init-method 和 destory-method 方法
- Bean 实现 InitializingBean 和 DisposableBean 接口
#### 其执行顺序关系为：
- Bean 在实例化的过程中：constructor > @PostConstruct >InitializingBean > init-method
- Bean 在销毁的过程中：@PreDestroy > DisposableBean > destroy-method

#### 还可以通过 BeanPostProcessor 在 bean 初始化前后添加一些操作
- 初始化执行顺序为：constructor > BeanPostProcessor#postProcessBeforeInitialization >
@PostConstructor > InitializingBean > init-method > BeanPostProcessor#
postProcessAfterInitialization
> 调 用 用 户 自 定 义 初 始 化 方 法 之 前 和 之 后 分 别 会 调 用 BeanPostProcessor 的
postProcessBeforeInitialization 和 postProcessAfterInitialization 方法。
参见 getBean 中的 initializeBean 方法。


####示例：
```java
public class Car implements InitializingBean, DisposableBean {
    public Car() {
        System.out.println("constructor...");
    }
    public void initCar() {
        System.out.println("init-method...");
    }
    @PostConstruct
    public void postConstructMethod() {
        System.out.println("post construct ..");
    }
    @PreDestroy
    public void beforeDestroy() {
        System.out.println("before destroy...");
    }
    public void destroyCar() {
        System.out.println("destroy-method....");
    }
    @Override
    public void destroy() throws Exception {
        System.out.println("disposable bean...");
    }
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("initializing bean...");
    }
}
```

```java
public class MyBeanPostProcessor implements BeanPostProcessor{
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName)
    throws BeansException {
        System.out.println("postProcessAfterInitialization "+ bean +" ,"+
        beanName);
        return bean;
    }
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName)
    throws BeansException {
        System.out.println("postProcessBeforeInitialization "+ bean +" ,"+
        beanName);
        return bean;
    }
}
```

```java
public class Main {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext ctx = new
        ClassPathXmlApplicationContext("beans-cycle.xml");
        Car car = (Car) ctx.getBean("car");
        System.out.println(car);
        ctx.close();
    }
}
```

```xml
<context:component-scan base-package="cycle" ></context:component-scan>
<bean id="car" class="cycle.Car" init-method="initCar" destroy-method="destroyCar">
</bean>
<bean class="scope.MyBeanPostProcessor"></bean>
```

```text
输出为：
constructor... postProcessBeforeInitialization cycle.Car@2db7a79b ,car
post construct ..
微信公众号：程序员乔戈里
initializing bean... init-method... postProcessAfterInitialization cycle.Car@2db7a79b ,car
cycle.Car@2db7a79b
// 关闭了
before destroy... disposable bean... destroy-method....
```
---