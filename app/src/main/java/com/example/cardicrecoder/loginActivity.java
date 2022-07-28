package com.example.cardicrecoder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;

public class loginActivity extends AppCompatActivity {
    EditText Email;
    EditText Password;
    Button Login;
    TextView Gotosignup;
    Toolbar toolbar;
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Email=findViewById(R.id.email_l);
        Password=findViewById(R.id.password_l);
        Login=findViewById(R.id.login);
        Gotosignup=findViewById(R.id.gotosignup);
        setToolbar();

        mAuth=FirebaseAuth.getInstance();
        Login.setOnClickListener(v->{
            userlogin();
        });

        Gotosignup.setOnClickListener(v-> {
            Intent intent = new Intent(this, sign_upActivity.class);
            startActivity(intent);
        } );



    }

    private void userlogin() {
        String email = Email.getText().toString().trim();
        String password = Password.getText().toString().trim();
        if (email.isEmpty()) {
            Email.setError("Enter an email address");
            Email.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Email.setError("Enter a valid email address");
            Email.requestFocus();
            return;
        }

        //checking the validity of the password
        if (password.isEmpty()) {
            Password.setError("Enter a password");
            Password.requestFocus();
            return;
        }
        if (password.length() < 6) {
            Password.setError("Minimum Length of  a password should be 6");
        }

        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful())
                {
                    Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                    intent.putExtra("email",email);
                    startActivity(intent);

                } else {
                    // If sign in fails, display a message to the user.

                        Toast.makeText(loginActivity.this, "login Unsuccessful", Toast.LENGTH_SHORT).show();
                    }
                }

        });
    }

    private void setToolbar() {
        toolbar=findViewById(R.id.toolbar);
        TextView title=toolbar.findViewById(R.id.toolbartitle);
        ImageButton back=toolbar.findViewById(R.id.back);
        title.setText("Sign In");
        back.setVisibility(View.GONE);
    }
}