package cn.kcs.common.fileutil;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.*;

/**
 * dom4j utill
 * @author kcs
 * @date 2019-03-08 10:02
 **/
public class DOM4Jutil {
    public static void main(String[] args) {
        List<Map<String, String>> list = readerXML("/Users/kcs/idea-workspace/project/base/common/src/main/resources/menu.xml");
        System.out.println(list.toString());
    }

    /**
     * @param path
     */
    public static List<Map<String, String>> readerXML(String path) {
        List<Map<String, String>> mapList = new ArrayList<>();
        // 解析books.xml文件
        // 创建SAXReader的对象reader
        SAXReader reader = new SAXReader();
        try {
            // 通过reader对象的read方法加载xml文件,获取document对象。
            Document document = reader.read(new File(path));
            // 通过document对象获取根节点
            Element bookStore = document.getRootElement();
            // 通过element对象的elementIterator方法获取迭代器
            Iterator it = bookStore.elementIterator();
            // 遍历迭代器，获取根节点中的信息
            while (it.hasNext()) {
                System.out.println("=====开始遍历=====");
                Element row = (Element) it.next();
                // 获取xml的属性名以及 属性值
                List<Attribute> bookAttrs = row.attributes();
                for (Attribute attr : bookAttrs) {
                    System.out.println("属性名：" + attr.getName() + "--属性值：" + attr.getStringValue());
                    Map<String, String> map = new HashMap<>();
                    map.put(attr.getName(), attr.getValue());
                    mapList.add(map);
                }
                Iterator itt = row.elementIterator();
                while (itt.hasNext()) {
                    Element bookChild = (Element) itt.next();
                    System.out.println("节点名：" + bookChild.getName() + "--节点值：" + bookChild.getStringValue());
                    Map<String, String> map = new HashMap<>();
                    map.put(bookChild.getName(), bookChild.getStringValue());
                    mapList.add(map);
                }
                System.out.println("=====结束遍历=====");
            }
        } catch (DocumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return mapList;
    }
}
