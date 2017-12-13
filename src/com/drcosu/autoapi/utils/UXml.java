package com.drcosu.autoapi.utils;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.dom4j.tree.DefaultAttribute;

import javax.xml.stream.events.Attribute;
import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by DELL on 2017/12/13.
 */
public class UXml {

    public static Map<String,Map<String,String>> xml = null;

    public static void readXml() throws DocumentException {
        System.out.println("loding xml");
        xml = new HashMap<>();
        SAXReader reader = new SAXReader();
        //读取文件 转换成Document
        Document document = reader.read(new File("config/recipe.xml.ftl"));
        //获取根节点元素对象
        Element root = document.getRootElement();
        //遍历
        listNodes(root);

    }
    //遍历当前节点下的所有节点
    public static void listNodes(Element node){
        //首先获取当前节点的所有属性节点
        List<DefaultAttribute> list = node.attributes();
        //遍历属性节点
        for(DefaultAttribute attribute : list){
            if(!xml.containsKey(node.getName())){
                xml.put(node.getName(),new HashMap<>());
            }
            xml.get(node.getName()).put(node.attribute("from").getValue(),node.attribute("to").getValue());
        }
        //同时迭代当前节点下面的所有子节点
        //使用递归
        Iterator<Element> iterator = node.elementIterator();
        while(iterator.hasNext()){
            Element e = iterator.next();
            listNodes(e);
        }
    }
}
