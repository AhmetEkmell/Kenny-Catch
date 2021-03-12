package com.ahmetekmell.basicgame;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Random;

public class ActivityPlayGame extends AppCompatActivity {

    ImageView imageView1, imageView2, imageView3, imageView4, imageView5, imageView6, imageView7, imageView8, imageView9;
    HashMap<Integer,EklenenlerListesi> hashList;
    HashMap<Integer,EklenenlerListesi> newHashList;
    Button oyunuBaslatButonu;
    ImageView imageArray[];
    TextView oyuncuText;
    TextView scoreText;
    TextView timeText;
    Runnable runnable;
    Handler handler;
    int score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        score = 0;
        hashList = EklenenlerListesi.getHashEklenenler();
        newHashList = EklenenlerListesi.getHashEklenenler_2();
        timeText = (TextView) findViewById(R.id.timetext);
        oyuncuText = findViewById(R.id.oyuncuTextView);

        if(newHashList.size() == 1){
            oyuncuText.setText(newHashList.get(0).getName());
        }
        else{
            oyuncuText.setText(newHashList.get(ActivityKuraCek.siralilar[sayac.GetSayac]).getName());
        }

        scoreText = (TextView) findViewById(R.id.puanText);
        oyunuBaslatButonu = (Button) findViewById(R.id.buton_baslat);
        oyunuBaslatButonu.setBackgroundColor(Color.GREEN);
        oyunuBaslatButonu.setEnabled(true);

        imageView1 = findViewById(R.id.imageView1);
        imageView2 = findViewById(R.id.imageView2);
        imageView3 = findViewById(R.id.imageView3);
        imageView4 = findViewById(R.id.imageView4);
        imageView5 = findViewById(R.id.imageView5);
        imageView6 = findViewById(R.id.imageView6);
        imageView7 = findViewById(R.id.imageView7);
        imageView8 = findViewById(R.id.imageView8);
        imageView9 = findViewById(R.id.imageView9);

        if(newHashList.size() == 1){
            imageView1.setImageBitmap(newHashList.get(0).getResim());
            imageView2.setImageBitmap(newHashList.get(0).getResim());
            imageView3.setImageBitmap(newHashList.get(0).getResim());
            imageView4.setImageBitmap(newHashList.get(0).getResim());
            imageView5.setImageBitmap(newHashList.get(0).getResim());
            imageView6.setImageBitmap(newHashList.get(0).getResim());
            imageView7.setImageBitmap(newHashList.get(0).getResim());
            imageView8.setImageBitmap(newHashList.get(0).getResim());
            imageView9.setImageBitmap(newHashList.get(0).getResim());
        }
        else{
            imageView1.setImageBitmap(newHashList.get(ActivityKuraCek.siralilar[sayac.GetSayac]).getResim());
            imageView2.setImageBitmap(newHashList.get(ActivityKuraCek.siralilar[sayac.GetSayac]).getResim());
            imageView3.setImageBitmap(newHashList.get(ActivityKuraCek.siralilar[sayac.GetSayac]).getResim());
            imageView4.setImageBitmap(newHashList.get(ActivityKuraCek.siralilar[sayac.GetSayac]).getResim());
            imageView5.setImageBitmap(newHashList.get(ActivityKuraCek.siralilar[sayac.GetSayac]).getResim());
            imageView6.setImageBitmap(newHashList.get(ActivityKuraCek.siralilar[sayac.GetSayac]).getResim());
            imageView7.setImageBitmap(newHashList.get(ActivityKuraCek.siralilar[sayac.GetSayac]).getResim());
            imageView8.setImageBitmap(newHashList.get(ActivityKuraCek.siralilar[sayac.GetSayac]).getResim());
            imageView9.setImageBitmap(newHashList.get(ActivityKuraCek.siralilar[sayac.GetSayac]).getResim());
        }

        imageArray = new ImageView[] {
                imageView1,imageView2,imageView3,
                imageView4,imageView5,imageView6,
                imageView7,imageView8,imageView9
        };

