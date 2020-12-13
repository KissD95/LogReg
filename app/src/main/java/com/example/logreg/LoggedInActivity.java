package com.example.logreg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class LoggedInActivity extends AppCompatActivity {
    Button btnSignOut;
    TextView textViewUsername;
    DbHelper adatbazis;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logged_in);
        init();
        adatLekerdezes();

        btnSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signout= new Intent(LoggedInActivity.this,MainActivity.class);
                startActivity(signout);
                finish();
            }
        });
    }

    private void init() {
        btnSignOut=findViewById(R.id.btnSignOut);
        textViewUsername=findViewById(R.id.textViewUsername);

        adatbazis= new DbHelper(this);


    }
    private void adatLekerdezes() {
        Cursor adatok= adatbazis.userDatas();
        if (adatok == null){
            Toast.makeText(this, "Hiba történt!", Toast.LENGTH_SHORT).show();
            return;
        }
        if (adatok.getCount()==0){
            Toast.makeText(this, "Nincs még adat felvéve!", Toast.LENGTH_SHORT).show();
        }
        StringBuilder builder = new StringBuilder();
        while (adatok.moveToNext()){
            builder.append("Üdvözlünk ").append(adatok.getString(0)).append("!");

        }
        textViewUsername.setText(builder);
    }
}