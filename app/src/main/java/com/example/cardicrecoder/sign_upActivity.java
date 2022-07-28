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

public class sign_upActivity extends AppCompatActivity {
    EditText Email;
    EditText Password;
    Button Login;
    TextView Gotologin;
    Toolbar toolbar;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        Email=findViewById(R.id.email_s);
        Password=findViewById(R.id.password_s);
        Login=findViewById(R.id.signup);
        Gotologin=findViewById(R.id.gotologin);

        mAuth=FirebaseAuth.getInstance();

        setToolbar();
        Login.setOnClickListener(v->{
          userRegister();
        });

        Gotologin.setOnClickListener(v-> {
            Intent intent = new Intent(this, loginActivity.class);
            startActivity(intent);
        } );



    }

    private void userRegister() {
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
        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this,new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(sign_upActivity.this, "Account Created", Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                    intent.putExtra("email",email);
                    startActivity(intent);

                } else {
                    // If sign in fails, display a message to the user.
                    if(task.getException() instanceof FirebaseAuthUserCollisionException)
                    {
                        Toast.makeText(sign_upActivity.this, "User is already registered", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(sign_upActivity.this, "Registration is not Successfull", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    private void setToolbar() {
        toolbar=findViewById(R.id.toolbar);
        TextView title=toolbar.findViewById(R.id.toolbartitle);
        ImageButton back=toolbar.findViewById(R.id.back);
        title.setText("Sign Up");
        back.setVisibility(View.GONE);
    }
}