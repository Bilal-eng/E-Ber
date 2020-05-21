package com.example.e_ber;

public class AppointmentModel {

    String tarih;
    String musteriIsmi;
    String berberIsmi;
    String uidMusteri, uidBerber;
    Boolean appiontmentDurumu;

    public String getTarih() {
        return tarih;
    }

    public void setTarih(String tarih) {
        this.tarih = tarih;
    }

    public String getMusteriIsmi() {
        return musteriIsmi;
    }

    public void setMusteriIsmi(String musteriIsmi) {
        this.musteriIsmi = musteriIsmi;
    }

    public String getBerberIsmi() {
        return berberIsmi;
    }

    public void setBerberIsmi(String berberIsmi) {
        this.berberIsmi = berberIsmi;
    }

    public String getUidMusteri() {
        return uidMusteri;
    }

    public void setUidMusteri(String uidMusteri) {
        this.uidMusteri = uidMusteri;
    }

    public String getUidBerber() {
        return uidBerber;
    }

    public void setUidBerber(String uidBerber) {
        this.uidBerber = uidBerber;
    }

    public Boolean getAppiontmentDurumu() {
        return appiontmentDurumu;
    }

    public void setAppiontmentDurumu(Boolean appiontmentDurumu) {
        this.appiontmentDurumu = appiontmentDurumu;
    }
}
