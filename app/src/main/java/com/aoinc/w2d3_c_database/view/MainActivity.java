package com.aoinc.w2d3_c_database.view;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.aoinc.w2d3_c_database.R;
import com.aoinc.w2d3_c_database.model.data.Pizza;
import com.aoinc.w2d3_c_database.model.db.PizzaDBHelper;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.pizza_flavor_editText)
    EditText pizzaFlavorEditText;

    @BindView(R.id.pizza_ingredients_editText)
    EditText pizzaIngredientsEditText;

    @BindView(R.id.pizza_calories_editText)
    EditText pizzaCaloriesEditText;

    @BindView(R.id.pizza_price_editText)
    EditText pizzaPriceEditText;

    @BindView(R.id.pizza_image_url_editText)
    EditText pizzaImageUrlEditText;

    private PizzaDBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        dbHelper = new PizzaDBHelper(this);
    }

    @OnClick(R.id.make_order_button)
    public void onMakeOrder(View view) {
        if(checkInput()) {
            String flavor = pizzaFlavorEditText.getText().toString().trim();
            String ingredients = pizzaIngredientsEditText.getText().toString().trim();
            int calories = Integer.parseInt(pizzaCaloriesEditText.getText().toString().trim());
            double price = Double.parseDouble(pizzaPriceEditText.getText().toString().trim());
            String url = pizzaImageUrlEditText.getText().toString().trim();

            Pizza pizza = new Pizza(flavor, price, calories, ingredients, url);
            dbHelper.insertPizzaOrder(pizza);
            Toast.makeText(this, "Pizza Inserted", Toast.LENGTH_SHORT).show();

            ClearEditTexts();
            readOrders();
        }
    }

    private void readOrders() {
        Cursor orders = dbHelper.getAllPizzaOrders();
        orders.move(-1);

        while(orders.moveToNext()) {
            String pizzaName = orders.getString(orders.getColumnIndex(PizzaDBHelper.COLUMN_PIZZA_FLAVOR));
            String pizzaIngredients = orders.getString(orders.getColumnIndex(PizzaDBHelper.COLUMN_PIZZA_INGREDIENTS));
            int pizzaCalories = Integer.parseInt(orders.getString(orders.getColumnIndex(PizzaDBHelper.COLUMN_PIZZA_CALORIES)));
            double pizzaPrice = Double.parseDouble(orders.getString(orders.getColumnIndex(PizzaDBHelper.COLUMN_PIZZA_PRICE)));
            String pizzaImageURL = orders.getString(orders.getColumnIndex(PizzaDBHelper.COLUMN_PIZZA_IMAGE_URL));

            Log.d("tag_pizza", pizzaName + ", " + pizzaIngredients + ", " + pizzaCalories + ", " + pizzaPrice + ", " + pizzaImageURL);
        }
    }

    private void ClearEditTexts() {
        pizzaFlavorEditText.setText("");
        pizzaIngredientsEditText.setText("");
        pizzaCaloriesEditText.setText("");
        pizzaPriceEditText.setText("");
        pizzaImageUrlEditText.setText("");
    }

    private boolean checkInput() {
        if (pizzaFlavorEditText.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "Pizza flavor cannot be empty.", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (pizzaIngredientsEditText.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "Pizza ingredients cannot be empty.", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (pizzaCaloriesEditText.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "Pizza calories cannot be empty.", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (pizzaPriceEditText.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "Pizza price cannot be empty.", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (pizzaImageUrlEditText.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "Pizza image URL cannot be empty.", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }
}