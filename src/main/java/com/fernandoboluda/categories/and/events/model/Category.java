package com.fernandoboluda.categories.and.events.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Category implements Sport {

    private final Long id;
    private String name;
    private Sport fatherCategory;
    private List<Sport> children = new ArrayList<>();

    public Category(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void addChild(Sport child) {
        this.children.add(child);
    }

    @Override
    public void addParent(Sport father) {
        this.fatherCategory = father;
    }

    @Override
    public List<Sport> getChildren() {
        return children;
    }

    public Sport getFatherCategory() {
        return fatherCategory;
    }

    @Override
    public String toString() {
        StringBuilder buffer = new StringBuilder();
        print(buffer, "", "");
        return buffer.toString();
    }

    @Override
    public void print(StringBuilder buffer, String prefix, String childrenPrefix) {
        buffer.append(prefix);
        buffer.append("[").append(id).append("] ").append(name);
        buffer.append('\n');
        for (Iterator<Sport> it = children.iterator(); it.hasNext(); ) {
            Sport next = it.next();
            if (it.hasNext()) {
                next.print(buffer, childrenPrefix + "├── ", childrenPrefix + "│   ");
            } else {
                next.print(buffer, childrenPrefix + "└── ", childrenPrefix + "    ");
            }
        }
    }
}
