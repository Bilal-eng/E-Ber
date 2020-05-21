package com.example.e_ber;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class AppointmentAdapter extends ArrayAdapter<AppointmentModel> {
    public static final String SHARED_PREF= "SharedPefs";
    private  Boolean durum1;
    Context mContext;

    private static class ViewHolder{
        TextView tvTarih,tvKuaforunIsmi;
        Button  btnBeklemde,btnOnaylma;


    }
    public AppointmentAdapter (ArrayList< AppointmentModel > data, Context context){
        super(context,R.layout.appointment_detalari, data);
        this.mContext=context;
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("SHARED_PREF",Context.MODE_PRIVATE);
        durum1 = sharedPreferences.getBoolean("Durum", false);
    }
    @Override
    public View getView(int position , View convertView, ViewGroup parent){
        //final String item = getItem(position);
        final AppointmentModel item1 = getItem(position);

        final ViewHolder viewHolder=new ViewHolder();
        LayoutInflater inflater=LayoutInflater.from(getContext());
        convertView = inflater.inflate(R.layout.appointment_detalari,parent,false);
        viewHolder.tvTarih=convertView.findViewById(R.id.tvTarih);
        viewHolder.tvKuaforunIsmi=convertView.findViewById(R.id.tvKuaforunIsmi);
        viewHolder.btnOnaylma=convertView.findViewById(R.id.btnOnaylma);
        viewHolder.btnBeklemde=convertView.findViewById(R.id.btnBeklemde);
        if(durum1)
        viewHolder.btnOnaylma.setVisibility(View.INVISIBLE);

        else {
            viewHolder.btnOnaylma.setVisibility(View.GONE);
            viewHolder.btnBeklemde.setVisibility(View.INVISIBLE);
        }

       // viewHolder.tvDurum=convertView.findViewById(R.id.tvDurum);
        viewHolder.tvTarih.setText(item1.getTarih());
        viewHolder.tvKuaforunIsmi.setText(item1.getBerberIsmi());
        return convertView;
    }


}
