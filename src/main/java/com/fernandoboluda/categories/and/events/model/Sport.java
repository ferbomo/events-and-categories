package com.fernandoboluda.categories.and.events.model;

import java.util.List;

public interface Sport {

    Long getId();

    String getName();

    void addChild(Sport sport);

    void addParent(Sport sport);

    List<Sport> getChildren();

    void print(StringBuilder buffer, String s, String s1);
}
