package com.example.e_ber;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class Myadaptert extends ArrayAdapter<BerberModel> {
Context mContext;
  //FirebaseDatabase database = FirebaseDatabase.getInstance();

    DatabaseReference data = FirebaseDatabase.getInstance().getReference();
    DatabaseReference userNameRef;



private static class ViewHolder{
    TextView tvBerIsmi,tvAdres;
    RatingBar ratingBar;
    TextView tvBalnce;
    Button btnRandevuIste,btnOnay;
    ListView lvBer;
    LinearLayout llItem;
}
public Myadaptert(List<BerberModel> data, Context context)
{
    super (context,R.layout.berberbilgisione,data);
    this.mContext=context;
}
@Override
public View getView(int position , View convertView, ViewGroup parent){

    final BerberModel item = getItem(position);
    final ViewHolder viewHolder=new ViewHolder();
    LayoutInflater inflater = LayoutInflater.from(getContext());
    convertView = inflater.inflate(R.layout.berberbilgisione,parent,false);
    viewHolder.tvBerIsmi = convertView.findViewById(R.id.tvBerIsmi);
    viewHolder.btnRandevuIste = convertView.findViewById(R.id.btnRandevuIste);
    viewHolder.lvBer = convertView.findViewById(R.id.lvBer);
    viewHolder.ratingBar = convertView.findViewById(R.id.ratingBar);
    viewHolder.llItem = convertView.findViewById(R.id.llItem);
    viewHolder.tvAdres = convertView.findViewById(R.id.tvAdres);



    viewHolder.tvBerIsmi.setText(item.getStoreName());
    viewHolder.tvAdres.setText(item.getMah().getMahalle_title());
    viewHolder.ratingBar.setRating(item.getRanking());
    viewHolder.llItem.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent (mContext, RandevuAlDegerlendir.class);
            intent.putExtra("BerName", item.getStoreName());
            intent.putExtra("BerUID", item.getUd());
            mContext.startActivity(intent);

        }
    });
    return convertView;

   }

}
