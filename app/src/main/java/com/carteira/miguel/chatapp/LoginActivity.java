package com.carteira.miguel.chatapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.rengwuxian.materialedittext.MaterialEditText;

public class LoginActivity extends AppCompatActivity {

    MaterialEditText email, senha;

    FirebaseAuth auth;
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Login");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        btnLogin = (Button) findViewById(R.id.btn_login);
        email = findViewById(R.id.email);
        senha = findViewById(R.id.senha);

        auth = FirebaseAuth.getInstance();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txtemail = email.getText().toString();
                String txtsenha = senha.getText().toString();

                if (TextUtils.isEmpty(txtemail) | TextUtils.isEmpty(txtsenha)) {
                    Toast.makeText(LoginActivity.this, "Todos os campos sao Obrigatorios", Toast.LENGTH_SHORT).show();
                } else {
                    auth.signInWithEmailAndPassword(txtemail, txtsenha).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                         if (task.isSuccessful()) {
                             Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                             intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                             startActivity(intent);
                             finish();
                         } else {
                             Toast.makeText(LoginActivity.this, "Falha ao entrar na conta", Toast.LENGTH_SHORT).show();
                         }
                        }
                    });
                }

            }
        });

    }
}
