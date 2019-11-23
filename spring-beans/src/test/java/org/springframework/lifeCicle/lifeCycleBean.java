package org.springframework.lifeCicle;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * Spring 容器根据实例化策略对 Bean 进行实例化。
 *
 * 实例化完成后，如果该 bean 设置了一些属性的话，则利用 set 方法设置一些属性。
 * 如果该 Bean 实现了 BeanNameAware 接口，则调用 setBeanName() 方法。
 * 如果该 bean 实现了 BeanClassLoaderAware 接口，则调用 setBeanClassLoader() 方法。
 * 如果该 bean 实现了 BeanFactoryAware接口，则调用 setBeanFactory() 方法。
 * 如果该容器注册了 BeanPostProcessor，则会调用postProcessBeforeInitialization() 方法完成 bean 前置处理
 * 如果该 bean 实现了 InitializingBean 接口，则调用 。afterPropertiesSet() 方法。
 * 如果该 bean 配置了 init-method 方法，则调用 init-method 指定的方法。
 * 初始化完成后，如果该容器注册了 BeanPostProcessor 则会调用 postProcessAfterInitialization() 方法完成 bean 的后置处理。
 * 对象完成初始化，开始方法调用。
 * 在容器进行关闭之前，如果该 bean 实现了 DisposableBean 接口，则调用 destroy() 方法。
 * 在容器进行关闭之前，如果该 bean 配置了 destroy-mehod，则调用其指定的方法。
 * 到这里一个 bean 也就完成了它的一生。
 */
public class lifeCycleBean implements BeanNameAware, BeanFactoryAware, BeanClassLoaderAware, BeanPostProcessor,
		InitializingBean, DisposableBean {

    private String test;

    public String getTest() {
        return test;
    }

    public void setTest(String test) {
        System.out.println("属性注入....");
        this.test = test;
    }

    public lifeCycleBean(){
        System.out.println("构造函数调用...");
    }

    public void display(){
        System.out.println("方法调用...");
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("BeanFactoryAware 被调用...");
    }

    @Override
    public void setBeanName(String name) {
        System.out.println("BeanNameAware 被调用...");
    }

    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {
        System.out.println("BeanClassLoaderAware 被调用...");
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("BeanPostProcessor postProcessBeforeInitialization 被调用...");
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("BeanPostProcessor postProcessAfterInitialization 被调用...");
        return bean;
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("DisposableBean destroy 被调动...");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("InitializingBean afterPropertiesSet 被调动...");
    }

    public void initMethod(){
        System.out.println("init-method 被调用...");
    }

    public void destroyMethdo(){
        System.out.println("destroy-method 被调用...");
    }

}