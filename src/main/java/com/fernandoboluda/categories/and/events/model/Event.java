package com.fernandoboluda.categories.and.events.model;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;

public class Event implements Sport {

    private Long id;
    private String name;
    private Sport parentCategory;

    public Event(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public void print(StringBuilder buffer, String prefix, String childrenPrefix) {
        buffer.append(prefix);
        buffer.append("[").append(id).append("] ").append(name);
        buffer.append('\n');
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void addChild(Sport sport) {
        throw new RuntimeException("Events don't have children");
    }

    @Override
    public void addParent(Sport sport) {
        this.parentCategory = sport;
    }

    @Override
    public List<Sport> getChildren() {
        return new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public Sport getParentCategory() {
        return parentCategory;
    }

    public StringBuilder getBreadCrumb(StringBuilder result){
        result.append("[").append(id).append("] ").append(this.getName()).append(": ");
        Category parentCategory = (Category) this.getParentCategory();
        Deque<String> dq = new ArrayDeque<>();
        dq.add(parentCategory.getName() + " -> ");
        do {
            parentCategory = (Category) parentCategory.getFatherCategory();
            if (parentCategory != null) {
                dq.add(parentCategory.getName() + " -> ");
            }
        } while (parentCategory != null);
        for (Iterator<String> itr = dq.descendingIterator(); itr.hasNext(); ) {
            result.append(itr.next());
        }
        result.setLength(result.length() - 3);
        result.append("\n");
        return result;
    }

}
