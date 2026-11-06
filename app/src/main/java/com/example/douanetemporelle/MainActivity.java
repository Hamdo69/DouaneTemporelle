package com.example.douanetemporelle;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import DouaneTemporelle.cles;

public class MainActivity extends AppCompatActivity {

    // 1.numero l inscription
    public static final String MATRICULE = "1234";

    EditText etNom, etNaissance, etDestination;
    Spinner spinnerMotif;
    Button btnInspecter;
    private cles Cles;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 2. torbet les donnees b interface
        etNom = findViewById(R.id.et_nom_hl);
        etNaissance = findViewById(R.id.et_naissance_hl);
        etDestination = findViewById(R.id.et_destination_hl);
        spinnerMotif = findViewById(R.id.spinner_motif_hl);
        btnInspecter = findViewById(R.id.btn_inspecter_hl);

        // 3.ki tenzel al button
        btnInspecter.setOnClickListener(v -> {
            String nom = etNom.getText().toString().trim();
            String naissanceStr = etNaissance.getText().toString().trim();
            String destinationStr = etDestination.getText().toString().trim();
            String motif = spinnerMotif.getSelectedItem().toString();

            // 4. tetaked eli les champs mch ferghin
            if (nom.isEmpty() || naissanceStr.isEmpty() || destinationStr.isEmpty()) {
                Toast.makeText(this, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show();
                return;
            }

            int naissance = Integer.parseInt(naissanceStr);
            int destination = Integer.parseInt(destinationStr);

            // 5. tetaked eli aawem logique walle
            if (destination <= naissance) {
                Toast.makeText(this, "L'année de destination doit être après la naissance", Toast.LENGTH_SHORT).show();
                return;
            }

            // 6. Intent explicite bech nhelou l’activité thenia
            Intent intent = new Intent(MainActivity.this, VerificationActivity .class);

            // 7. nabathou les donnes b cles
            intent.putExtra(Cles.cle(MATRICULE, "NOM_VOYAGEUR"), nom);
            intent.putExtra(Cles.cle(MATRICULE, "ANNEE_NAISSANCE"), naissance);
            intent.putExtra(Cles.cle(MATRICULE, "ANNEE_DESTINATION"), destination);
            intent.putExtra(Cles.cle(MATRICULE, "MOTIF"), motif);

            startActivity(intent);
        });
    }

    private class VerificationActivity {
    }
}