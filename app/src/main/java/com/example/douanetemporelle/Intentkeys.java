package com.example.douanetemporelle;

public class Intentkeys {
//hedhy el fonction e static ely traj3elne el key el traveller mnghir me nst7a9ou na3mlou object mn IntentKey
    public static String getKey(String matricule, String nomCle) {
        return matricule + "_" + nomCle;
    }
}