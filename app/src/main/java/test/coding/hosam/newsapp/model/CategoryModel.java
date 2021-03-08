package test.coding.hosam.newsapp.model;

public class CategoryModel {

    private String name, nameCategory;
    private boolean isSelected = false;

    public CategoryModel(String name, String nameCategory) {
        this.name = name;
        this.nameCategory = nameCategory;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }


    public String getNameCategory() {
        return nameCategory;
    }

    public void setNameCategory(String nameCategory) {
        this.nameCategory = nameCategory;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
