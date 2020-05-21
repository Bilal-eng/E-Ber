package com.example.e_ber;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;

import static com.example.e_ber.YorumAdapeter.Default;

public class RandevuAlDegerlendir extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {
    private Button btnGonder, btnRandevuIste, btnDegerlendir;
    private RatingBar rbRatingBar;
    private EditText etYorum;
    private TextView  btnYorumlar;
    private ImageView ivback;
    private TextView tvShowone,tvKuaforunIsmi,tvTarih;
    //private Boolean firstTime = false;
    private FirebaseAuth mAuth;

    private DatabaseReference myRef7;
    private  int year, month,day, hour,minute,yearfinal,monthfial,dayfinal,hourfinal,minutfinal;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference userNameRef;
    DatabaseReference myRef8 = database.getReference("BIBBOO / USERS");
    DatabaseReference MyRef10 = database.getReference("BIBBOO/Appointment");

    BerModeli ber;
    DatabaseReference myRef9;
    private String uid;
    private String userName;
    private String berName;
    private String berUid;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.salon_degerlendir);
        btnGonder = findViewById(R.id.btnGonder);
        btnRandevuIste = findViewById(R.id.btnRandevuIste);
        rbRatingBar = findViewById(R.id.rbRatingBar);
        etYorum = findViewById(R.id.etYorum);
        ivback= findViewById(R.id.ivback);
        btnDegerlendir = findViewById(R.id.btndegerlendir);
        btnYorumlar = findViewById(R.id.btnYorumlar);
        mAuth = FirebaseAuth.getInstance();
        // tvShowone=findViewById(R.id.tvShowone);
        // tvKuaforunIsmi = findViewById(R.id.tvKuaforunIsmi);
        // tvTarih=findViewById(R.id.tvTarih);


        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        FirebaseUser user1 = mAuth.getCurrentUser();
        SharedPreferences sharedPreferences = getSharedPreferences("mydata", Context.MODE_PRIVATE);
        userName = sharedPreferences.getString ("name",Default);
        uid = user.getUid();
        //uid = user1.getPhoneNumber();

        berName = getIntent().getStringExtra("BerName");
        berUid = getIntent().getStringExtra("BerUID");

        myRef7 = database.getReference("Berberler/" + berUid + "/Yorumlar");


        btnGonder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(etYorum.getText().toString().isEmpty() ){
                    Toast.makeText(RandevuAlDegerlendir.this ,"please write your comment",Toast.LENGTH_LONG).show();}

                else {
                    checkIfUserSentComment(berUid, uid, userName);
                }
            }
        });
        ivback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        btnYorumlar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RandevuAlDegerlendir.this, YorumlarOkumak.class);
                intent.putExtra("BerUID", berUid);
                startActivity(intent);
                KullanciInfoModel user1 = new KullanciInfoModel();
                user1.setYorumlar(etYorum.getText().toString());
                myRef8.push().setValue(user1);

            }
        });
        final String y;

        btnRandevuIste.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Calendar c= Calendar.getInstance();
//
                year=c.get(Calendar.YEAR);
                month=c.get(Calendar.MONTH);
                day=c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(RandevuAlDegerlendir.this,RandevuAlDegerlendir.this,year,month,day);
                datePickerDialog.show();


            }
        });

//        btnRandevuIste.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(RandevuAlDegerlendir.this,RandevularimActivity.class);
//                startActivity(intent);
//            }
//        });



        btnDegerlendir.setOnClickListener(new View.OnClickListener() {
            @Override


            public void onClick(View view) {


                            if (rbRatingBar.getRating()==0){

                                Toast.makeText(RandevuAlDegerlendir.this,"Please choose a Rating  value",Toast.LENGTH_LONG).show();

                            }
                            else
                            {

                    myRef9 = database.getReference("BIBBOO/" + berUid);
                    //myRef9.setValue(rbRatingBar.getRating());


                    final ValueEventListener eventListener = new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            ber = dataSnapshot.getValue(BerModeli.class);
                            float X = (ber.getRanking() * ber.getUserNumber())+ rbRatingBar.getRating();
                            ber.setUserNumber(ber.getUserNumber() + 1);
                            ber.setRanking(X / ber.getUserNumber());
                            myRef9.setValue(ber);
                            myRef9.removeEventListener(this);
                            //btnDegerlendir.setVisibility(View.GONE);
                            btnDegerlendir.setEnabled(false);

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    };
                    myRef9.addValueEventListener(eventListener);

                   Toast.makeText(RandevuAlDegerlendir.this,"Degerlendirme alindi Tesekkurler ",Toast.LENGTH_LONG).show();
                }}



        });




    }

    private void checkIfUserSentComment(String berUId, final String userId, final String useName){
        DatabaseReference myRef = database.getReference("Berberler/" + berUId + "/Yorumlar");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot item : dataSnapshot.getChildren()){
                    BerYorum berYorum;
                    berYorum = item.getValue(BerYorum.class);
                    if (berYorum.getUserId() != null){
                        if (berYorum.getUserId().equals(userId)){
                            Toast.makeText(RandevuAlDegerlendir.this,"Daha once yorum yaptiniz!", Toast.LENGTH_LONG).show();
                            return;
                        }
                    }
                }
                BerYorum berYorum = new BerYorum();
                berYorum.setYorum(etYorum.getText().toString());
                berYorum.setUserName(useName);
                berYorum.setUserId(userId);

                myRef7.push().setValue(berYorum);
                Toast.makeText(RandevuAlDegerlendir.this,"senin Yorumun Alindi Tesekkurler",Toast.LENGTH_LONG).show();
            }
            @Override
            public void onCancelled(DatabaseError error) {
                Log.w("muti", "Failed to read value.", error.toException());
            }
        });


    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
            yearfinal=year;
            monthfial=month+1;
            dayfinal=day;
            Calendar c=Calendar.getInstance();
            hour=c.get(Calendar.HOUR_OF_DAY);
            minute=c.get(Calendar.MINUTE);
            TimePickerDialog timePickerDialog=new TimePickerDialog(RandevuAlDegerlendir.this,RandevuAlDegerlendir.this,hour,minute, DateFormat.is24HourFormat(this));
            timePickerDialog.show();


    }


    @Override
    public void onTimeSet(TimePicker timePicker, int i, int i1) {
            hourfinal=i;
            minutfinal=i1;

            //tvShowone.setText(  "Tarih "+dayfinal+" / "+ monthfial +" / "+yearfinal+" --- "+"SAAT:  "+hourfinal+":"+ minutfinal );
            AppointmentModel appointment=new AppointmentModel();
            appointment.setTarih(  dayfinal+" / "+ monthfial + " / "+ yearfinal +" --- "+ "SAAT:  "+hourfinal +":"+ minutfinal);
            appointment.setMusteriIsmi(userName);
            appointment.setUidMusteri(uid);
            appointment.setUidBerber(berUid);
            appointment.setBerberIsmi(berName);
           Intent intent=new Intent (RandevuAlDegerlendir.this,BerBer_Musterti_Secmek.class);
           intent.putExtra("musteriIsmi",appointment.getMusteriIsmi());
           intent.putExtra("kuaforiIsmi",appointment.getBerberIsmi());
          // tvTarih.setText(appointment.getTarih());
          // tvKuaforunIsmi.setText(appointment.getBerberIsmi());
           MyRef10.push().setValue(appointment);


    }
}
