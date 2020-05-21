package com.example.e_ber;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Kuafor_Musteri extends AppCompatActivity {
    private Button btnKuafor,btnMusteri;
    private Boolean durum=false;
    public static final String SHARED_PREF= "SharedPefs";

    FirebaseDatabase database=FirebaseDatabase.getInstance();
    DatabaseReference myRef11=database.getReference("BIBBO/Bolean");



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kuafor__musteri);
        btnKuafor=findViewById(R.id.btnKuafor);
        btnMusteri=findViewById(R.id.btnMusteri);
        btnKuafor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(Kuafor_Musteri.this,BerBer_Musterti_Secmek.class);
                startActivity(intent);


            }
        });
        btnMusteri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent (Kuafor_Musteri.this,IlIlce.class);
                startActivity(intent);
                durum=true;

                SharedPreferences sharedPreferences=getSharedPreferences("SHARED_PREF", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor= sharedPreferences.edit();
                editor.putBoolean("Durum",durum);
                editor.apply();


            }
        });
    }
}
