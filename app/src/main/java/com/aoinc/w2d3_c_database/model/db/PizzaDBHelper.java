package com.aoinc.w2d3_c_database.model.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;

import androidx.annotation.Nullable;

import com.aoinc.w2d3_c_database.model.data.Pizza;

public class PizzaDBHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "pizza_orders.db";
    public static int DB_VERSION = 1;

    public static final String PIZZA_TABLE_NAME = "PIZZA_ORDERS";
    public static final String COLUMN_ORDER_ID = "order_id";
    public static final String COLUMN_PIZZA_FLAVOR = "flavor";
    public static final String COLUMN_PIZZA_INGREDIENTS = "ingredients";
    public static final String COLUMN_PIZZA_CALORIES = "calories";
    public static final String COLUMN_PIZZA_PRICE = "price";
    public static final String COLUMN_PIZZA_IMAGE_URL = "image_url";

    public PizzaDBHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + PIZZA_TABLE_NAME
                + " ( "  + COLUMN_ORDER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_PIZZA_FLAVOR + " TEXT, "
                + COLUMN_PIZZA_INGREDIENTS + " TEXT, "
                + COLUMN_PIZZA_PRICE + " TEXT, "
                + COLUMN_PIZZA_CALORIES + " INTEGER, "
                + COLUMN_PIZZA_IMAGE_URL + " TEXT)";

        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String update = "DROP TABLE IF EXISTS " + PIZZA_TABLE_NAME;
        db.execSQL(update);
        onCreate(db);
        DB_VERSION = newVersion;
    }

    public void insertPizzaOrder(Pizza pizzaOrdered) {
        ContentValues pizzaContent = new ContentValues();

        pizzaContent.put(COLUMN_PIZZA_FLAVOR, pizzaOrdered.getPizzaFlavor());
        pizzaContent.put(COLUMN_PIZZA_INGREDIENTS, pizzaOrdered.getIngredients());
        pizzaContent.put(COLUMN_PIZZA_PRICE, pizzaOrdered.getPizzaPrice());
        pizzaContent.put(COLUMN_PIZZA_CALORIES, pizzaOrdered.getCalories());
        pizzaContent.put(COLUMN_PIZZA_IMAGE_URL, pizzaOrdered.getImageURL());

        SQLiteDatabase database = getWritableDatabase();
        database.insert(PIZZA_TABLE_NAME, null, pizzaContent);
    }

    // TODO: return array instead of cursor
    public Cursor getAllPizzaOrders() {
        Cursor allPizzaOrders = getReadableDatabase().rawQuery("SELECT * FROM " + PIZZA_TABLE_NAME,null);
        return allPizzaOrders;
    }
}
