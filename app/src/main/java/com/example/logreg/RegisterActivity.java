    package com.example.logreg;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

    public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    EditText et_Username,et_Email,et_Password,et_FullName;
    Button btnSignUp,btnBack;
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
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.btnSignUp:
                    break;
                case R.id.btnBack:
                    break;
            }
        }
    }