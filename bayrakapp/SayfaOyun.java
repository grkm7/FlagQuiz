package com.info.bayrakapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashSet;

public class SayfaOyun extends AppCompatActivity {
    private TextView textViewDogru,textViewYanlis,textViewSoruSayisi;
    private ImageView imageView;
    private Button buttonA,buttonB,buttonC,buttonD;
    private int hak=5;
    private ArrayList<Bayraklar> sorularListe;
    private ArrayList<Bayraklar> yanlisSeceneklerListe;
    private Bayraklar dogruSoru;
    private Veritabani vt = new Veritabani(this);
    private int soruSayac=0;
    private int dogruSayac=0;
    private int yanlisSayac=0;
    private HashSet<Bayraklar> secenekleriKaristirmaListe = new HashSet<>();
    private ArrayList<Bayraklar> seceneklerListe = new ArrayList<>();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sayfa_oyun);

        textViewDogru = findViewById(R.id.textViewDogru);
        textViewYanlis = findViewById(R.id.textViewYanlis);
        textViewSoruSayisi = findViewById(R.id.textViewSoruSayisi);
        imageView = findViewById(R.id.imageView);
        buttonA = findViewById(R.id.buttonA);
        buttonB = findViewById(R.id.buttonB);
        buttonC = findViewById(R.id.buttonC);
        buttonD = findViewById(R.id.buttonD);

        sorularListe= new BayraklarDAO().rasgele5Getir(vt);
        soruYukle();


        buttonA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dogruKontrol(buttonA);
                sayacKontrol();


            }
        });

        buttonB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dogruKontrol(buttonB);
                sayacKontrol();

            }
        });

        buttonC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dogruKontrol(buttonC);
                sayacKontrol();

            }
        });

        buttonD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dogruKontrol(buttonD);
                sayacKontrol();

            }
        });



    }

    public void soruYukle(){

        textViewSoruSayisi.setText(soruSayac+". Soru");
        textViewDogru.setText("Doğru : "+dogruSayac);
        textViewYanlis.setText("Yanlış : "+yanlisSayac);


        dogruSoru = sorularListe.get(soruSayac);
        yanlisSeceneklerListe= new BayraklarDAO().rasgele3YanlisSecenekGetir(vt,dogruSoru.getBayrak_id());

        imageView.setImageResource(getResources().getIdentifier(dogruSoru.getBayrak_resim(),"drawable",getPackageName()));

        secenekleriKaristirmaListe.clear();
        secenekleriKaristirmaListe.add(dogruSoru);
        secenekleriKaristirmaListe.add(yanlisSeceneklerListe.get(0));
        secenekleriKaristirmaListe.add(yanlisSeceneklerListe.get(1));
        secenekleriKaristirmaListe.add(yanlisSeceneklerListe.get(2));

        seceneklerListe.clear();

        for(Bayraklar b: secenekleriKaristirmaListe){
            seceneklerListe.add(b);
        }

        buttonA.setText(seceneklerListe.get(0).getBayrak_ad());
        buttonB.setText(seceneklerListe.get(1).getBayrak_ad());
        buttonC.setText(seceneklerListe.get(2).getBayrak_ad());
        buttonD.setText(seceneklerListe.get(3).getBayrak_ad());





    }

    public void dogruKontrol(Button button){
        String buttonyazi = button.getText().toString();
        String dogruCevap = dogruSoru.getBayrak_ad();

        if(buttonyazi.equals(dogruCevap)){
            dogruSayac++;
        }else{
            yanlisSayac++;
        }


    }

    public void sayacKontrol(){
        soruSayac++;
        if(soruSayac!=5){
            soruYukle();

        }else{
            Intent intent = new Intent(SayfaOyun.this,SayfaSonuc.class);
            intent.putExtra("dogrusayac",dogruSayac);
            startActivity(intent);
            finish();

        }
    }
}