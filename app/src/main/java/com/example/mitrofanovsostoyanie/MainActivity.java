package com.example.mitrofanovsostoyanie;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    User user = new User("Undefined", 0);
    final static String userVariableKey = "USER_VARIABLE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState != null) {
            user = (User) savedInstanceState.getSerializable(userVariableKey);
            TextView dataView = findViewById(R.id.dataView);
            dataView.setText("Name: " + user.getName() + " Age: " + user.getAge());
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putSerializable(userVariableKey, user);
        super.onSaveInstanceState(outState);
    }

    public void saveData(View view) {
        EditText nameBox = findViewById(R.id.nameBox);
        String name = nameBox.getText().toString();

        int age = 0;
        try {
            EditText yearBox = findViewById(R.id.yearBox);
            age = Integer.parseInt(yearBox.getText().toString());
        } catch (NumberFormatException ex) {
        }

        user = new User(name, age);

        TextView dataView = findViewById(R.id.dataView);
        dataView.setText("Name: " + user.getName() + " Age: " + user.getAge());
    }

    public void getData(View view) {
        TextView dataView = findViewById(R.id.dataView);
        dataView.setText("Name: " + user.getName() + " Age: " + user.getAge());
    }
}