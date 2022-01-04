package com.fernandoboluda.categories.and.events.service;

import com.fernandoboluda.categories.and.events.repository.SportsStorage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class InitialLoaderTest {


    private SportsStorage sportsStorage ;

    private InitialLoader loader;

    @BeforeEach
    void init(){
        sportsStorage = new SportsStorage(new HashMap<>());
        loader = new InitialLoader(sportsStorage);
    }

    @Test
    void shouldPutDataIntoStorage(){
        loader.load();

        assertEquals(9, sportsStorage.getSize());
    }

    @Test
    void shouldBeIdempotent(){
        loader.load();
        loader.load();

        assertEquals(9, sportsStorage.getSize());
    }

}