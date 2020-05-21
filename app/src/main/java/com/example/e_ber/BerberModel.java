package com.example.e_ber;

public class BerberModel {
    private String storeName;
    private IllerModel il;
    private IlcelerModeli ilce;
    private MahallelerModeli mah;
    private String ud;
    private float ranking;
    private int UserNumber;
    private  String yorumlar;

    public String getYorumlar() {
        return yorumlar;
    }

    public void setYorumlar(String yorumlar) {
        this.yorumlar = yorumlar;
    }

    public String getUd() {
        return ud;
    }

    public void setUd(String ud) {
        this.ud = ud;
    }

    public float getRanking() {
        return ranking;
    }

    public void setRanking(float ranking) {
        this.ranking = ranking;
    }

    public int getUserNumber() {
        return UserNumber;
    }

    public void setUserNumber(int userNumber) {
        UserNumber = userNumber;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public IllerModel getIl() {
        return il;
    }

    public void setIl(IllerModel il) {
        this.il = il;
    }

    public IlcelerModeli getIlce() {
        return ilce;
    }

    public void setIlce(com.example.e_ber.IlcelerModeli ilce) {
        this.ilce = ilce;
    }

    public MahallelerModeli getMah() {
        return mah;
    }

    public void setMah(MahallelerModeli mah) {
        this.mah = mah;
    }
}
