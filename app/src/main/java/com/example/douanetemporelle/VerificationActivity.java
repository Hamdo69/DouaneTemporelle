package com.example.douanetemporelle;

import android.os.Bundle;
import android.content.Intent;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class VerificationActivity extends AppCompatActivity {

    private TextView txtNom;
    private TextView txtAge;
    private TextView txtMotif;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_verification);

        txtNom = findViewById(R.id.txt_nom_value_kh);
        txtAge = findViewById(R.id.txt_age_value_kh);
        txtMotif = findViewById(R.id.txt_motif_value_kh);

        // hne lest9bel t3 el intent
        Intent intent = getIntent();

        //hne el getStringExtra() bch tst9bl el putExtra() t3 el mainActivity
        String nom = intent.getStringExtra(
                Intentkeys.getKey("7011", "NOM_VOYAGEUR"));

        //nafs chy hne el getIntExtra() bch tst9bl el putExtra() t3 el mainActivity just Int khater les dates type Int
        int naissance = intent.getIntExtra(
                Intentkeys.getKey("7011", "ANNEE_NAISSANCE"), 0);

        int destination = intent.getIntExtra(
                Intentkeys.getKey("7011", "ANNEE_DESTINATION"), 0);

        String motif = intent.getStringExtra(
                Intentkeys.getKey("7011", "MOTIF")); //Intentkeys.getKey() st3mlnehe bch ykoun el key fy lest9bel hwe nafsou bdhabet el key fy ely tb3atth

        // hne yt7sb 3mor el traveller fy destination
        int age = destination - naissance;

        // laffichage
        txtNom.setText(nom);
        txtAge.setText(age + " ans");
        txtMotif.setText(motif);
    }
}