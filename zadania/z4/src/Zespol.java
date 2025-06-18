import java.util.*;

public class Zespol {
    private List<Pracownik> pracownicy = new ArrayList<>();

    public void dodaj(Pracownik p) {
        pracownicy.add(p);
    }

    public void pokazWszystkich() {
        if (pracownicy.isEmpty()) {
            System.out.println("Brak pracowników.");
        } else {
            pracownicy.forEach(System.out::println);
        }
    }

    public boolean edytuj(String imie, String noweStanowisko, int noweGodziny, int noweBombki) {
        for (Pracownik p : pracownicy) {
            if (p.getImie().equalsIgnoreCase(imie)) {
                p.setStanowisko(noweStanowisko);
                p.setLiczbaGodzin(noweGodziny);
                p.setWykonaneBombki(noweBombki);
                return true;
            }
        }
        return false;
    }

    public boolean czyJestPracownik(String imie) {
        return pracownicy.stream().anyMatch(p -> p.getImie().equalsIgnoreCase(imie));
    }

    public boolean usun(String imie) {
        return pracownicy.removeIf(p -> p.getImie().equalsIgnoreCase(imie));
    }

    public int zliczWydajnosc() {
        return pracownicy.stream().mapToInt(Pracownik::getWykonaneBombki).sum();
    }
}
