package com.example.virtualguide.model;

public class KnowledgeItem {

    private final int id;
    private final String title;
    private final String shortDescription;
    private final String categoryName;
    private final KnowledgeCategory category;
    private final String fullDescription;

    public KnowledgeItem(int id,
                         String title,
                         String shortDescription,
                         String categoryName,
                         KnowledgeCategory category,
                         String fullDescription) {

        this.id = id;
        this.title = title;
        this.shortDescription = shortDescription;
        this.categoryName = categoryName;
        this.category = category;
        this.fullDescription = fullDescription;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public KnowledgeCategory getCategory() {
        return category;
    }

    public String getFullDescription() {
        return fullDescription;
    }
}
