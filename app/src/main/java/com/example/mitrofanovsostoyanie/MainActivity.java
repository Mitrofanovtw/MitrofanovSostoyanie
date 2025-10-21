package com.example.mitrofanovsostoyanie;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class MainActivity extends AppCompatActivity
{
    User user = new User("Undefined", 0, "");
    final static String userVariableKey = "USER_VARIABLE";
    final static String FILE_NAME = "user.data";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState != null)
        {
            user = (User) savedInstanceState.getSerializable(userVariableKey);
            TextView dataView = findViewById(R.id.dataView);
            dataView.setText("Name: " + user.getName() + " Age: " + user.getAge() + " Email: " + user.getEmail());
        }

        else
        {
            getData(null);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState)
    {
        outState.putSerializable(userVariableKey, user);
        super.onSaveInstanceState(outState);
    }

    public void saveData(View view)
    {
        EditText nameBox = findViewById(R.id.nameBox);
        String name = nameBox.getText().toString();

        int age = 0;

        try
        {
            EditText yearBox = findViewById(R.id.yearBox);
            age = Integer.parseInt(yearBox.getText().toString());
        }

        catch (NumberFormatException ex)
        {

        }

        EditText emailBox = findViewById(R.id.emailBox);
        String email = emailBox.getText().toString();

        user = new User(name, age, email);

        TextView dataView = findViewById(R.id.dataView);
        dataView.setText("Name: " + user.getName() + " Age: " + user.getAge() + " Email: " + user.getEmail());

        try
        {
            FileOutputStream fos = openFileOutput(FILE_NAME, MODE_PRIVATE);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(user);
            oos.close();
        }

        catch (Exception ex)
        {

        }
    }

    public void getData(View view)

    {
        try
        {
            FileInputStream fin = openFileInput(FILE_NAME);
            ObjectInputStream ois = new ObjectInputStream(fin);
            user = (User) ois.readObject();
            ois.close();
        }
        catch (Exception ex)
        {

        }

        TextView dataView = findViewById(R.id.dataView);
        dataView.setText("Name: " + user.getName() + " Age: " + user.getAge() + " Email: " + user.getEmail());
    }
}