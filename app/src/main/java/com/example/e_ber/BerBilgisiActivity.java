package com.example.e_ber;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class BerBilgisiActivity extends AppCompatActivity {
    private ListView lvBer;
    private TextView tvBerIsmi, tvAdres,tvBalnce;
    private ImageView ivtwo;
    //private ProgressBar prloader;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef4 = database.getReference("BIBBOO");
    DatabaseReference berberlerRef = database.getReference("Berberler");
    //DatabaseReference myRef5=database.getReference("LqeXnV3jTLi7hPY80ze/Ranking2");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ber_bilgisi);
        tvBerIsmi=findViewById(R.id.tvBerIsmi);
        tvAdres=findViewById(R.id.tvAdres);
        ivtwo=findViewById(R.id.ivback);

        final List<BerberModel> berberler = new ArrayList<>();
        final MahallelerModeli mah = getIntent().getParcelableExtra("Mah");
        final ArrayList<BerModeli> object=new ArrayList<>();
        final ListView listView=findViewById(R.id.lvBer);
        if (mah != null){
            berberlerRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    berberler.clear();
                    for(DataSnapshot item5:dataSnapshot.getChildren()){
                        BerberModel ber;
                        ber = item5.getValue(BerberModel.class);
                        if (ber != null && ber.getMah() != null){
                            if (mah.getMahalle_id().equals(ber.getMah().getMahalle_id())){
                                ber.setUd(item5.getKey());
                                berberler.add(ber);
                            }
                        }
                    }
                    Myadaptert adapter = new Myadaptert(berberler,BerBilgisiActivity.this);
                    listView.setAdapter(adapter);
                }
                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }


    }

}
