package com.example.logreg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnRegister,btnLogin;
    EditText editTextUsername,editTextPassword;
    DbHelper adatbazis;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

    }

    private void init() {
        btnLogin=findViewById(R.id.btnLogin);
        btnRegister=findViewById(R.id.btnRegister);
        editTextUsername=findViewById(R.id.editTextUsername);
        editTextPassword=findViewById(R.id.editTextPassword);

        btnRegister.setOnClickListener(this);
        btnLogin.setOnClickListener(this);

        adatbazis= new DbHelper(MainActivity.this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnLogin:
                bejelentkezes();
                break;
            case R.id.btnRegister:
                Intent register = new Intent(this,RegisterActivity.class);
                startActivity(register);
                finish();
                break;
                
        }
    }

    private void bejelentkezes() {
        String username=editTextUsername.getText().toString();
        String password=editTextPassword.getText().toString();
        Cursor adatok =adatbazis.bejelentkezes(username,password);

        if (username.isEmpty()){
            Toast.makeText(this, "Felhasználónév vagy Email megadása kötelező", Toast.LENGTH_SHORT).show();
        }
        if (password.isEmpty()){
            Toast.makeText(this, "Jelszó megadása kötelező", Toast.LENGTH_SHORT).show();
        }
        if (adatok.getCount()==0){
            Toast.makeText(this, "Hiba a bejelentlezés során", Toast.LENGTH_SHORT).show();
        }else if (adatok.getCount()>0) {
            Intent login=new Intent(this,LoggedInActivity.class);
            startActivity(login);
            finish();
        }

    }

}