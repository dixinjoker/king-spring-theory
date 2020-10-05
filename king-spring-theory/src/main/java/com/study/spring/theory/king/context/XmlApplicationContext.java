package com.study.spring.theory.king.context;

import com.study.spring.theory.king.beans.factory.BeanDefinition;
import com.study.spring.theory.king.beans.factory.DefaultBeanFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class XmlApplicationContext implements ApplicationContext{
    //1.持有BeanFactory
    private DefaultBeanFactory beanFactory;

    public XmlApplicationContext(String xmlConfigFileName) {
        this.beanFactory = new DefaultBeanFactory();
        //1.因为基于XML解析的，所以完成xml解析得到BeanDefinition，注册到BeanFactory
        //2.解析
            parseXmlRegistBeanDefinition(xmlConfigFileName);
    }
    //2.解析
    private void parseXmlRegistBeanDefinition(String xmlConfigFileName) {
        //3.使用document
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try{
            DocumentBuilder builder = factory.newDocumentBuilder();
            //4.开始解析
            Document doc = builder.parse(this.getClass().getResourceAsStream(xmlConfigFileName));
            //5.得到所有的bean元素
            NodeList nodeList = doc.getElementsByTagName("bean");
            Element e = null;
            //类加载器
            ClassLoader classLoader = this.getClass().getClassLoader();
            //一个一个注册BeanDefinition
            for (int i = 0; i<nodeList.getLength(); i++){
                e = (Element)nodeList.item(i);
                String beanName = e.getAttribute("id");
                Class<?> beanClass = classLoader.loadClass(e.getAttribute("class"));
                //其他属性的处理略
                //注册beanDefinition
                this.beanFactory.registerBeanDefinition(beanName,new BeanDefinition(beanClass));
            }
        } catch (ParserConfigurationException | SAXException | IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Object getBean(String beanName) throws Throwable {
        return this.beanFactory.getBean(beanName);
    }

    @Override
    public void preInstantiateSingletons() throws Throwable {
        this.beanFactory.preInstantiateSingletons();
    }
}
