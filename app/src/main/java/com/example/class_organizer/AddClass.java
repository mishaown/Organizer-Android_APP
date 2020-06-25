package com.example.class_organizer;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class AddClass extends AppCompatActivity {
    EditText edit_name, edit_des;
    Button save_Button;
    Calendar calendar;
    TextView dateText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_class);

        edit_name = findViewById(R.id.edit_text_name);
        edit_des = findViewById(R.id.edit_text_des);
        save_Button = findViewById(R.id.save_btn);
        calendar = new GregorianCalendar();
        dateText = findViewById(R.id.dateText);

        save_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Save_Data()) {
                    Class_Database db = new Class_Database(AddClass.this);
                    db.addClass(edit_name.getText().toString().trim(),
                            edit_des.getText().toString().trim(),
                            new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime()));
                }
            }
        });


    }
    public boolean Save_Data() {
        String Name = edit_name.getText().toString();
        if(Name.isEmpty()) {
            edit_name.setError("Type a name.");
            edit_name.requestFocus();
            return false;
        } else {
            return true;
        }
    }

    public void chooseDate(View view) {
        final View dialogView = View.inflate(this, R.layout.date_picker, null);
        final DatePicker datePicker = dialogView.findViewById(R.id.date_picker);
        datePicker.updateDate(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));


        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(dialogView);
        builder.setTitle("Choose Date");
        builder.setNegativeButton("Cancel", null);
        builder.setPositiveButton("Set", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {


                calendar = new GregorianCalendar(datePicker.getYear(), datePicker.getMonth(), datePicker.getDayOfMonth());
                dateText.setText(new SimpleDateFormat("E, dd MMMM yyyy").format(calendar.getTime()));
            }
        });

        builder.show();
    }
}