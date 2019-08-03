package org.cent.ApiDemo.model.pojo;

import java.util.ArrayList;
import java.util.List;

/**
 * 接口
 * @author Vincent
 * @version 1.0 2019/8/3
 */
public class Item {

    // 内部接口标识
    private String id;
    // 外部接口名称
    private String name;
    // 接口字段映射
    private List<ItemField> fields;

    public Item(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<ItemField> getFields() {
        return fields;
    }

    public int getSize() {
        if (fields == null) {
            return 0;
        } else {
            return fields.size();
        }
    }

    public void addField(ItemField field) {
        if (fields == null) {
            fields = new ArrayList<>();
        }
        fields.add(field);
    }

    @Override
    public String toString() {
        return "Item{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", fields=" + fields +
                '}';
    }
}
