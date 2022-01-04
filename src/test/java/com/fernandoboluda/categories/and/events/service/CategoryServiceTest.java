package com.fernandoboluda.categories.and.events.service;

import com.fernandoboluda.categories.and.events.model.Sport;
import com.fernandoboluda.categories.and.events.repository.SportsStorage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CategoryServiceTest {

    private SportsStorage sportsStorage;

    private CategoryService service;

    @BeforeEach
    void init() {
        sportsStorage = new SportsStorage(new HashMap<>());
        service = new CategoryService(sportsStorage);
    }

    @Test
    void shouldCreateACategory() {

        Sport category = service.create(1L, "Sports");

        assertNotNull(category);
        assertEquals(1L, category.getId());
        assertEquals("Sports", category.getName());
    }

    @Test
    void shouldRemoveACategory() {
        Sport category = service.create(1L, "Sports");
        sportsStorage.saveSport(category);

        boolean result = service.removeCategory(1L);

        assertTrue(result);
    }

    @Test
    void shouldNotRemoveACategoryWithChildren() {
        Sport category = service.create(1L, "Sports");
        Sport childCategory = service.create(2L, "Basket");
        category.addChild(childCategory);
        sportsStorage.saveSport(category);

        boolean result = service.removeCategory(1L);

        assertFalse(result);
    }

    @Test
    void shouldUpdateACategory() {
        Sport category = service.create(1L, "Sports");
        sportsStorage.saveSport(category);

        Sport result = service.updateCategoryName(1L, "Basket");

        assertEquals("Basket", result.getName());
    }
}