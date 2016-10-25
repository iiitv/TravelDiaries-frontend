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
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {
    private EditText email,password;
    private Button btLogin;
    private TextView txtRegister;
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        firebaseAuth=FirebaseAuth.getInstance();

        if (firebaseAuth.getCurrentUser()!=null){
            startActivity(new Intent(Login.this, MainActivity.class));
        }

        progressDialog=new ProgressDialog(this);
        email=(EditText)findViewById(R.id.email);
        password=(EditText)findViewById(R.id.password);
        btLogin=(Button)findViewById(R.id.blogin);
        txtRegister=(TextView)findViewById(R.id.textViewRegister);





        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userLogin();
                //startActivity(new Intent(Login.this, MainActivity.class));
            }
            });




        txtRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Login.this, Register.class));
            }
        });



    }

    public void  userLogin() {
        String Email = email.getText().toString().trim();
        String Password = password.getText().toString().trim();

        if (TextUtils.isEmpty(Email)) {
            Toast.makeText(this, "Please enter mail id", Toast.LENGTH_SHORT).show();
            return;

        }
        if (TextUtils.isEmpty(Password)) {
            Toast.makeText(this, "Please enter password", Toast.LENGTH_SHORT).show();
            return;
        }
        progressDialog.setMessage("Please wait for Login...");
        progressDialog.show();


        firebaseAuth.signInWithEmailAndPassword(Email,Password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
               progressDialog.dismiss();
                if(task.isSuccessful()){
                    finish();
                     Toast.makeText(Login.this,"Successful Login",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(Login.this,MainActivity.class));

                }
                else{
                    Toast.makeText(Login.this,"Email or Password is wrong",Toast.LENGTH_SHORT).show();
                }




            }
        });
    }
}
