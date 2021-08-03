package com.info.bayrakapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SayfaSonuc extends AppCompatActivity {
    private TextView textViewSonuc,textViewBasari;
    private Button buttonTekrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sayfa_sonuc);

        textViewBasari = findViewById(R.id.textViewBasari);
        textViewSonuc = findViewById(R.id.textViewSonuc);
        buttonTekrar = findViewById(R.id.buttonTekrar);

        buttonTekrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SayfaSonuc.this,SayfaOyun.class));
                finish();
            }
        });

    }
}