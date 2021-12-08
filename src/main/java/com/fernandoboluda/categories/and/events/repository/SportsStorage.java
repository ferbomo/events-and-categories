package com.fernandoboluda.categories.and.events.repository;

import com.fernandoboluda.categories.and.events.model.Category;
import com.fernandoboluda.categories.and.events.model.Event;
import com.fernandoboluda.categories.and.events.model.Sport;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SportsStorage {

    Map<Long, Sport> sportsRepository;

    public SportsStorage(Map<Long, Sport> sportsRepository) {
        this.sportsRepository = sportsRepository;
    }

    public void saveSport(Sport sport) {
        sportsRepository.put(sport.getId(), sport);
    }

    public Sport getSport(Long id) {
        return sportsRepository.get(id);
    }

    public boolean removeSport(Long id) {
        return sportsRepository.remove(id) != null;
    }

    public int getSize() {
        return sportsRepository.size();
    }

    public List<Category> getRootCategories() {
        List<Category> rootCategories = new ArrayList<>();
        for (Sport sport : sportsRepository.values()) {
            if (sport instanceof Category) {
                Category category = (Category) sport;
                if (category.getFatherCategory() == null) {
                    rootCategories.add(category);
                }
            }
        }
        return rootCategories;
    }

    public Event getEventByName(String eventName) {
        for (Sport sport : sportsRepository.values()) {
            if (sport instanceof Event) {
                Event event = (Event) sport;
                if (event.getName().equals(eventName)) {
                    return event;
                }
            }
        }
        return null;
    }

    public Event get(String id) {
        return (Event) sportsRepository.get(Long.parseLong(id));
    }

    public Category getCategoryByName(String categoryName) {
        for (Sport sport : sportsRepository.values()) {
            if (sport instanceof Category) {
                Category category = (Category) sport;
                if (category.getName().equals(categoryName)) {
                    return category;
                }
            }
        }
        return null;
    }
}
