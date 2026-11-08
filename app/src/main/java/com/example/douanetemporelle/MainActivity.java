
package com.example.douanetemporelle;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    // Les composants de l'interface
    private EditText edtNom;
    private EditText edtNaissance;
    private EditText edtDestination;

    private RadioGroup radioMotif;
    private Button btnInspecter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        edtNom = findViewById(R.id.edt_nom_kh);
        edtNaissance = findViewById(R.id.edt_naissance_kh);
        edtDestination = findViewById(R.id.edt_destination_kh);

        radioMotif = findViewById(R.id.radio_motif_kh);
        btnInspecter = findViewById(R.id.btn_inspecter_kh);

        btnInspecter.setOnClickListener(v -> {

            String nom = edtNom.getText().toString().trim();
            String naissanceStr = edtNaissance.getText().toString().trim();
            String destinationStr = edtDestination.getText().toString().trim();

            // 2. Vérification des champs vides
            if (nom.isEmpty() || naissanceStr.isEmpty() || destinationStr.isEmpty()) {
                Toast.makeText(this,
                        "Veuillez remplir tous les champs",
                        Toast.LENGTH_SHORT).show();
                return;
            }

            int selectedId = radioMotif.getCheckedRadioButtonId(); //bech traja3 el ID t3 el button ely 5tarou el traveller

            if (selectedId == -1) { //hne law fardhna enou 3ml inspecter mnghir me y5tar 7ata sbb
                Toast.makeText(this,
                        "Veuillez choisir un motif de voyage",
                        Toast.LENGTH_SHORT).show();
                return;
            }

            RadioButton selectedRadio = findViewById(selectedId); //bhedhy najmou nouslou ll button ely e5tarou
            String motif = selectedRadio.getText().toString(); // w bgetText() bch na9rou chnwe hwe el e5tyar

            try { //btbi3e lesmne nst3mlou try/catch bch ysyrch tomb llapp edhe ken ra9em ely l9ineh b3d me 3mlne parseInt invalide wala 7aja
                int naissance = Integer.parseInt(naissanceStr);
                int destination = Integer.parseInt(destinationStr);

                if (destination <= naissance) {
                    Toast.makeText(this,
                            "L'année de destination doit être après la naissance",
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                // Les données sont valides
                Toast.makeText(this,
                        "Profil valide : " + nom + " - " + motif,
                        Toast.LENGTH_LONG).show();

            } catch (NumberFormatException e) {
                Toast.makeText(this,
                        "Veuillez saisir des années valides",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
}
