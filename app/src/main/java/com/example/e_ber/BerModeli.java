package com.example.e_ber;

import android.widget.Button;
import android.widget.RatingBar;

public class BerModeli {
    private String berismi,adres, ud;
    private float ranking;
    private int UserNumber;

    public int getUserNumber() {
        return UserNumber;
    }

    public void setUserNumber(int userNumber) {
        UserNumber = userNumber;
    }

    public String getBerismi() {
        return berismi;
    }

    public void setBerismi(String berismi) {
        this.berismi = berismi;
    }

    public String getAdres() {
        return adres;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }

    public float getRanking() {
        return ranking;
    }

    public void setRanking(float ranking) {
        this.ranking = ranking;
    }

    public String getUd() {
        return ud;
    }

    public void setUd(String ud) {
        this.ud = ud;
    }
}
