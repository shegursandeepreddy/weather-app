package com.example.weather;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.weather.data.DBHelper;

public class Profile extends AppCompatActivity {


    private EditText password, username;
    private TextView closeTextBtn, saveTextButton;
    private Button delete,logout;
    DBHelper d1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);



        password=(EditText) findViewById(R.id.settings_password);
        username=(EditText) findViewById(R.id.settings_username);
        closeTextBtn=findViewById(R.id.close_setting);
        saveTextButton=findViewById(R.id.update_setting);
        delete=findViewById(R.id.delete);
        logout=findViewById(R.id.logout);
        d1=new DBHelper(this);

        closeTextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(Profile.this,home.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Profile.this,login.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);

            }
        });

        saveTextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String user=username.getText().toString();

                String pass=password.getText().toString();
                if(user.equals("")||pass.equals("")){
                    Toast.makeText(Profile.this,"Please fill all the details",Toast.LENGTH_LONG).show();
                }else{
                     boolean updateData= d1.updateUserData(user,pass);
                     if (updateData==true){
                         Toast.makeText(Profile.this,"Profile Has been Updated",Toast.LENGTH_LONG).show();
                         Intent intent=new Intent(Profile.this,login.class);
                         intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                         startActivity(intent);
                     }
                     else {
                         Toast.makeText(Profile.this,"Getting Error",Toast.LENGTH_LONG).show();

                     }

                }

            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user=username.getText().toString();
                if(user.equals("")){
                    Toast.makeText(Profile.this,"Please Enter the Username",Toast.LENGTH_LONG).show();
                }else{
                    boolean updateData= d1.deleteUser(user);
                    if (updateData==true){
                        Toast.makeText(Profile.this,"Profile Has been Deleted",Toast.LENGTH_LONG).show();
                        Intent intent=new Intent(Profile.this,login.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                    }
                    else {
                        Toast.makeText(Profile.this,"User is not found",Toast.LENGTH_LONG).show();

                    }

                }
            }
        });
    }
}