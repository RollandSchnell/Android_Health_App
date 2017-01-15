package com.health.health_app;



public class Food {

    private String foodName;
    private int carbs;
    private int protein;
    private int fat;
    private int calorie;

    public Food() {}

    public Food(String foodName, int carbs, int protein, int fat, int calorie) {

        this.calorie = calorie;
        this.carbs = carbs;
        this.fat = fat;
        this.protein = protein;
        this.foodName = foodName;
    }

    public int getCalorie() {
        return calorie;
    }

    public void setCalorie(int calorie) {
        this.calorie = calorie;
    }

    public int getCarbs() {
        return carbs;
    }

    public void setCarbs(int carbs) {
        this.carbs = carbs;
    }

    public int getFat() {
        return fat;
    }

    public void setFat(int fat) {
        this.fat = fat;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public int getProtein() {
        return protein;
    }

    public void setProtein(int protein) {
        this.protein = protein;
    }
}
