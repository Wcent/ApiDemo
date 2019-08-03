package org.cent.ApiDemo.util;

import org.cent.ApiDemo.model.pojo.Item;
import org.cent.ApiDemo.model.pojo.ItemField;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.net.URL;
import java.util.Iterator;

/**
 * xml parser
 * @author Vincent
 * @version 1.0 2019/8/3
 */
public class ItemParserUtil {

    static public Item parseXml(URL url) throws DocumentException {
        SAXReader saxReader = new SAXReader();
        Document document = saxReader.read(url);
        Element root = document.getRootElement();

        Element itemElement = root.element("item");
        // 未指定接口名称时默认使用内部标识命名
        String itemId = itemElement.attributeValue("id");
        String itemName = itemElement.attributeValue("name", itemId);
        Item item = new Item(itemId, itemName);

        for (Iterator<Element> iterator=itemElement.elementIterator(); iterator.hasNext();) {
            Element fieldElement = iterator.next();
            // 未指定字段名称时默认使用内部字段标识命名，其他未指定属性取默认值
            String id = fieldElement.attributeValue("id");
            String name = fieldElement.attributeValue("name", id);
            String type = fieldElement.attributeValue("type", "String");
            int length = Integer.parseInt(fieldElement.attributeValue("length", "0"));
            boolean must = Boolean.parseBoolean(fieldElement.attributeValue("must", "false"));
            ItemField itemField = new ItemField(id, name, type, length, must);
            item.addField(itemField);
        }

        return item;
    }
}
