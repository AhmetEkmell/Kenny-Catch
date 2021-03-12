package com.ahmetekmell.basicgame;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Random;

public class ActivityKuraCek extends AppCompatActivity {

    private TextView siralananlar;
    private TextView onePlayerText;
    private TextView oyuncuSiralamasi;
    private ImageView solImage, sagImage;
    private Random solZarRnd, sagZarRnd;
    private int SolzarDegeri , SagzarDegeri;

    HashMap<Integer,EklenenlerListesi> hashList;
    HashMap<Integer,EklenenlerListesi> newHashList;


    Button oyunaBaslaButonu;
    Button zarAtButonu;
    public static int siralilar[];
    int dizi_sayaci = 0;
    String eklenenleriGoster = "";
    int toplam = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kura_cek);

        hashList = EklenenlerListesi.getHashEklenenler();
        newHashList = EklenenlerListesi.getHashEklenenler_2();

        siralananlar = (TextView) findViewById(R.id.textViewZarDegerleri);
        onePlayerText = (TextView) findViewById(R.id.onePlayerTextView);
        onePlayerText.setVisibility(TextView.INVISIBLE);
        oyuncuSiralamasi = (TextView) findViewById(R.id.textViewOyunSiralamasi);
        solImage = (ImageView) findViewById(R.id.ImageLeftDice);
        sagImage = (ImageView) findViewById(R.id.ImageRightDice);
        zarAtButonu = findViewById(R.id.buton_zarAt);
        zarAtButonu.setBackgroundColor(Color.GREEN);
        oyunaBaslaButonu = findViewById(R.id.buton_gameRun);
        oyunaBaslaButonu.setBackgroundColor(Color.RED);
        oyunaBaslaButonu.setEnabled(false);

        if(hashList.size() == 1){
            EklenenlerListesi oyuncu = hashList.get(CustomAdapter.positions.get(0));
            newHashList.put(0, new EklenenlerListesi(oyuncu.getName(),oyuncu.getResim()));
            zarAtButonu.setBackgroundColor(Color.RED);
            zarAtButonu.setEnabled(false);
            onePlayerText.setVisibility(TextView.VISIBLE);
            siralananlar.setVisibility(TextView.INVISIBLE);
            oyuncuSiralamasi.setVisibility(TextView.INVISIBLE);
            oyunaBaslaButonu.setBackgroundColor(Color.GREEN);
            oyunaBaslaButonu.setEnabled(true);
            onePlayerText.setText("Tek Kişilik Oyun Oynuyorsun "+oyuncu.getName()+" Oyuna Direkt Başlayabilirsin :) İyi Oyunlar :)");
        }

        siralilar = new int[hashList.size()];

        solZarRnd = new Random();
        sagZarRnd = new Random();

    }

    public void zarAtMetodu(View view) throws InterruptedException {
        AlertDialog.Builder alert = new AlertDialog.Builder(ActivityKuraCek.this);
        Boolean AyniSayiVarMi;

        SolzarDegeri = solZarRnd.nextInt(6)+1;
        SagzarDegeri = sagZarRnd.nextInt(6)+1;
        toplam = SolzarDegeri + SagzarDegeri;

        AyniSayiVarMi = isThereNumber(toplam);
      try {
          if(AyniSayiVarMi == false)
          {
              siralilar[dizi_sayaci] = toplam;
              EklenenlerListesi oyuncu = hashList.get(CustomAdapter.positions.get(dizi_sayaci));
              eklenenleriGoster += oyuncu.getName()+" = "+toplam+"\n";
              siralananlar.setText(eklenenleriGoster);
              zarDegeriResimYazdir(SolzarDegeri,SagzarDegeri);
              newHashList.put(toplam, new EklenenlerListesi(oyuncu.getName(),oyuncu.getResim()));
              dizi_sayaci++;
          }
          else if(AyniSayiVarMi == true)
          {
              alert.setMessage("Rastgele Çekilen Zar Değeri = "+toplam+"\nLütfen Tekrar Zar Atar Mısın?\nFarklı Zar Değeri Gelmesi Gerekiyor :)");
              alert.setPositiveButton("TAMAM", new DialogInterface.OnClickListener() {
                  @Override
                  public void onClick(DialogInterface dialog, int which) {
                      //Bir şey yapmasın işte maksat görünüş.
                  }
              });
              alert.show();
          }

          //zar At buton kontrolü
          setEnabledZarAtButon();

      }catch (Exception e){
          System.out.println(e.toString());
      }

    }

    public void setEnabledZarAtButon() {
        int dizi_boyutu = siralilar.length;
        if(siralilar[dizi_boyutu-1] != 0 || siralilar[dizi_boyutu-1] > 0){
            zarAtButonu.setBackgroundColor(Color.RED);
            zarAtButonu.setEnabled(false);
            diziSirala();
            oyunculariSirala();
            oyunaBaslaButonu.setBackgroundColor(Color.GREEN);
            oyunaBaslaButonu.setEnabled(true);
        }
    }


    public void oyunculariSirala(){
        try {
            int n = siralilar.length;
            String siraliOyuncular = "";

            for(int i=0; i<n; i++){
                siraliOyuncular +=  (i+1) +". Oyuncu "+ newHashList.get(siralilar[i]).getName()+"\n" ;
            }

            oyuncuSiralamasi.setText(siraliOyuncular);
        }catch (Exception e){
            System.out.println("Oyuncular Sirala Metodu: "+e.toString());
        }
    }

    public void diziSirala(){
        try {
            int n = siralilar.length;
            for (int i = 0; i < n-1; i++)
                for (int j = 0; j < n-i-1; j++)
                    if (siralilar[j] < siralilar[j+1])
                    {
                        int temp = siralilar[j+1];
                        siralilar[j+1] = siralilar[j];
                        siralilar[j] = temp;
                    }
        }catch (Exception e){
            System.out.println("dizi Sırala metodu: "+e.toString());
        }
    }

    public boolean isThereNumber(int toplam){

        int dizi_boyutu = siralilar.length;

        for(int i = 0; i < dizi_boyutu; i++) {
            if(siralilar[i] == toplam){
                return true;
            }
        }
        return false;
    }

    public void zarDegeriResimYazdir(int solZar, int sagZar){
        switch (solZar){
            case 1:
                solImage.setImageResource(R.drawable.onedice);
                break;
            case 2:
                solImage.setImageResource(R.drawable.twodice);
                break;
            case 3:
                solImage.setImageResource(R.drawable.threedice);
                break;
            case 4:
                solImage.setImageResource(R.drawable.fourdice);
                break;
            case 5:
                solImage.setImageResource(R.drawable.fivedice);
                break;
            case 6:
                solImage.setImageResource(R.drawable.sixdice);
                break;
        }

        switch (sagZar){
            case 1:
                sagImage.setImageResource(R.drawable.onedice);
                break;
            case 2:
                sagImage.setImageResource(R.drawable.twodice);
                break;
            case 3:
                sagImage.setImageResource(R.drawable.threedice);
                break;
            case 4:
                sagImage.setImageResource(R.drawable.fourdice);
                break;
            case 5:
                sagImage.setImageResource(R.drawable.fivedice);
                break;
            case 6:
                sagImage.setImageResource(R.drawable.sixdice);
                break;
        }

    }

    public void GameRun(View view){
        Intent intent = new Intent(ActivityKuraCek.this, ActivityPlayGame.class);
        startActivity(intent);
    }



}
