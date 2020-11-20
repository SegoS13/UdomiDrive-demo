package com.pawa.ec.udomidrive_demo.UI.User;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.pawa.ec.udomidrive_demo.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Verification2Activity extends AppCompatActivity {
    @BindView(R.id.btn_continue) Button btn_continue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification2);
        ButterKnife.bind(this);

        btn_continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Verification2Activity.this, DatosUserActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });
    }
}