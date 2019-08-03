package org.cent.ApiDemo.model.pojo;

/**
 * 接口字段
 * @author Vincent
 * @version 1.0 2019/8/3
 */
public class ItemField {

    // 内部接口字段标识
    private String id;
    // 外部接口字段名称
    private String name;
    private String type;
    private int length;
    private boolean must;

    public ItemField(String id, String name, String type, int length, boolean must) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.length = length;
        this.must = must;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public boolean isMust() {
        return must;
    }

    public void setMust(boolean must) {
        this.must = must;
    }

    @Override
    public String toString() {
        return "ItemField{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", length=" + length +
                ", must=" + must +
                '}';
    }
}
