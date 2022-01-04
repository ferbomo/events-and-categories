package com.fernandoboluda.categories.and.events.service;

import com.fernandoboluda.categories.and.events.model.Category;
import com.fernandoboluda.categories.and.events.model.Sport;
import com.fernandoboluda.categories.and.events.repository.SportsStorage;


public class CategoryService {

    private SportsStorage sportsStorage;

    public CategoryService(SportsStorage sportsStorage) {
        this.sportsStorage = sportsStorage;
    }

    public Sport create(Long id, String name) {
        return new Category(id, name);
    }

    public Sport updateCategoryName(Long id, String name) {
        Category category = (Category) sportsStorage.getSport(id);
        if (name != null) {
            category.setName(name);
        }

        sportsStorage.saveSport(category);

        return category;
    }

    public boolean removeCategory(Long id) {
        Category category = (Category) sportsStorage.getSport(id);
        if (category.getChildren().isEmpty()) {
            return sportsStorage.removeSport(id);
        } else {
            System.out.println("You can't remove a category with child categories or associated events.");
            return false;
        }
    }

}
