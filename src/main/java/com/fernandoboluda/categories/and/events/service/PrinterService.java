package com.fernandoboluda.categories.and.events.service;

import com.fernandoboluda.categories.and.events.model.Category;
import com.fernandoboluda.categories.and.events.model.Event;
import com.fernandoboluda.categories.and.events.repository.SportsStorage;

import java.util.List;

public class PrinterService {

    private static final String COMMA_SEPARATOR = ",";

    public void printCategoriesAndEvents(SportsStorage sportsStorage) {
        List<Category> rootCategories = sportsStorage.getRootCategories();
        for (Category category : rootCategories) {
            System.out.println(category.toString());
        }
    }

    public void printEventsIds(String events, SportsStorage sportsStorage) {
        StringBuilder result = new StringBuilder();
        String[] eventsArray = events.split(",");
        for (String eventName : eventsArray) {
            Event event = sportsStorage.getEventByName(eventName);
            result.append(event.getId()).append(",");
        }
        result.setLength(result.length() - 1);
        System.out.println(result);
    }

    public void printCategoryId(String categoryName, SportsStorage sportsStorage) {
        StringBuilder result = new StringBuilder();
        Category category = sportsStorage.getCategoryByName(categoryName);
        result.append(category.getId());
        System.out.println(result);
    }

    public void printEventsBreadcrumb(String ids, SportsStorage sportsStorage) {
        StringBuilder result = new StringBuilder();
        String[] idsArray = ids.split(COMMA_SEPARATOR);
        for (String id : idsArray) {
            sportsStorage.get(id).getBreadCrumb(result);
        }
        System.out.println(result);
    }
}
