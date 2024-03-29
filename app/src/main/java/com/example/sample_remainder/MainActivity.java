package com.example.sample_remainder;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.sample_remainder.data.ReminderDbHelper;
import com.example.sample_remainder.data.UserContract;

public class MainActivity extends AppCompatActivity {

    private EditText usernameEditText;
    private EditText passwordEditText;
    private Button loginButton;

    // Replace these with your actual login credentials
    private static final String VALID_USERNAME = "user123";
    private static final String VALID_PASSWORD = "password123";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usernameEditText = findViewById(R.id.editTextUsername);
        passwordEditText = findViewById(R.id.editTextPassword);
        loginButton = findViewById(R.id.buttonLogin);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String enteredUsername = usernameEditText.getText().toString();
                String enteredPassword = passwordEditText.getText().toString();

                if (isValidCredentials(enteredUsername, enteredPassword)) {
                    // Successful login, replace with your logic
                    showToast("Login successful");
                    Intent intent = new Intent(MainActivity.this, RemainderType.class);
                    startActivity(intent);
                    finish(); // Optional: Finish the MainActivity if it should not be accessible after login

                } else {
                    // Invalid credentials, show error message
                    showToast("Invalid username or password");
                }
            }
        });
    }

    private boolean isValidCredentials(String username, String password) {
        // Replace with your actual validation logic, e.g., API call to server
        return username.equals(VALID_USERNAME) && password.equals(VALID_PASSWORD);
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
    private void insertUserCredentials(String username, String password) {
        ReminderDbHelper dbHelper = new ReminderDbHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(UserContract.UserEntry.COLUMN_USERNAME, username);
        values.put(UserContract.UserEntry.COLUMN_PASSWORD, password);

        long newRowId = db.insert(UserContract.UserEntry.TABLE_NAME, null, values);

        if (newRowId != -1) {
            showToast("User registered successfully");
        } else {
            showToast("Error registering user");
        }

        db.close();
    }

}
