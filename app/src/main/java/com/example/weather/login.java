package com.example.weather;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.example.weather.data.DBHelper;
import com.google.android.material.textfield.TextInputLayout;

public class login extends AppCompatActivity {
    private TextInputLayout username, password;

    private ProgressDialog loadingbar;
    private CheckBox remember;

    DBHelper d1;

    private Button b1, b2, forget_btr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);
        b1=findViewById(R.id.signup);
        b2=findViewById(R.id.login);


        username=findViewById(R.id.user_name);
        password=findViewById(R.id.pass_word);

        d1=new DBHelper(this);
        loadingbar=new ProgressDialog(this);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(login.this, Register.class);
                startActivity(intent);
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user=username.getEditText().getText().toString();

                String pass=password.getEditText().getText().toString();
                if(user.equals("")||pass.equals("")){
                    Toast.makeText(login.this,"Please fill all the details",Toast.LENGTH_LONG).show();
                }
                else {
                    Boolean checkUser=d1.checkusernamepassword(user,pass);
                    if (checkUser==true){
                        Toast.makeText(login.this,"Login Successfully",Toast.LENGTH_LONG).show();
                        Intent intent=new Intent(login.this,home.class);
                        startActivity(intent);
                    }else {
                        Toast.makeText(login.this,"Invalid Credential",Toast.LENGTH_LONG).show();
                    }
                }
            }
        });


    }
}