package com.aoinc.w2d3_c_database.model.data;

import android.os.Parcel;
import android.os.Parcelable;

public class Pizza implements Parcelable {
    private String pizzaFlavor;
    private double pizzaPrice;
    private int calories;
    private String ingredients;
    private String imageURL;

    public Pizza(String pizzaFlavor, double pizzaPrice, int calories, String ingredients, String imageURL) {
        this.pizzaFlavor = pizzaFlavor;
        this.pizzaPrice = pizzaPrice;
        this.calories = calories;
        this.ingredients = ingredients;
        this.imageURL = imageURL;
    }

    protected Pizza(Parcel in) {
        pizzaFlavor = in.readString();
        pizzaPrice = in.readDouble();
        calories = in.readInt();
        ingredients = in.readString();
        imageURL = in.readString();
    }

    public static final Creator<Pizza> CREATOR = new Creator<Pizza>() {
        @Override
        public Pizza createFromParcel(Parcel in) {
            return new Pizza(in);
        }

        @Override
        public Pizza[] newArray(int size) {
            return new Pizza[size];
        }
    };

    public String getPizzaFlavor() {
        return pizzaFlavor;
    }
    public void setPizzaFlavor(String pizzaFlavor) {
        this.pizzaFlavor = pizzaFlavor;
    }

    public double getPizzaPrice() {
        return pizzaPrice;
    }
    public void setPizzaPrice(double pizzaPrice) {
        this.pizzaPrice = pizzaPrice;
    }

    public int getCalories() {
        return calories;
    }
    public void setCalories(int calories) {
        this.calories = calories;
    }

    public String getIngredients() {
        return ingredients;
    }
    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getImageURL() {
        return imageURL;
    }
    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(pizzaFlavor);
        dest.writeDouble(pizzaPrice);
        dest.writeInt(calories);
        dest.writeString(ingredients);
        dest.writeString(imageURL);
    }
}
