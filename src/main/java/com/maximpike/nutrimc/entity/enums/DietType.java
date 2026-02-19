package com.maximpike.nutrimc.entity.enums;

public enum DietType {

    OMNIVORE("Omnivore", "Eats all food types"),
    VEGETARIAN("Vegetarian","No meat or fish"),
    VEGAN("Vegan","No animal products"),
    PESCATARIAN("Pescatarian", "Vegetable and fish"),
    KETO("Ketogenic","High fat, low carb"),
    PALEO("Paleo","Whole foods, no grains"),
    MEDITERRANEAN("Mediterranean","Heart-healthy, plant-based");

    private final String displayName;
    private final String description;

    DietType(String displayName, String description) {
        this.displayName = displayName;
        this.description = description;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getDescription() {
        return description;
    }
}
