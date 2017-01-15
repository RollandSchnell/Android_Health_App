package com.health.health_app;



import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;


public class FoodShow extends AppCompatActivity{

    ListView listView;
    CustomListAdapter listAdapter;
    String[] values;
    String[] imgid;
    SharedPreferences sharedPreferences;
    public static final String MyPREFERENCES = "MyPrefs" ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.all_foods_layout);
        int i = 0;


        sharedPreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        Set<String> myStrings = sharedPreferences.getStringSet("list", null);

        if(myStrings == null) {
            Toast.makeText(this,"No items in the list please add some",Toast.LENGTH_SHORT).show();
        } else {

            values = new String[myStrings.size()];
            imgid = new String[myStrings.size()];


            for (Iterator<String> it = myStrings.iterator(); it.hasNext(); ) {
                String s = it.next();
                values[i] = s;
                imgid[i] = s;
                i++;
            }


            listView = (ListView) findViewById(R.id.allFoodsList);

            listAdapter = new CustomListAdapter(this, values, imgid);

            listView.setAdapter(listAdapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> parent, View view,
                                        int position, long id) {

                }

            });

        }


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



}
