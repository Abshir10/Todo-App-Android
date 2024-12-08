package com.example.todolistgu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button addbtn;
    ListView listview;
    EditText input;
    String text;
    ArrayList<String> items;
    ArrayAdapter<String> itemsAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addbtn= findViewById(R.id.addbtn);
        listview = findViewById(R.id.listview);
        input= findViewById(R.id.input);

       addbtn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               additem(view);
           }
       });

        items = new ArrayList<>();
        itemsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, items);
        listview.setAdapter(itemsAdapter);

        deleteItem();

    }

    public void additem(View view){
      text = input.getText().toString();

        if(!text.equals("")){
            itemsAdapter.add(text);
        }
        else {
            Toast.makeText(this, "please Enter Text", Toast.LENGTH_SHORT).show();
        }
    }

    public void deleteItem() {
        listview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long id) {
                // Remove the item from the list
                items.remove(position);

                // Notify the adapter that the data set has changed
                itemsAdapter.notifyDataSetChanged(); // Ensure you have a reference to the adapter

                return true; // Indicate that the event was handled
            }
        });
    }



}