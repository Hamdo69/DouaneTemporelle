package com.example.douanetemporelle;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Button;
import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;

public class ResultActivity extends AppCompatActivity {

    private LinearLayout layoutResult; //hedhy t3 el controle t3 el couleur t3 el background
    private TextView txtMessage; //hedhy t3 el message t3 el mouef9a wala bch ytchd
    private Button btnNouveauVoyageur; //hedhe kif me 9olna el button t3 return lldebut t3 el app

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WindowCompat.enableEdgeToEdge(getWindow());
        setContentView(R.layout.activity_result);

        layoutResult = findViewById(R.id.layout_result_kh);
        txtMessage = findViewById(R.id.txt_result_message_kh);
        btnNouveauVoyageur = findViewById(R.id.btn_nouveau_voyageur_kh);

        // bhedhy bch naa3mlou recuperation lldonnees mn el activité louel
        Intent intent = getIntent();

        //el getBooleanExtra() bch ne5dhou bihe e decision eme true wala false
        boolean verdict = intent.getBooleanExtra(
                Intentkeys.getKey("7011", "VERDICT"), false);

        //el getStringExtra() bch ne5dhou bihe esm el traveller
        String nom = intent.getStringExtra(
                Intentkeys.getKey("7011", "NOM_VOYAGEUR"));
                //el Intentkeys.getKey() just bch nab9ou nsta3mlou el cles ely 3mlnehom mbkry

        if (verdict) {

            layoutResult.setBackgroundColor(Color.GREEN);
            txtMessage.setText("Bon voyage " + nom);

        } else {

            layoutResult.setBackgroundColor(Color.RED);
            txtMessage.setText(nom + " est en état d'arrestation temporelle");

            //lhne est3mlne el layoutResult khater n7bou el couleur t3 el contenera el principal mch rl couleur t3 text

        }

        btnNouveauVoyageur.setOnClickListener(v -> {

            Intent intentRetour = new Intent(
                    ResultActivity.this,
                    MainActivity.class
            );

            //FLAG_ACTIVITY_CLEAR_TOP hedhy l9itha fy StackOverFlow 9alek e sidi taamel destroy l2ay instance fyl back stack
            intentRetour.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            startActivity(intentRetour);
            finish();
            //bl finish() bch nsakrou el activité t3 el ResultActivity
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.layout_result_kh), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}