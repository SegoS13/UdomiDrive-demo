package com.pawa.ec.udomidrive_demo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity5 extends AppCompatActivity {

    @BindView(R.id.edt_email) AppCompatEditText edt_email;
    @BindView(R.id.edt_password) AppCompatEditText edt_password;
    @BindView(R.id.btn_accept) Button btn_accept;

    FirebaseAuth mAuth;
    DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);
        ButterKnife.bind(this);

        //Instanciamos
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        btn_accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });
    }
    private void login() {
        if(!edt_email.getText().toString().isEmpty() && !edt_password.getText().toString().isEmpty()){
            //minimo 6 caracteres
            if (edt_password.length()>=6){
                
                mAuth.signInWithEmailAndPassword(edt_email.getText().toString(), edt_password.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        // si se realizo la tarea
                        if(task.isSuccessful()){
                            Toast.makeText(MainActivity5.this, "Login realizado correctamente", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(MainActivity5.this, "El email o la contrasena son incorrectos", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        }else {
            Toast.makeText(this, "Llene los campos", Toast.LENGTH_SHORT).show();
        }
    }
}