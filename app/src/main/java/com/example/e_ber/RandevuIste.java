package com.example.e_ber;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class RandevuIste extends AppCompatActivity {
private Button btnonaylama;
private TextView tvIsim;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_randevu_iste);
        //btnonaylama=findViewById(R.id.btnonaylama);
        tvIsim=findViewById(R.id.tvIsim);
        btnonaylama.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int x=0;
                x=x+1;

                Intent intent=new Intent (RandevuIste.this,RandevuAlDegerlendir.class);
                intent.putExtra("Onay Verildi mi",x);
                startActivity(intent);


            }
        });

    }
}
