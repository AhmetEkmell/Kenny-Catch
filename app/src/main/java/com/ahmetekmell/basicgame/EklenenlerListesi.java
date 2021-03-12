package com.ahmetekmell.basicgame;

import android.graphics.Bitmap;
import java.util.HashMap;

class sayac{
    public static int GetSayac = 0;
}

public class EklenenlerListesi {

    private String isim;
    private Bitmap resim;
    private static HashMap<Integer,EklenenlerListesi> hashEklenenler;
    private static HashMap<Integer,EklenenlerListesi> hashEklenenler_2;

    public static HashMap<Integer,EklenenlerListesi> getHashEklenenler(){
        if(hashEklenenler == null){
            hashEklenenler = new HashMap<>();
        }
        return hashEklenenler;
    }

    public static HashMap<Integer,EklenenlerListesi> getHashEklenenler_2(){
        if(hashEklenenler_2 == null){
            hashEklenenler_2 = new HashMap<>();
        }
        return hashEklenenler_2;
    }

    public EklenenlerListesi(String isim, Bitmap resim){
        this.isim = isim;
        this.resim = resim;
    }

    public String getName(){
        return isim;
    }

    public Bitmap getResim(){
        return resim;
    }




}
