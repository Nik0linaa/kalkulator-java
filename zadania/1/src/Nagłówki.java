public class Nagłówki {
    String label;
    String producent;
    String opis;
    String idPolwyrobu;
    String idGotowca;
    int norma;
   String rozmiar;
    String opakowanie;
    double cenaEwidencyjna;
    String dekor;
    String kolor;
    int sztukiWOpakowaniu;
    int iloscSztukWOpakowaniu;
    int zamowionaIloscOpakowan;
    double cenaZaOpakowanie;
    String customerSKU;
    public Nagłówki(String[] dane) {
        this.label = dane[0];
        this.producent = dane[1];
        this.opis = dane[2];
        this.idPolwyrobu = dane[3];
        this.idGotowca = dane[4];
        this.norma = parseIntSafe(dane[5]);
        this.rozmiar = dane[6];
        this.opakowanie = dane[7];
        this.cenaEwidencyjna = parseDoubleSafe(dane[8]);
        this.dekor = dane[9];
        this.kolor = dane[10];
        this.sztukiWOpakowaniu = parseIntSafe(dane[11]);
        this.iloscSztukWOpakowaniu = parseIntSafe(dane[12]);
        this.zamowionaIloscOpakowan = parseIntSafe(dane[13]);
        this.cenaZaOpakowanie = parseDoubleSafe(dane[14]);
        this.customerSKU = dane.length > 15 ? dane[15] : "";
    }
    public double wartosc() {
        return zamowionaIloscOpakowan * cenaZaOpakowanie;
    }

    public String getRozmiar() {
        return rozmiar;
    }
    private int parseIntSafe(String s) {
        try {
            return Integer.parseInt(s.trim());
        } catch (Exception e) {
            return 0;
        }
    }

    private double parseDoubleSafe(String s) {
        try {
            return Double.parseDouble(s.replace(",", ".").replaceAll("[^\\d.]", ""));
        } catch (Exception e) {
            return 0.0;
        }
    }
}
