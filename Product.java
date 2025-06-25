package kbeauty;

public class Product {
    private String name;
    private String category;
    private String mood;
    private int price;  // 원화 단위

    public Product(String name, String category, String mood, int price) {
        this.name = name;
        this.category = category;
        this.mood = mood;
        this.price = price;
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

    public int getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return name + " (" + category + ", 감정: " + mood + ") - " + price + "원";
    }
}
