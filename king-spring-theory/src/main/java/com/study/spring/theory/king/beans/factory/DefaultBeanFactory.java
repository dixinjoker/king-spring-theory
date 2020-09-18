package com.study.spring.theory.king.beans.factory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DefaultBeanFactory implements  BeanFactory,BeanDefinitionRegistry {

    //Bean定义的map,以beanName为key
    private final Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>(256);

    //保存单例实例，因为不知道是什么类，所以v为Object
    private final Map<String, Object> singletonObjects = new ConcurrentHashMap<>(256);
    @Override
    public Object getBean(String beanName) throws Throwable {
        //1.如何创建这个名字的 bean实例
        //2.需要知道用户的Bean定义信息----》首先提供一个功能让用户可以给出他的bean定义信息

        //3.单例 第一次获取，创建实例，后续获取前面创建的实例===》如何持有当前的单例实例===》使用map
        //判断是否是单例的bean
        BeanDefinition bd = this.beanDefinitionMap.get(beanName);
        if (null == bd){
            throw new IllegalArgumentException(beanName+"bean 不存在！");
        }

        Object bean = null;
        if (bd.getScop() == BeanDefinition.Scop.singleton){
            bean = this.singletonObjects.get(beanName);
            if (null == bean){//第一次检查
                //需要创建bean单例
                synchronized (this.singletonObjects) { //双重检查+锁机制
                    bean = this.singletonObjects.get(beanName);//查看此刻有无
                    if (null == bean) { //第二次检查
                        bean = createBeanInstance(beanName);
                        this.singletonObjects.put(beanName, bean);
                    }
                }
            }
        }
        else {//非单例
            bean = createBeanInstance(beanName);
        }
        return bean;
    }

    @Override
    public void registerBeanDefinition(String beanName, BeanDefinition beanDefinition) {
        //注册进来进行保存====》使用map存=====》在getBean方法中使用
        this.beanDefinitionMap.put(beanName, beanDefinition);
    }

    private Object createBeanInstance(String beanName) throws IllegalAccessException, InstantiationException {
        //使用最简单方法(调用构造方法)来创建实例
        return beanDefinitionMap.get(beanName).getBeanClass().newInstance();
    }

    //提前实例化单例bean
    @Override
    public void preInstantiateSingletons() throws Throwable {
        synchronized (beanDefinitionMap){
            for (Map.Entry<String,BeanDefinition>entry:this.beanDefinitionMap.entrySet()){
                String beanName = entry.getKey();
                BeanDefinition bd = entry.getValue();
                if(bd.getScop() == BeanDefinition.Scop.singleton){
                    this.getBean(beanName);
                }
            }
        }

    }



}
