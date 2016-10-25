package com.jsrathore.traveldairies;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;

public class Register extends AppCompatActivity {
    private EditText email,password;
    private Button btRegister;
    private TextView backToLogin;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;

    @Override


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        progressDialog=new ProgressDialog(this);
        firebaseAuth=FirebaseAuth.getInstance();

        email=(EditText)findViewById(R.id.email);
        password=(EditText)findViewById(R.id.password);
        btRegister=(Button)findViewById(R.id.bregister);
        backToLogin=(TextView)findViewById(R.id.textViewLogin);



        btRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerData();

                startActivity(new Intent(Register.this,Login.class));
            }
        });

        backToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Register.this,Login.class));
            }
        });



    }
    public void registerData(){
        String Email=email.getText().toString().trim();
        String Password=password.getText().toString().trim();
        if (TextUtils.isEmpty(Email)){
            Toast.makeText(this, "Please enter mail id",Toast.LENGTH_SHORT).show();
            return;

        }
        if (TextUtils.isEmpty(Password)){
            Toast.makeText(this, "Please enter password",Toast.LENGTH_SHORT).show();
            return;

        }


        progressDialog.setMessage("Please wait for Registration...");
        progressDialog.show();

        firebaseAuth.createUserWithEmailAndPassword(Email,Password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful()){
                    Toast.makeText(Register.this,"Successful register",Toast.LENGTH_SHORT).show();
                    if (firebaseAuth.getCurrentUser()!=null){
                        finish();
                        startActivity(new Intent(Register.this, Login.class));
                    }

                }
                else{
                    Toast.makeText(Register.this,"Could not register",Toast.LENGTH_SHORT).show();
                }
            }
        });


    }




}
