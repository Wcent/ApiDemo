package org.cent.ApiDemo.model.pojo;

import java.util.HashMap;

/**
 * @author Vincent
 * @version 1.0 2019/8/3
 */
public class ItemTemplate {

    private HashMap<String, Item> itemHashMap;

    public ItemTemplate(HashMap<String, Item> itemHashMap) {
        this.itemHashMap = itemHashMap;
    }

    public Item getItem(String name) {
        return itemHashMap.get(name);
    }

    public void eval(String name) {

    }

    @Override
    public String toString() {
        return "ItemTemplate{" +
                "itemHashMap=" + itemHashMap +
                '}';
    }
}
