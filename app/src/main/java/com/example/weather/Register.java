package com.example.weather;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.example.weather.data.DBHelper;
import com.example.weather.data.MyDbHandler;
import com.google.android.material.textfield.TextInputLayout;

public class Register extends AppCompatActivity {
    private Button sign_bt,back;
    private TextInputLayout username,password;
    MyDbHandler db;
    DBHelper d1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        username=findViewById(R.id.username);

        password=findViewById(R.id.password1);
        sign_bt=findViewById(R.id.reg);
        back=findViewById(R.id.backlog);
        db=new MyDbHandler(this);
        d1=new DBHelper(this);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Register.this,login.class);
                startActivity(intent);
            }
        });
        sign_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String user=username.getEditText().getText().toString();

                String pass=password.getEditText().getText().toString();
                if(user.equals("")||pass.equals("")){
                    Toast.makeText(Register.this,"Please fill all the details",Toast.LENGTH_LONG).show();
                }
                else {
                    Boolean checkUser= d1.checkusername(user);
                    if(checkUser==false){
                        Boolean insert=d1.insertData(user,pass);
                        if(insert==true){
                            Toast.makeText(Register.this,"Registered Successfully",Toast.LENGTH_LONG).show();
                            Intent intent=new Intent(Register.this,login.class);
                            startActivity(intent);
                        }else {
                            Toast.makeText(Register.this,"Registered Failed",Toast.LENGTH_LONG).show();
                        }
                    }else {
                        Toast.makeText(Register.this,"User already Exists",Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }
}