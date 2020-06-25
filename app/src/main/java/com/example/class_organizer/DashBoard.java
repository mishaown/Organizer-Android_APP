package com.example.class_organizer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class DashBoard extends AppCompatActivity {
    RecyclerView recyclerView;

    Class_Database db;
    ArrayList<String> Class_name, Class_des, Class_ID, Class_Date;
    CustomAdaptor customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);

        recyclerView = findViewById(R.id.recycleView);
        FloatingActionButton button = findViewById(R.id.floatingActionButton);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DashBoard.this, AddClass.class));
            }
        });
        db = new Class_Database(DashBoard.this);
        Class_ID = new ArrayList<>();
        Class_name = new ArrayList<>();
        Class_des = new ArrayList<>();
        Class_Date = new ArrayList<>();

        storeDatatoArray();

        customAdapter = new CustomAdaptor(DashBoard.this, Class_ID, Class_name, Class_des, Class_Date);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(DashBoard.this));
    }
    void storeDatatoArray() {
        Cursor cursor = db.readData();
        if (cursor.getCount() == 0) {
            Toast.makeText(this, "No Book Found!", Toast.LENGTH_SHORT).show();

        } else {
            while (cursor.moveToNext()) {
                Class_ID.add(cursor.getString(0));
                Class_name.add(cursor.getString(1));
                Class_des.add(cursor.getString(2));
                Class_Date.add(cursor.getString(3));
            }
        }
    }
}