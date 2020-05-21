package com.example.e_ber;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.internal.bind.ArrayTypeAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static android.view.View.GONE;

public class BerEkraniActivity extends AppCompatActivity {
    private Spinner srIl;
    private Spinner srIlce,srMah;
    private  IllerModel selectedIl;
    private  IlcelerModeli selectedIlce;
    private Button btnSave;
    private ImageButton ivbackone;
    private EditText etBerStoreName;
    private MahallelerModeli selectedMah;
    private ProgressBar prloader;
    private Button btnRandev;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("cities/2/data");
    DatabaseReference MyRef2=database.getReference("towns/2/data");
    DatabaseReference MyRef3=database.getReference("mah/2/data");
    DatabaseReference berRef = database.getReference("Berberler");
    ArrayList<IllerModel> object=new ArrayList<>();
    ArrayList<IlcelerModeli> object2=new ArrayList<>();
    ArrayList<MahallelerModeli>object3=new ArrayList<>();
    //ArrayList<IllerModel> iller =new ArrayList<>();
    ArrayList<IlcelerModeli> ilcelerforil =new ArrayList<>();
    ArrayList<MahallelerModeli> mahalleler =new ArrayList<>();


    // ArrayList<>
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ber_ekrani);
        btnRandev = findViewById(R.id.btnRandev);
        srIl=findViewById(R.id.srIl);
        srIlce=findViewById(R.id.srIlce);
        srMah=findViewById(R.id.srMah);
        btnSave = findViewById(R.id.btnSave);
        ivbackone=findViewById(R.id.ivbackone);
        etBerStoreName = findViewById(R.id.etBerStoreName);
        prloader = findViewById(R.id.prloader);
        prloader.setVisibility(View.VISIBLE);




        srIlce.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectedIlce = ilcelerforil.get(i);
                //Log.e("GET i", ilcelerforil.get(i) +   " i");
                // Log.e("I",i +"ii");
                //  Log.e("Bak",selectedIl.getName());
                List<String> SpinnerArray3=new ArrayList<>();
                //mahalleler.clear();
                for(MahallelerModeli item3:object3){
                    if(selectedIlce.getIlce_key().equals(item3.getMahalle_ilcekey())) {
                        SpinnerArray3.add(item3.getMahalle_title());

                        mahalleler.add(item3);
                        ArrayAdapter adapter3=new ArrayAdapter<>(BerEkraniActivity.this,R.layout.custom_drop_down_list_item,SpinnerArray3);
                        srMah.setAdapter(adapter3);

                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        srMah.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectedMah = mahalleler.get(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        MyRef2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                object2.clear();
                for(DataSnapshot item2:dataSnapshot.getChildren()){
                    object2.add(item2.getValue(IlcelerModeli.class));
                }
                Collections.sort(object2, new Comparator<IlcelerModeli>() {
                    @Override
                    public int compare(IlcelerModeli illerModel, IlcelerModeli t1) {
                        return illerModel.getIlce_title().compareTo(t1.getIlce_title());
                    }
                });
                //Collections.sort(spinnerArray);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
        MyRef3.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                object3.clear();
                for(DataSnapshot item4:dataSnapshot.getChildren()){
                    object3.add(item4.getValue(MahallelerModeli.class));
                    //Log.e("muti", item3 +"");
                }
//
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                final List<String> spinnerArray = new ArrayList<>();
                object.clear();
                // object.clear();
                for(DataSnapshot item : dataSnapshot.getChildren()){

                    object.add(item.getValue(IllerModel.class));

                    spinnerArray.add(object.get(object.size() - 1).getSehir_title());
                }
                Collections.sort(object, new Comparator<IllerModel>() {
                    @Override
                    public int compare(IllerModel illerModel, IllerModel t1) {
                        return illerModel.getSehir_title().compareTo(t1.getSehir_title());
                    }
                });
                Collections.sort(spinnerArray);

                ArrayAdapter<String>adapter= new ArrayAdapter<>(BerEkraniActivity.this,R.layout.custom_drop_down_list_item,spinnerArray);
                srIl.setAdapter(adapter);

                srIl.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, final int position, long l) {
                        selectedIl = object.get(position);
                        List<String>SpinnerArray2=new ArrayList<>();
                        ilcelerforil.clear();
                        for (IlcelerModeli item1: object2){
                            if (selectedIl.getSehir_key().equals(item1.getIlce_sehirkey())){
                                SpinnerArray2.add(item1.getIlce_title());

                                ilcelerforil.add(item1);
                                Log.e("muti",item1.getIlce_title() +"");
                                // ilcelerforil.get(0).getName()
                                ArrayAdapter<String > adapter2=new ArrayAdapter<>(BerEkraniActivity.this,R.layout.custom_drop_down_list_item,SpinnerArray2);
                                srIlce.setAdapter(adapter2);
                            }
                        }

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) { }

                });

                prloader.setVisibility(GONE);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("muti", "Failed to read value.", error.toException());

            }
        });
//        if (srIl!=null)
//            prloader.setVisibility(GONE);


        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String berStoreName = etBerStoreName.getText().toString();
                if(srIl != null && srIlce != null && srMah != null && !berStoreName.isEmpty()) {
                    BerberModel berber = new BerberModel();
                    berber.setIl(selectedIl);
                    berber.setIlce(selectedIlce);
                    berber.setMah(selectedMah);
                    berber.setStoreName(etBerStoreName.getText().toString());
                    berRef.push().setValue(berber);
                    btnSave.setEnabled(false);
//                    btnSave.setBackgroundColor(getResources().getColor(R.color.gri));
                    btnSave.setBackground(getResources().getDrawable(R.drawable.rectangle_bg_image));
                    Toast.makeText(BerEkraniActivity.this,"Adresiniz Basari ile  gonderilmistir !",Toast.LENGTH_LONG).show();
                    Intent intent= new Intent(BerEkraniActivity.this,BerBer_Musterti_Secmek.class);
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(BerEkraniActivity.this,"Please fill out yor Provision place",Toast.LENGTH_LONG).show();
                }
            }
        });

        btnRandev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(BerEkraniActivity.this,RandevularimActivity.class);
                startActivity(intent);
            }
        });

        ivbackone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // finish();
                onBackPressed();
            }
        });
    }

}
