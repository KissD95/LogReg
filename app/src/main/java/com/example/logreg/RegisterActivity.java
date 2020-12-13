    package com.example.logreg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

    public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    EditText et_Username,et_Email,et_Password,et_FullName;
    Button btnSignUp,btnBack;
    DbHelper adatbazis;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        init();
    }

        private void init() {
        et_Username=findViewById(R.id.editTextUsernameReg);
        et_Email=findViewById(R.id.editTextEmail);
        et_Password=findViewById(R.id.editTextPasswordReg);
        et_FullName=findViewById(R.id.editTextName);

        btnSignUp=findViewById(R.id.btnSignUp);
        btnBack=findViewById(R.id.btnBack);


        btnBack.setOnClickListener(this);
        btnSignUp.setOnClickListener(this);

        adatbazis=new DbHelper(RegisterActivity.this);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.btnSignUp:
                    adatRogzites();
                    break;
                case R.id.btnBack:
                    Intent back= new Intent(this,MainActivity.class);
                    startActivity(back);
                    finish();
                    break;
            }
        }

        private void adatRogzites() {
                String username=et_Username.getText().toString().trim();
                String email=et_Email.getText().toString().trim();
                String password=et_Password.getText().toString().trim();
                String fullname=et_FullName.getText().toString().trim();
                int fullnameSpaceIndex= fullname.indexOf(" ");
                int atSignIndex=email.indexOf("@");


                if (email.isEmpty()){
                     Toast.makeText(this, "Email megadása kötelező!", Toast.LENGTH_SHORT).show();
                     return;
                }
                if (atSignIndex==-1){
                    Toast.makeText(this, "Az emailnek tartalmaznia kell @ jelet", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (username.isEmpty()){
                    Toast.makeText(this, "Felhasználónév megadása kötelező!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (password.isEmpty()){
                    Toast.makeText(this, "Jelszó megadása kötelező!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (fullname.isEmpty()){
                    Toast.makeText(this, "Teljes név megadása kötelező!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (fullnameSpaceIndex==-1){
                    Toast.makeText(this, "A teljes név legalább két névből áll.", Toast.LENGTH_SHORT).show();
                    return;
                }


            if (adatbazis.Regisztracio(email,username,password,fullname)){
                Toast.makeText(this, "Sikeres regisztráció!", Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(this, "Sikertelen regisztráció!", Toast.LENGTH_SHORT).show();
            }
        }
    }