public class Dane {
    private static int licznikId = 1;

    private int id;
    private String imie;
    private String nazwisko;
    private int ilosc;
    private String model;
    private String kolor;
    private String termin;

    public Dane(String imie, String nazwisko, int ilosc, String model, String kolor, String termin) {
        this.id = licznikId++;
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.ilosc = ilosc;
        this.model = model;
        this.kolor = kolor;
        this.termin = termin;
    }

    public int getId() {
        return id;
    }

    public String getImie() { return imie; }
    public String getNazwisko() { return nazwisko; }
    public int getIlosc() { return ilosc; }
    public String getModel() { return model; }
    public String getKolor() { return kolor; }
    public String getTermin() { return termin; }

    @Override
    public String toString() {
        return String.format("ID: %d | %s %s | %d bombek | Model: %s | Kolor: %s | Termin: %s",
                id, imie, nazwisko, ilosc, model, kolor, termin);
    }
    public String toCSV() {
        return id + ";" + imie + ";" + nazwisko + ";" + ilosc + ";" + model + ";" + kolor + ";" + termin;
    }
    public static Dane fromCSV(String linia) throws Exception {
        String[] parts = linia.split(";");
        if (parts.length != 7) throw new Exception("Zły format danych");
        Dane d = new Dane(parts[1], parts[2], Integer.parseInt(parts[3]), parts[4], parts[5], parts[6]);
        d.id = Integer.parseInt(parts[0]);

        if (d.id >= licznikId) {
            licznikId = d.id + 1;
        }

        return d;
    }
}
