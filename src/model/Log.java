package model;

import java.lang.reflect.Array;

public class Log {
    private String datum;
    private int[] tijdstip;
    private double totaalBedrag,korting,teBetalen;

    public Log(String datum,int[] tijdstip,double bedrag,double korting,double teBetalen){
        this.datum=datum;
        this.tijdstip=tijdstip;
        this.totaalBedrag=bedrag;
        this.korting=korting;
        this.teBetalen=teBetalen;
    }

    public String getDatum() {
        return datum;
    }

    public void setDatum(String datum) {
        this.datum = datum;
    }

    public int[] getTijdstip() {
        return tijdstip;
    }

    public String getTijdstipString() {
        return tijdstip[0] + ":" + tijdstip[1];
    }

    public void setTijdstip(int[] tijdstip) {
        this.tijdstip = tijdstip;
    }

    public double getTotaalBedrag() {
        return totaalBedrag;
    }

    public void setTotaalBedrag(double totaalBedrag) {
        this.totaalBedrag = totaalBedrag;
    }

    public double getKorting() {
        return korting;
    }

    public void setKorting(double korting) {
        this.korting = korting;
    }

    public double getTeBetalen() {
        return teBetalen;
    }

    public void setTeBetalen(double teBetalen) {
        this.teBetalen = teBetalen;
    }
}