        for (ImageView image: imageArray){
            image.setVisibility(View.INVISIBLE);
        }
    }

    public void oyunuBaslat(View view){
        oyunuBaslatButonu.setBackgroundColor(Color.RED);
        oyunuBaslatButonu.setEnabled(false);
        resimleriSakla();
        new CountDownTimer(10000,1000){

            @Override
            public void onTick(long millisUntilFinished) {
                oyunuBaslatButonu.setBackgroundColor(Color.RED);
                oyunuBaslatButonu.setEnabled(false);
                timeText.setText("Kalan Süre: "+millisUntilFinished/1000);
            }

            @Override
            public void onFinish() {
                oyunuBaslatButonu.setBackgroundColor(Color.GREEN);
                oyunuBaslatButonu.setEnabled(true);
                score = 0;
                timeText.setText("SÜREN BİTTİ :) :) ");
                handler.removeCallbacks(runnable);
                for (ImageView image: imageArray){
                    image.setVisibility(View.INVISIBLE);
                }

                if(ActivityKuraCek.siralilar.length == 1)
                {
                    AlertDialog.Builder alert = new AlertDialog.Builder(ActivityPlayGame.this);
                    alert.setTitle("Oyun Bitti!");
                    alert.setMessage("Oyunun Bitti "+newHashList.get(ActivityKuraCek.siralilar[0]).getName()+"\nTekrar Oynamak İstiyor musun?");
                    alert.setPositiveButton("EVET", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //restart
                            Intent intent = getIntent();
                            finish(); // Güncel aktiviteyi bitirecek aynı aktiviteyi başlatacak.
                            startActivity(intent);
                        }
                    });

                alert.setNegativeButton("HAYIR", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        hashList.clear();
                        newHashList.clear();
                        CustomAdapter.positions.clear();
                        Intent intent = new Intent(ActivityPlayGame.this, ActivityGiris.class);
                        startActivity(intent);
                    }
                });

                alert.show();
                }
                else if(ActivityKuraCek.siralilar.length > 1)
                {
                   if(sayac.GetSayac != ActivityKuraCek.siralilar.length-1){
                       AlertDialog.Builder alert3 = new AlertDialog.Builder(ActivityPlayGame.this);
                       alert3.setTitle("Oyun Bitti!");
                       alert3.setMessage("Oyunun Bitti "+newHashList.get(ActivityKuraCek.siralilar[sayac.GetSayac]).getName()+"\nSıradaki Oyuncu: "+newHashList.get(ActivityKuraCek.siralilar[sayac.GetSayac+1]).getName());
                       alert3.setPositiveButton("Sıradaki Oyuncu", new DialogInterface.OnClickListener() {
                           @Override
                           public void onClick(DialogInterface dialog, int which) {
                               sayac.GetSayac++;
                               //restart
                               Intent intent = getIntent();
                               finish(); // Güncel aktiviteyi bitirecek aynı aktiviteyi başlatacak.
                               startActivity(intent);
                           }
                       });
                       alert3.show();
                   }
                   else if(sayac.GetSayac == ActivityKuraCek.siralilar.length-1){
                        final AlertDialog.Builder alert2 = new AlertDialog.Builder(ActivityPlayGame.this);
                        alert2.setTitle("Oyun Bitti!");
                        alert2.setMessage("Oyunun Bitti "+newHashList.get(ActivityKuraCek.siralilar[sayac.GetSayac]).getName()+"\nTekrar Oynamak İstiyor Musunuz?");
                        alert2.setPositiveButton("EVET", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                hashList.clear();
                                newHashList.clear();
                                CustomAdapter.positions.clear();
                                Intent intent = new Intent(ActivityPlayGame.this, ActivityGiris.class);
                                startActivity(intent);
                            }
                        });

                        alert2.show();
                   }
                }
            }
        }.start();
    }

    public void increaseScore(View view){
        score++;
        scoreText.setText("Puan: "+score);
    }

    public void resimleriSakla(){
        handler = new Handler();
        if(oyunuBaslatButonu.isEnabled() != true){
            runnable = new Runnable() {
                @Override
                public void run() {
                    for (ImageView image: imageArray){
                        image.setVisibility(View.INVISIBLE);
                    }
                    Random random = new Random();
                    int i = random.nextInt(9);
                    imageArray[i].setVisibility(View.VISIBLE);
                    handler.postDelayed(this,500);
                }
            };
        }
        handler.post(runnable);
    }
}
