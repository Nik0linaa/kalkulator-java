import java.time.LocalDate;

public class Generatorkodow {
    private static int licznik = 1;

    public static String generujKod(String kolor, int rozmiar) {
        String kolorUpper = kolor.toUpperCase();
        String rok = String.valueOf(LocalDate.now().getYear());
        String numer = String.format("%05d", licznik++);
        return String.format("BOM-%s-%d-%s-%s", kolorUpper, rozmiar, rok, numer);
    }

}
