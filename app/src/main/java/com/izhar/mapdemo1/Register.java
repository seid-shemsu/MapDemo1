package com.izhar.mapdemo1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Register extends AppCompatActivity {

    EditText phone, password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        setTitle("Sign Up");

        phone = findViewById(R.id.phone);
        password = findViewById(R.id.password);
    }

    public void login(View view) {
        startActivity(new Intent(this, Login.class));
        finish();
    }

    public void register(View view) {
        if (phone.getText().toString().length() == 13 && password.getText().toString().length() > 5 ){
            startActivity(new Intent(this, Verify.class).putExtra("phone", phone.getText().toString()));
            finish();
        }
        else {
            Toast.makeText(this, "error", Toast.LENGTH_SHORT).show();
            if (password.getText().toString().length() <= 5)
                password.setError("minimum password length is 6");
            if (phone.getText().toString().length() != 10 || !phone.getText().toString().substring(0,2).startsWith("09"))
                phone.setError("invalid phone number");
        }
    }
}