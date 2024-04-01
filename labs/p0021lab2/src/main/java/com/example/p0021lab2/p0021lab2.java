package com.example.p0021lab2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.ListMenuItemView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.BaseExpandableListAdapter;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class p0021lab2 extends AppCompatActivity {


//    private ListView listView = findViewById(R.id.listView);
    private CustomListAdapter adapter;

    private String[] brands = {"Acura", "Chevrolet", "Genesis", "Honda", "Infiniti"};
    private String[] models = {"Silverado", "Corvette", "GV70", "Accord", "Civic", "QX50", "MDX"};
    private String[] types = {"Oil change", "Tune-up", "Engine overhaul", "Brake pad replacement", "Brake fluid flush", "Steering system repair", "Tire replacement"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_p0021lab2);

        adapter = new CustomListAdapter(this, models, brands, types);
        ListView listView = findViewById(R.id.listView);
        listView.setAdapter(adapter);
    }

    public void buttonClicked(View view) {
        EditText editText = (EditText) findViewById(R.id.userInputField);
        String enteredYear = editText.getText().toString();

        Random random = new Random();
        random.setSeed(String.format("%s%s%s", adapter.selectedBrand, adapter.selectedModel, enteredYear).hashCode());
        int randInt = random.nextInt(10000 - 100);



        String result = String.format("%s %s %s", adapter.selectedBrand, adapter.selectedModel, enteredYear);
        TextView outResult = (TextView) findViewById(R.id.resultTextView);
        TextView resultSum = (TextView) findViewById(R.id.resultSum);
        outResult.setText(String.format("%s\n%s\n$%s", adapter.selectedType, result, String.valueOf(randInt)));
//        resultSum.setText(randInt);
//        resultSum.setText("$" + random.nextInt(100 - 10000));

    }

    public class CustomListAdapter extends BaseAdapter {
        private Context context;
        private String[] modelsArray;
        private String[] brandsArray;
        private String[] typesArray;

        public String selectedBrand;
        public String selectedModel;
        public String selectedType;

        public CustomListAdapter(Context context, String[] modelsArray, String[] someStuffArray, String[] typesArray) {
            this.context = context;
            this.modelsArray = modelsArray;
            this.brandsArray = someStuffArray;
            this.typesArray = typesArray;
        }

        @Override
        public int getCount() {
            return 1;
        }

        @Override
        public Object getItem(int position) {
            return modelsArray[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                LayoutInflater inflater = LayoutInflater.from(context);
                convertView = inflater.inflate(R.layout.list_item_layout, parent, false);
            }
            TextView resultTextView = convertView.findViewById(R.id.resultTextView);

            Spinner modelSpinner = convertView.findViewById(R.id.modelSpinner);
            ArrayAdapter<String> modelsAdapter = new ArrayAdapter<>(
                    context, android.R.layout.simple_spinner_item, modelsArray);
            modelsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            modelSpinner.setAdapter(modelsAdapter);

            modelSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    selectedModel = parent.getItemAtPosition(position).toString();
                    // Do something with the selected item
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                    // Optional interface method to respond to nothing being selected
                }
            });

            Spinner brandSpinner = convertView.findViewById(R.id.brandSpinner);
            ArrayAdapter<String> brandsAdapter = new ArrayAdapter<>(
                    context, android.R.layout.simple_spinner_item, brandsArray);
            brandsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            brandSpinner.setAdapter(brandsAdapter);

            brandSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    selectedBrand = parent.getItemAtPosition(position).toString();
                    // Do something with the selected item
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                    // Optional interface method to respond to nothing being selected
                }



            });

            Spinner typeSpinner = convertView.findViewById(R.id.typeSpinner);
            ArrayAdapter<String> typesAdatpter = new ArrayAdapter<>(
                    context, android.R.layout.simple_spinner_item, typesArray);
            typesAdatpter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            typeSpinner.setAdapter(typesAdatpter);

            typeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    selectedType = parent.getItemAtPosition(position).toString();
                    // Do something with the selected item
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                    // Optional interface method to respond to nothing being selected
                }



            });



            return convertView;
        }
    }
}
