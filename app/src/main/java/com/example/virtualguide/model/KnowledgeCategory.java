package com.example.virtualguide.model;

public enum KnowledgeCategory {
    CORE("Основные понятия"),
    TECH("Технологии"),
    PARTICIPANTS("Участники"),
    PROCESSES("Процессы");

    private final String displayName;

    KnowledgeCategory(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
