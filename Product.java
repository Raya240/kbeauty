package kbeauty;

public class Product {
    private String name;
    private String category;
    private String mood;

    public Product(String name, String category, String mood) {
        this.name = name;
        this.category = category;
        this.mood = mood;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public String getMood() {
        return mood;
    }

    @Override
    public String toString() {
        return name + " (" + category + ", 감정: " + mood + ")";
    }
}
