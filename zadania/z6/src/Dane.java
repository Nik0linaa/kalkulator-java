public class Dane {
    private String miesiac;
    private int ilosc;
    private String typ;
    private String klient;

    public Dane(String miesiac, int ilosc, String typ, String klient) {
        this.miesiac = miesiac;
        this.ilosc = ilosc;
        this.typ = typ;
        this.klient = klient;
    }

    public String getMiesiac() { return miesiac; }
    public int getIlosc() { return ilosc; }
    public String getTyp() { return typ; }
    public String getKlient() { return klient; }
}
