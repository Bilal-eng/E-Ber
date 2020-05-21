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

import static android.content.Context.MODE_PRIVATE;

public class YorumAdapeter  extends ArrayAdapter<BerYorum> {
    Context mContext;
public static final String Default="N/S";
    private static class ViewHolder{
        TextView tvIsim,tvYorum;

    }
    public YorumAdapeter (ArrayList<BerYorum> data, Context context){
      super(context, R.layout.isimyorum, data);
      this.mContext=context;
    }
    @Override
    public View getView(int position , View convertView, ViewGroup parent){
    //final String item = getItem(position);
    final BerYorum item1 = getItem(position);

    final ViewHolder viewHolder=new ViewHolder();
        LayoutInflater inflater=LayoutInflater.from(getContext());
        convertView = inflater.inflate(R.layout.isimyorum,parent,false);
        viewHolder.tvIsim=convertView.findViewById(R.id.tvIsim);
        viewHolder.tvYorum=convertView.findViewById(R.id.tvYorum);
        viewHolder.tvYorum.setText(item1.getYorum());

        viewHolder.tvIsim.setText(item1.getUserName());





        return convertView;
    }

}

