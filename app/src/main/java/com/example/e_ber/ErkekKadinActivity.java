package com.example.e_ber;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ErkekKadinActivity extends AppCompatActivity {
private Button btnErkek,btnKadin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.erkek_kadin_arayuzu);
        btnErkek=findViewById(R.id.btnErkek);
        btnKadin=findViewById(R.id .btnkadin);
        btnErkek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        btnErkek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ErkekKadinActivity.this,IlIlce.class);
                startActivity(intent);

            }
        });

    }

}
