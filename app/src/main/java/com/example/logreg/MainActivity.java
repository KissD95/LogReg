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
    Button btnRegister,btnLogin,btnList;
    EditText editTextUsername,editTextPassword;
    DbHelper adatbazis;
    TextView textViewDatas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

    }

    private void init() {
        btnLogin=findViewById(R.id.btnLogin);
        btnRegister=findViewById(R.id.btnRegister);
        btnList=findViewById(R.id.btnList);
        editTextUsername=findViewById(R.id.editTextUsername);
        editTextPassword=findViewById(R.id.editTextPassword);

        textViewDatas=findViewById(R.id.textViewDatas);

        btnRegister.setOnClickListener(this);
        btnLogin.setOnClickListener(this);
        btnList.setOnClickListener(this);

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
            case R.id.btnList:
                adatLekerdezes();
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
    private void adatLekerdezes() {
        Cursor adatok= adatbazis.adatLekeredezes();
        if (adatok == null){
            Toast.makeText(this, "Hiba történt!", Toast.LENGTH_SHORT).show();
            return;
        }
        if (adatok.getCount()==0){
            Toast.makeText(this, "Nincs még adat felvéve!", Toast.LENGTH_SHORT).show();
        }
        StringBuilder builder = new StringBuilder();
        while (adatok.moveToNext()){
            builder.append("Id: ").append(adatok.getInt(0)).append("\n");
            builder.append("Email: ").append(adatok.getString(1)).append("\n");
            builder.append("Flehasználónév: ").append(adatok.getString(2)).append("\n");
            builder.append("Jelszó: ").append(adatok.getString(3)).append("\n");
            builder.append("Teljes név: ").append(adatok.getString(4)).append("\n\n");
        }
        textViewDatas.setText(builder);
    }

}