package com.example.e_ber;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class OnayActivity extends AppCompatActivity {

    private boolean mVerificationInProgress = false;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;
    private String verificationId;
    private FirebaseAuth mAuth;
    private EditText etCodeone;
    private Button btnSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onay);

        mAuth=FirebaseAuth.getInstance();
        etCodeone=findViewById(R.id.etCodeone);
        btnSend=findViewById(R.id.btnSend);
       String phonenumber=getIntent().getStringExtra("phone Number");
       sendVerificationCode(phonenumber);
       findViewById(R.id.btnSend).setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               String code=etCodeone.toString().trim();
               verifyCode(code);

           }
       });

    }

private void verifyCode(String code){

        PhoneAuthCredential credential= PhoneAuthProvider.getCredential(verificationId,code);
        signInWithCredantial(credential);
}

    private void signInWithCredantial(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Intent intent=new Intent (OnayActivity.this, ErkekKadinActivity.class);
                    //intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

                    startActivity(intent);

                } else {
                    Toast.makeText(OnayActivity.this,task.getException().getMessage(),Toast.LENGTH_LONG).show();
                }
            }
        });

    }


    private  void sendVerificationCode (String number){
        PhoneAuthProvider.getInstance().verifyPhoneNumber(number,60,TimeUnit.SECONDS, TaskExecutors.MAIN_THREAD,mCallback);
}

private PhoneAuthProvider.OnVerificationStateChangedCallbacks
        mCallback =new PhoneAuthProvider.OnVerificationStateChangedCallbacks()
{
    @Override
    public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
        super.onCodeSent(s, forceResendingToken);
        verificationId=s;
    }

    @Override
    public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
    String code=phoneAuthCredential.getSmsCode();
    if(code!=null){
        verifyCode(code);
    }
    }

    @Override
    public void onVerificationFailed(@NonNull FirebaseException e) {
        Toast.makeText(OnayActivity.this,e.getMessage(),Toast.LENGTH_LONG).show();
    }
};
}
