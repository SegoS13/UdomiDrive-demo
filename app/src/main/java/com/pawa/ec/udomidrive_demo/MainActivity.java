package com.pawa.ec.udomidrive_demo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.pawa.ec.udomidrive_demo.UI.Driver.LoginDriverActivity;
import com.pawa.ec.udomidrive_demo.UI.Driver.DriverLocationActivity;
import com.pawa.ec.udomidrive_demo.UI.User.UserLocationActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.btn_continue) Button btn_continue;
    @BindView(R.id.btn_driver) Button btn_driver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        btn_continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, UserLocationActivity.class);
                //Intent intent = new Intent(MainActivity.this, MainActivity4.class);


                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });
        btn_driver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(FirebaseAuth.getInstance().getCurrentUser() != null){
                    Intent intent = new Intent(MainActivity.this, DriverLocationActivity.class);
                    startActivity(intent);
                }else {
                    Intent intent = new Intent(MainActivity.this, LoginDriverActivity.class);
                    startActivity(intent);
                }
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });
    }

}
