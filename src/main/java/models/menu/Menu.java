package models.menu;

import java.util.List;

public class Menu {
    private String id;
    private String disName;
    private int disNumber;
    private MenuType type;
    private MenuCategory category;
    private List<Menu> children;
    private String method;
    private String parentId;

    public List<Menu> getChildren() {
        return children;
    }

    public void setChildren(List<Menu> children) {
        this.children = children;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public MenuCategory getCategory() {
        return category;
    }

    public void setCategory(MenuCategory category) {
        this.category = category;
    }

    public MenuType getType() {
        return type;
    }

    public void setType(MenuType type) {
        this.type = type;
    }

    public int getDisNumber() {
        return disNumber;
    }

    public void setDisNumber(int disNumber) {
        this.disNumber = disNumber;
    }

    public String getDisName() {
        return disName;
    }

    public void setDisName(String disName) {
        this.disName = disName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    @Override
    public String toString() {
        return "models.menu.Menu{" +
                "disName='" + disName + '\'' +
                ", disNumber=" + disNumber +
                ", type=" + type +
                ", category=" + category +
                ", children=" + children +
                ", method=" + method +
                '}';
    }
}