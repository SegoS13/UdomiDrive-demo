package com.pawa.ec.udomidrive_demo.UI.Driver;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.Toolbar;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.pawa.ec.udomidrive_demo.MainActivity;
import com.pawa.ec.udomidrive_demo.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import dmax.dialog.SpotsDialog;

public class LoginDriverActivity extends AppCompatActivity {

    @BindView(R.id.edt_email) AppCompatEditText edt_email;
    @BindView(R.id.edt_password) AppCompatEditText edt_password;
    @BindView(R.id.btn_accept) Button btn_accept;
    @BindView(R.id.btn_crear_cuenta) TextView btn_crear_cuenta;

    FirebaseAuth mAuth;
    DatabaseReference mDatabase;
    AlertDialog alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logindrive);
        ButterKnife.bind(this);

        //Instanciamos
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        alertDialog = new SpotsDialog.Builder().setContext(LoginDriverActivity.this).setMessage("Espere....").build();

        setToolbar();

        btn_accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });

        btn_crear_cuenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginDriverActivity.this, CuentaActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });
    }
    private void login() {
        if(!edt_email.getText().toString().isEmpty() && !edt_password.getText().toString().isEmpty()){
            //minimo 6 caracteres
            if (edt_password.length()>=6){
                alertDialog.show();
                mAuth.signInWithEmailAndPassword(edt_email.getText().toString(), edt_password.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        // si se realizo la tarea
                        if(task.isSuccessful()){
                            //Toast.makeText(LoginDriverActivity.this, "Login realizado correctamente", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(LoginDriverActivity.this, MainActivity2.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(intent);
                        }else {
                            Toast.makeText(LoginDriverActivity.this, "El email o la contrasena son incorrectos", Toast.LENGTH_SHORT).show();
                        }
                        alertDialog.dismiss();
                    }
                });
            }
        }else {
            Toast.makeText(this, "Llene los campos", Toast.LENGTH_SHORT).show();
        }
    }
    private void setToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        TextView lblTitle = toolbar.findViewById(R.id.tvToolbarTitle);
        ImageButton btnToolbar1 = toolbar.findViewById(R.id.ibToolbar1);

        lblTitle.setText("LOGIN");
        btnToolbar1.setImageDrawable(getDrawable(R.drawable.arrow_left));

        btnToolbar1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginDriverActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        setSupportActionBar(toolbar);

    }
}