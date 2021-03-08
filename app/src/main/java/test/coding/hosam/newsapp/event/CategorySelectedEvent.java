package test.coding.hosam.newsapp.event;

import test.coding.hosam.newsapp.model.CategoryModel;

public class CategorySelectedEvent {

    private CategoryModel model;

    public CategorySelectedEvent(CategoryModel model) {
        this.model = model;
    }

    public CategoryModel getModel() {
        return model;
    }

    public void setModel(CategoryModel model) {
        this.model = model;
    }
}
