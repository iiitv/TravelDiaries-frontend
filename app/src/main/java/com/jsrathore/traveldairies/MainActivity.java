package com.jsrathore.traveldairies;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private TextView txtemail,txtpassword;
    private Button btlogout;
    Register register=new Register();
    private FirebaseAuth firebaseAuth;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebaseAuth=FirebaseAuth.getInstance();
        //if already login then do first log out then again login
        if (firebaseAuth.getCurrentUser()==null){
            finish();
            startActivity(new Intent(MainActivity.this,Login.class));
        }

        FirebaseUser user =firebaseAuth.getCurrentUser();


        txtemail=(TextView)findViewById(R.id.email);
        txtpassword=(TextView)findViewById(R.id.password);
        btlogout=(Button)findViewById(R.id.blogout);

        txtemail.setText("Welcomme to my application </br> This is yourID :  "+user.getEmail());
        txtpassword.setText("This is your Password : No It's Secret");



        btlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firebaseAuth.signOut();
                finish();
                startActivity(new Intent(MainActivity.this, Login.class));
            }
        });

    }
}
