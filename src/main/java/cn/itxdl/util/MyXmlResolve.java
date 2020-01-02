package cn.itxdl.util;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class MyXmlResolve {
    public static List<Map<String,MySqlTemplate>> xmlResolve(){
        try{
            List<Map<String,MySqlTemplate>> list = new LinkedList<>();
            FileInputStream fis = new FileInputStream("E:\\JetBrains Workspaces\\IdeaProjects\\CircleLinkedList\\Metadata_Mybatis\\src\\main\\resources\\bean.xml");
            SAXReader saxReader = new SAXReader();
            Document document = saxReader.read(fis);
            Element rootElement = document.getRootElement();
            List<Element> elements = rootElement.elements();
            for (Element e:elements){
                Map<String,MySqlTemplate> map = new HashMap<>();
                String id = e.attributeValue("id");
                String resultType = e.attributeValue("resultType");
                String sql = e.getText();
                MySqlTemplate mySqlTemplate = new MySqlTemplate(resultType,sql);
                map.put(id,mySqlTemplate);
                list.add(map);
            }
            return list;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }

}
