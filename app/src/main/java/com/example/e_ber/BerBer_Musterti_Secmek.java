package com.example.e_ber;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class BerBer_Musterti_Secmek extends AppCompatActivity   {

    private Button btnKadinKuaforu,btnErkekKuaforu,btnRandevularim;
    FirebaseDatabase database= FirebaseDatabase.getInstance();
    DatabaseReference myRefAppointemnt= database.getReference("BIBBBO/Appointment");
    private TextView tvshow;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ber_ber__musterti__secmek);
        btnKadinKuaforu=findViewById(R.id.btnKadinKuaforu);
        btnErkekKuaforu=findViewById(R.id.btnErkekKuaforu);
        btnRandevularim=findViewById(R.id.btnRandevularim);
        //tvshow=findViewById(R.id.tvshow);


        myRefAppointemnt.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
              for (DataSnapshot item3: dataSnapshot.getChildren()){
                  AppointmentModel appointment;
                  appointment=item3.getValue(AppointmentModel.class);
                 if(appointment.getBerberIsmi().equals(getIntent().getStringExtra("musteriIsmi"))){
                     tvshow.setText(getIntent().getStringExtra("kuaforiIsmi"));
                 }

              }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        String MusteriIsmi=getIntent().getStringExtra("musteriIsmi");




        btnErkekKuaforu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (BerBer_Musterti_Secmek.this,BerEkraniActivity.class);
                startActivity(intent);
            }
        });


    }

}
