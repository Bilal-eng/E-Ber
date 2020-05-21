package com.example.e_ber;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.vicmikhailau.maskededittext.MaskedEditText;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {
public Button btnGonder;
public EditText etTelefon,etIsim;

private static final String SHARED_PREF_NAME = "username";
FirebaseDatabase database1 = FirebaseDatabase.getInstance();
DatabaseReference myref6 = database1.getReference("USERS");



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_in_page);
        btnGonder=findViewById(R.id.btnGonder);
        etIsim=findViewById(R.id.etIsim);
        etTelefon=findViewById(R.id.etTelefonGiriniz);

final KullanciInfoModel user=new KullanciInfoModel();
Random r=new Random();
int i=(r.nextInt(1000000));
Log.e("mutu", i + "");

//btnGonder.setOnClickListener(new View.OnClickListener() {
//    @Override
//    public void onClick(View view) {
//        Intent intent = new Intent (MainActivity.this,OnayActivity.class);
//        intent.putExtra("phone Number",  etTelefon.getText().toString());
//        startActivity(intent);
//    }
//});


        btnGonder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String myTelephoneNumber = etTelefon.getText().toString().trim().replace("(","").replace(")","").replace(" ","");

                Toast.makeText(MainActivity.this, myTelephoneNumber, Toast.LENGTH_LONG).show();

//                SharedPreferences sharedPreferences=getSharedPreferences("mydata", Context.MODE_PRIVATE);
//                SharedPreferences.Editor editor=sharedPreferences .edit();
//                editor.putString("name",etIsim.getText().toString());
//                editor.commit();
//
//
//                user.setName(etIsim.getText().toString());
//                user.setID(etTelefon.getText().toString());
//                String userName = etIsim.getText().toString();
//                String telephoneNumber = etTelefon.getText().toString();
//                if(TextUtils.isEmpty(userName) || TextUtils.isEmpty(telephoneNumber)){
//                    myref6.push().setValue(user);
//                }
//                else
//                {
//                    etTelefon.setText("numara girin");
//                    etTelefon.setText("telefon girin");
//                }
//
//                if(telephoneNumber.isEmpty()){
//                    etTelefon.setHint("numra girin ");
//                    etTelefon.requestFocus();
//                    return;
//                }
                Intent intent =new Intent(MainActivity.this,OnayActivity.class);
//                intent.putExtra("phone Number",telephoneNumber);
                startActivity(intent);

            }
        });

    }



}
