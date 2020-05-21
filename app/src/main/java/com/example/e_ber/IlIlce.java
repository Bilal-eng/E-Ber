package com.example.e_ber;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.collection.ArraySortedMap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static android.media.CamcorderProfile.get;

public class IlIlce extends AppCompatActivity {
    private Spinner srIl;
    private TextView tvText;
    private Spinner srIlce,srMah;
    private  IllerModel selectedIl;
    private  IlcelerModeli selectedIlce;
    private Button btnBul;
    private ImageButton ivbackone;
    private MahallelerModeli selectedMah;
    private ProgressBar prloaderone;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("cities/2/data");
    DatabaseReference MyRef2=database.getReference("towns/2/data");
    DatabaseReference MyRef3=database.getReference("mah/2/data");
    ArrayList<IllerModel> object=new ArrayList<>();
    ArrayList<IlcelerModeli> object2=new ArrayList<>();
    ArrayList<MahallelerModeli>object3=new ArrayList<>();
    //ArrayList<IllerModel> iller =new ArrayList<>();
    ArrayList<IlcelerModeli> ilcelerforil =new ArrayList<>();
    ArrayList<MahallelerModeli> mahalleler =new ArrayList<>();
    ArrayList<BerberModel> obj=new ArrayList<>();

   // ArrayList<>
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loction);
        srIl=findViewById(R.id.srIl);
        srIlce=findViewById(R.id.srIlce);
        srMah=findViewById(R.id.srMah);
        btnBul=findViewById(R.id.btnBul);
        ivbackone=findViewById(R.id.ivbackone);
        prloaderone=findViewById(R.id.prloaderone);
          prloaderone.setVisibility(View.VISIBLE);


        srMah.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectedMah = mahalleler.get(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) { }
        });

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
                        ArrayAdapter adapter3=new ArrayAdapter<>(IlIlce.this,R.layout.custom_drop_down_list_item,SpinnerArray3);
                        srMah.setAdapter(adapter3);
                    }
                }
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

                ArrayAdapter<String>adapter= new ArrayAdapter<>(IlIlce.this,R.layout.custom_drop_down_list_item,spinnerArray);
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
                               //Log.e("muti",item1.getIlce_title() +"");
                               // ilcelerforil.get(0).getName()
                                ArrayAdapter<String > adapter2=new ArrayAdapter<>(IlIlce.this,R.layout.custom_drop_down_list_item,SpinnerArray2);
                                srIlce.setAdapter(adapter2);
                                if(srIl!=null)
                                    prloaderone.setVisibility(View.GONE);
                            }
                        }

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) { }
                });

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("muti", "Failed to read value.", error.toException());

            }
        });






        btnBul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(srIl!=null&& srIlce!=null&& srMah!=null) {

                Intent intent = new Intent (IlIlce.this,BerBilgisiActivity.class);
                intent.putExtra("Mah", selectedMah);
                startActivity(intent);
                }
                else
                {
                    Toast.makeText(IlIlce.this,"Please fill out yor Provision place",Toast.LENGTH_LONG).show();
                }
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
