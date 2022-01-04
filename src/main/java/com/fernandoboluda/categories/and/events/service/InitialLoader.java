package com.fernandoboluda.categories.and.events.service;

import com.fernandoboluda.categories.and.events.model.Category;
import com.fernandoboluda.categories.and.events.model.Event;
import com.fernandoboluda.categories.and.events.repository.SportsStorage;


public class InitialLoader {

    private SportsStorage sportsStorage;

    public InitialLoader(SportsStorage sportsStorage) {
        this.sportsStorage = sportsStorage;
    }

    public void load() {
        if(sportsStorage.getSize() > 0){
            System.out.println("You already loaded data!!");
            return;
        }
        Category sports = new Category(1L, "Deportes");
        Category football = new Category(2L, "Fútbol");
        sports.addChild(football);
        Category bbvaLeague = new Category(3L, "Liga BBVA");
        football.addParent(sports);
        football.addChild(bbvaLeague);
        Event realMadridBarcelona = new Event(4L, "Real Madrid - Barcelona");
        Event realMadridVillarreal = new Event(5L, "Real Madrid - Villarreal");
        bbvaLeague.addParent(football);
        bbvaLeague.addChild(realMadridBarcelona);
        bbvaLeague.addChild(realMadridVillarreal);
        realMadridBarcelona.addParent(bbvaLeague);
        realMadridVillarreal.addParent(bbvaLeague);

        sportsStorage.saveSport(football);
        sportsStorage.saveSport(bbvaLeague);
        sportsStorage.saveSport(realMadridBarcelona);
        sportsStorage.saveSport(realMadridVillarreal);

        Category basket = new Category(6L, "Baloncesto");
        sports.addChild(basket);
        Category euroLeague = new Category(7L, "Euroliga");
        basket.addParent(sports);
        basket.addChild(euroLeague);
        Event finalFour = new Event(8L, "Final Four");
        euroLeague.addParent(basket);
        euroLeague.addChild(finalFour);
        finalFour.addParent(euroLeague);

        sportsStorage.saveSport(basket);
        sportsStorage.saveSport(euroLeague);
        sportsStorage.saveSport(finalFour);
        sportsStorage.saveSport(sports);

        Category deleteMe = new Category(9L, "DeleteMe");
        sportsStorage.saveSport(deleteMe);

        System.out.println("Data loaded successfully!");
    }
}
