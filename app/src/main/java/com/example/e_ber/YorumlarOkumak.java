package com.example.e_ber;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class YorumlarOkumak extends AppCompatActivity {
 private TextView tvIsim,tvYorum;
    FirebaseDatabase database = FirebaseDatabase.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yorumlar_okumak);
        tvYorum=findViewById(R.id.etYorum);
        final ListView listView =findViewById(R.id.lvListview);
        final ArrayList<BerYorum> yorumlar = new ArrayList<>();
        String berUId = getIntent().getStringExtra("BerUID");
        DatabaseReference myRef = database.getReference("Berberler/" + berUId + "/Yorumlar");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
               for(DataSnapshot item : dataSnapshot.getChildren()){
                    BerYorum berYorum;
                    berYorum = item.getValue(BerYorum.class);
                   yorumlar.add(berYorum);

               }
               YorumAdapeter myAdapter=new YorumAdapeter(yorumlar,YorumlarOkumak.this);
                listView.setAdapter(myAdapter);
            }
            @Override
            public void onCancelled(DatabaseError error) {
                Log.w("muti", "Failed to read value.", error.toException());
            }
        });

    }
}
