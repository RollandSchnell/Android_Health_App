package com.health.health_app;



import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FoodManager extends AppCompatActivity {



    ListView listView;
    EditText editText;
    String searchedFood;
    List<Food> foodList = new ArrayList<>();
    CustomListAdapter listAdapter;
    Intent intent;
    SharedPreferences sharedPreferences;
    public static final String MyPREFERENCES = "MyPrefs" ;
    Set<String> myStrings;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_food_layout);

        listView = (ListView) findViewById(R.id.searchList);
        editText = (EditText) findViewById(R.id.searchInput);


        foodList.add(new Food("banana", 5, 10, 10 ,150));
        foodList.add(new Food("apple", 1, 8 , 15, 88));





        intent = new Intent(this, MainActivity.class);




        TextWatcher fieldValidatorTextWatcher = new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                searchedFood = editText.getText().toString();
                search(searchedFood);
                //Toast.makeText(getApplicationContext(), searchedFood, Toast.LENGTH_SHORT).show();
            }


        };
        editText.addTextChangedListener(fieldValidatorTextWatcher);
    }

    public void search(final String searchedFood) {

        int size = foodList.size();

        String[] values = new String[size];
        String[] imgid = new String[size];

        for(int i = 0; i < size; i++) {

            if(foodList.get(i).getFoodName().contains(searchedFood)) {


                values[i] = foodList.get(i).getFoodName();
                imgid[i] = foodList.get(i).getFoodName();
            }


        }



        listAdapter = new CustomListAdapter(this , values , imgid);

        listView.setAdapter(listAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {


                String  itemValue = (String) listView.getItemAtPosition(position);

                    sharedPreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
                    myStrings = sharedPreferences.getStringSet("list", new HashSet<String>());
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("foodName", itemValue);
                    intent.putExtra("foodName", itemValue);
                    myStrings.add(itemValue);
                    editor.apply();

                    Toast.makeText(getApplicationContext(), "Added", Toast.LENGTH_SHORT).show();



            }

        });

    }


    public class CustomListAdapter extends ArrayAdapter<String> {

        private final Activity context;
        private final String[] itemname;
        private final String[] imgid;

        public CustomListAdapter(Activity context, String[] itemname, String[] imgid) {
            super(context, R.layout.list_item_view, itemname);

            this.context = context;
            this.itemname = itemname;
            this.imgid = imgid;
        }

        public View getView(int position, View view, ViewGroup parent) {
            LayoutInflater inflater = context.getLayoutInflater();
            View rowView = inflater.inflate(R.layout.list_item_view, null,true);

            TextView txtTitle = (TextView) rowView.findViewById(R.id.item);
            ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
            TextView extratxt = (TextView) rowView.findViewById(R.id.textView1);
            extratxt.setTextColor(Color.BLACK);
            if(itemname[position] != null && imgid[position] != null) {
                txtTitle.setText(itemname[position]);
                imageView.setImageResource(getResources().getIdentifier(imgid[position], "drawable", getPackageName()));
                extratxt.setText("Click to add " + itemname[position]);
            } else {
                txtTitle.setText("");
               // imageView.setImageResource();
                extratxt.setText("");
            }
            return rowView;

        }

    }


    public void onDone(View view){

        if(sharedPreferences == null) {
            Toast.makeText(getApplicationContext(), "No items selected", Toast.LENGTH_SHORT).show();
        } else {

            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putStringSet("list", myStrings);

            editor.apply();

            startActivity(intent);
        }

    }







}
