package com.example.e_ber;

public class KullanciInfoModel {
    private String name,yorumlar;
    private Float ranking;
    private String ID;




    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public Float getRanking() {
        return ranking;
    }

    public void setRanking(Float ranking) {
        this.ranking = ranking;
    }

    public String getYorumlar() {
        return yorumlar;
    }

    public void setYorumlar(String yorumlar) {
        this.yorumlar = yorumlar;
    }
}
