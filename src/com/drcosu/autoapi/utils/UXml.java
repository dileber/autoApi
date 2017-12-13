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
        //��ȡ�ļ� ת����Document
        Document document = reader.read(new File("config/recipe.xml.ftl"));
        //��ȡ���ڵ�Ԫ�ض���
        Element root = document.getRootElement();
        //����
        listNodes(root);

    }
    //������ǰ�ڵ��µ����нڵ�
    public static void listNodes(Element node){
        //���Ȼ�ȡ��ǰ�ڵ���������Խڵ�
        List<DefaultAttribute> list = node.attributes();
        //�������Խڵ�
        for(DefaultAttribute attribute : list){
            if(!xml.containsKey(node.getName())){
                xml.put(node.getName(),new HashMap<>());
            }
            xml.get(node.getName()).put(node.attribute("from").getValue(),node.attribute("to").getValue());
        }
        //ͬʱ������ǰ�ڵ�����������ӽڵ�
        //ʹ�õݹ�
        Iterator<Element> iterator = node.elementIterator();
        while(iterator.hasNext()){
            Element e = iterator.next();
            listNodes(e);
        }
    }
}
