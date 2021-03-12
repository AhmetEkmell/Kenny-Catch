package com.ahmetekmell.basicgame;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    Boolean kontrol0=true,kontrol1=true,kontrol2=true,kontrol3=true,kontrol4=true,kontrol5=true;
    HashMap<Integer,EklenenlerListesi> hashList;
    public static ArrayList<Integer> positions;
    ArrayList<Veriler> verilerList;
    LayoutInflater layoutInflater;
    Context context;

    public CustomAdapter(ArrayList<Veriler> kisiler, Context context) {
        verilerList = new ArrayList<>();
        verilerList = kisiler;
        this.context = context;
        hashList = EklenenlerListesi.getHashEklenenler();
        positions = new ArrayList<>();
        ActivityGiris.devamEt.setBackgroundColor(Color.RED);
    }

    // Her bir satır için temsil edilecek arayüz seçilir.
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.row_list,parent,false);
        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.txtName.setText(verilerList.get(position).getIsim());
        holder.imgView.setImageResource(verilerList.get(position).getImgSource());
        holder.linearLayout.setTag(holder);
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyViewHolder viewHolder = (MyViewHolder)v.getTag();
                int position2 = viewHolder.getAdapterPosition();
                listviewBackgroundColor(v,position2);
                if(hashList.isEmpty()){
                    ActivityGiris.devamEt.setBackgroundColor(Color.RED);
                    ActivityGiris.devamEt.setEnabled(false);
                }
                else{
                    ActivityGiris.devamEt.setBackgroundColor(Color.GREEN);
                    ActivityGiris.devamEt.setEnabled(true);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return verilerList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        TextView txtName;
        ImageView imgView;
        LinearLayout linearLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.kisiIsmiID);
            imgView = itemView.findViewById(R.id.resimID);
            linearLayout = itemView.findViewById(R.id.linearLayoutID);
        }
    }

    public void listviewBackgroundColor(View view, int position){

        String oyuncu = verilerList.get(position).getIsim();

        switch (position){
            case 0:
                    if(kontrol0){
                        view.setBackgroundColor(Color.GREEN);
                        hashList.put(position, new EklenenlerListesi(oyuncu, ActivityGiris.person1));
                        positions.add(position);
                        kontrol0 = false;
                    }
                    else if(kontrol0 == false){
                        view.setBackgroundColor(Color.RED);
                        hashList.remove(position);
                        positions.remove(Integer.valueOf(position));
                        kontrol0 = true;
                    }
            case 1:
                    if(kontrol1){
                        view.setBackgroundColor(Color.GREEN);
                        hashList.put(position, new EklenenlerListesi(oyuncu, ActivityGiris.person2));
                        positions.add(position);
                        kontrol1 = false;
                    }
                    else if(kontrol1 == false){
                        view.setBackgroundColor(Color.RED);
                        hashList.remove(position);
                        positions.remove(Integer.valueOf(position));
                        kontrol1 = true;
                    }
                break;
            case 2:
                    if(kontrol2){
                        view.setBackgroundColor(Color.GREEN);
                        hashList.put(position, new EklenenlerListesi(oyuncu, ActivityGiris.person3));
                        positions.add(position);
                        kontrol2 = false;
                    }
                    else if(kontrol2 == false){
                        view.setBackgroundColor(Color.RED);
                        hashList.remove(position);
                        positions.remove(Integer.valueOf(position));
                        kontrol2 = true;
                    }
                break;
            case 3:
                    if(kontrol3){
                        view.setBackgroundColor(Color.GREEN);
                        hashList.put(position, new EklenenlerListesi(oyuncu, ActivityGiris.person4));
                        positions.add(position);
                        kontrol3 = false;
                    }
                    else if(kontrol3 == false){
                        view.setBackgroundColor(Color.RED);
                        hashList.remove(position);
                        positions.remove(Integer.valueOf(position));
                        kontrol3 = true;
                    }
                break;
            case 4:
                    if(kontrol4){
                        view.setBackgroundColor(Color.GREEN);
                        hashList.put(position, new EklenenlerListesi(oyuncu, ActivityGiris.person5));
                        positions.add(position);
                        kontrol4 = false;
                    }
                    else if(kontrol4 == false){
                        view.setBackgroundColor(Color.RED);
                        hashList.remove(position);
                        positions.remove(Integer.valueOf(position));
                        kontrol4 = true;
                    }
                break;
            case 5:
                    if(kontrol5){
                        view.setBackgroundColor(Color.GREEN);
                        hashList.put(position, new EklenenlerListesi(oyuncu, ActivityGiris.person6));
                        positions.add(position);
                        kontrol5 = false;
                    }
                    else if(kontrol5 == false){
                        view.setBackgroundColor(Color.RED);
                        hashList.remove(position);
                        positions.remove(Integer.valueOf(position));
                        kontrol5 = true;
                    }
                break;
            default:
                break;
        }

    }
}
