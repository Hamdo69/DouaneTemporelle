package com.example.douanetemporelle;

import android.os.Bundle;
import android.content.Intent;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import android.widget.Button;
public class VerificationActivity extends AppCompatActivity {

    private TextView txtNom;
    private TextView txtAge;
    private TextView txtMotif;
    private Button btnApprouver;
    private Button btnRefuser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_verification);

        txtNom = findViewById(R.id.txt_nom_value_kh);
        txtAge = findViewById(R.id.txt_age_value_kh);
        txtMotif = findViewById(R.id.txt_motif_value_kh);

        btnApprouver = findViewById(R.id.btn_approuver_kh);
        btnRefuser = findViewById(R.id.btn_refuser_kh);

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

        //hne n3aytou llfonction isPrime() o nzydou nchoufou ely el motif hwe el fas3a llmadhy o tabdilou edhe ken zouz 7ejet hedhom t7a9a9ou donc na3mlou disabled ll button approuver b setEnable()
        if (motif.equals("Modification du passé") && isPrime(age)) {
            btnApprouver.setEnabled(false);
        }

        btnApprouver.setOnClickListener(v -> {
            envoyerResultat(true, nom);
        });

        btnRefuser.setOnClickListener(v -> {
            envoyerResultat(false, nom);
        });
    }

    //hne el fonction isPrime
    private boolean isPrime(int n) {

        if (n <= 1) {
            return false;
        }

        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) {
                return false;
            }
        }

        return true;
    }

    //el envoyerResultat() bch te5ou zouz parameters el verdict o hwe e decision t3 el approuver (true wala false) o te5ou e nom t3 e traveller
    private void envoyerResultat(boolean verdict, String nom) {

        //hne bch ta3mel intent jdid l ResultActivity
        Intent intent = new Intent(
                VerificationActivity.this,
                ResultActivity.class
        );

        //o bputExtra() bch tab3eth el 2 valeurs el verdict o el clé
        intent.putExtra(
                Intentkeys.getKey("7011", "VERDICT"),
                verdict
        );

        intent.putExtra(
                Intentkeys.getKey("7011", "NOM_VOYAGEUR"),
                nom
        );

        startActivity(intent);
    }
}