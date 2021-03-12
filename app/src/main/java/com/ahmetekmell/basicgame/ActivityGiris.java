package com.ahmetekmell.basicgame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class ActivityGiris extends AppCompatActivity{

    RecyclerView recyclerView;
    Context context = this;
    ArrayList<Veriler> kisiler = new ArrayList<>();

    public static Button devamEt;

    public static Bitmap person1;
    public static Bitmap person2;
    public static Bitmap person3;
    public static Bitmap person4;
    public static Bitmap person5;
    public static Bitmap person6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giris);

        recyclerView = findViewById(R.id.recyclerViewID);
        devamEt = (Button) findViewById(R.id.butonDevamke);
        devamEt.setEnabled(false);

        //Resimler
       /*

        person1 = BitmapFactory.decodeResource(getApplicationContext().getResources(),R.drawable.person1);
        person2 = BitmapFactory.decodeResource(getApplicationContext().getResources(),R.drawable.person2);
        person3 = BitmapFactory.decodeResource(getApplicationContext().getResources(),R.drawable.person3);
        person4 = BitmapFactory.decodeResource(getApplicationContext().getResources(),R.drawable.person4);
        person5 = BitmapFactory.decodeResource(getApplicationContext().getResources(),R.drawable.person5);
        person6 = BitmapFactory.decodeResource(getApplicationContext().getResources(),R.drawable.person6);

        kisiler.add(new Veriler("person1",R.drawable.person1));
        kisiler.add(new Veriler("person2",R.drawable.person2));
        kisiler.add(new Veriler("person3",R.drawable.person3));
        kisiler.add(new Veriler("person4",R.drawable.person4));
        kisiler.add(new Veriler("person5",R.drawable.person5));
        kisiler.add(new Veriler("person6",R.drawable.person6));

      */

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        linearLayoutManager.scrollToPosition(0);

        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);

        CustomAdapter customAdapter = new CustomAdapter(kisiler,context);
        recyclerView.setAdapter(customAdapter);

    }

    public void butonDevamEt(View view){
        Intent intent = new Intent(this, ActivityKuraCek.class);
        startActivity(intent);
    }
}
