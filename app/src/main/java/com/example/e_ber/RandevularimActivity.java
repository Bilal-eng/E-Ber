package com.example.e_ber;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Adapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import static com.example.e_ber.YorumAdapeter.Default;

public class RandevularimActivity extends AppCompatActivity {
    private TextView tvShowone, tvKuaforunIsmi,tvTarih;


    // private ListView  listView;
    FirebaseDatabase database= FirebaseDatabase.getInstance();
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    DatabaseReference myRef10 = database.getReference("BIBBOO/Appointment");
    String currentuser = FirebaseAuth.getInstance().getCurrentUser().getUid();
    ArrayList<AppointmentModel> kuaforObjects5= new ArrayList<>();
    ArrayList<AppointmentModel> musteriObjects5= new ArrayList<>();
    String uid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_randevularim);
        final    ListView listView = findViewById(R.id.lvRandevu);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        SharedPreferences sharedPreferences = getSharedPreferences("mydata", Context.MODE_PRIVATE);
       uid = user.getUid();
        //uid = currentuser;
        myRef10.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                AppointmentModel appointment;
                for( DataSnapshot item:dataSnapshot.getChildren()){
                    appointment= item.getValue(AppointmentModel.class);
                    //objects5.clear();
                    //objects5.add(appointment);
                    if(appointment.getBerberIsmi().equals("ETI BERBER")){
                        kuaforObjects5.add(appointment);
                    }
                AppointmentAdapter adapter =  new AppointmentAdapter(kuaforObjects5,RandevularimActivity.this);
                listView.setAdapter(adapter);

                }}
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
}
