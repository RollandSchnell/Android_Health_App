package com.health.health_app;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class MainActivity extends AppCompatActivity {



    String foodName;
    TextView outputCalorie;
    TextView outputCarbs;
    TextView outputProtein;
    TextView outputFat;
    List<Food> foodList = new ArrayList<>();
    public static final String MyPREFERENCES = "MyPrefs" ;
    SharedPreferences sharedPreferences;
    Button showButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        foodList.add(new Food("banana", 5, 10, 10 ,150));
        foodList.add(new Food("apple", 1, 8 , 15, 88));


        outputCalorie = (TextView) findViewById(R.id.calorieOutput);
        outputCarbs = (TextView) findViewById(R.id.carbOutput);
        outputProtein = (TextView) findViewById(R.id.proteinOutput);
        outputFat = (TextView) findViewById(R.id.fatOutput);
        showButton = (Button) findViewById(R.id.showButton);






    }


    public void onAdd(View view) {


        Intent intent = new Intent(this, FoodManager.class);
        startActivity(intent);

    }

    public void onShow(View view) {

        Intent intent = new Intent(this, FoodShow.class);
        startActivity(intent);


    }

    public void onClear(View view) {

        if(sharedPreferences.getStringSet("list", null) == null) {
            Toast.makeText(this,"No items in the list please add some",Toast.LENGTH_SHORT).show();
        }else {
            sharedPreferences.edit().remove("list").apply();
            sharedPreferences.edit().remove("foodName").apply();

            outputFat.setText(Integer.toString(0));
            outputProtein.setText(Integer.toString(0));
            outputCalorie.setText(Integer.toString(0));
            outputCarbs.setText(Integer.toString(0));
        }


    }


    public void onTotal(View view) {

        sharedPreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        Set<String> myStrings = sharedPreferences.getStringSet("list", null);
        List<Food> temp = new ArrayList<>();
        int calorie = 0;
        int carbs = 0;
        int protein = 0;
        int fat = 0;

        if(myStrings == null) {
            Toast.makeText(this,"No items in the list please add some",Toast.LENGTH_SHORT).show();
        } else {

            for (Iterator<String> it = myStrings.iterator(); it.hasNext(); ) {
                String s = it.next();

                for (int j = 0; j < foodList.size(); j++) {
                    if (foodList.get(j).getFoodName().equals(s)) {
                        temp.add(foodList.get(j));
                    }
                }

            }


            for (int i = 0; i < temp.size(); i++) {

                fat += temp.get(i).getFat();
                protein += temp.get(i).getProtein();
                carbs += temp.get(i).getCarbs();
                calorie += temp.get(i).getCalorie();

            }


            outputFat.setText(Integer.toString(fat));
            outputProtein.setText(Integer.toString(protein));
            outputCalorie.setText(Integer.toString(calorie));
            outputCarbs.setText(Integer.toString(carbs));

        }

    }







}
