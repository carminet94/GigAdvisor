package com.example.gigadvisor.data.model;

public class Rating {

    String titolo, descrizione, data;

    public Rating(String tit, String dat, String des){
        titolo=tit;
        data=dat;
        descrizione=des;
    }

    public String getTitolo() {
        return titolo;
    }

    public String getDescrizione() { return descrizione;}

    public String getData(){
        return data;
    }

}
