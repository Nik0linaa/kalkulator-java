import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Zamowienia {
    private List<Dane> lista = new ArrayList<>();
    private static final String PLIK = "zamowienia.csv";

    public Zamowienia() {
        wczytajZPliku();
    }

    public void dodajZamowienie(Dane dane) {
        lista.add(dane);
        zapiszDoPliku();
    }

    public void pokazWszystkie() {
        if (lista.isEmpty()) {
            System.out.println("Brak zamówień.");
        } else {
            lista.forEach(System.out::println);
        }
    }

    public List<Dane> filtrujPoImieniuLubNazwisku(String tekst) {
        return lista.stream()
                .filter(d -> d.getImie().equalsIgnoreCase(tekst) || d.getNazwisko().equalsIgnoreCase(tekst))
                .collect(Collectors.toList());
    }

    public List<Dane> filtrujPoDacie(String data) {
        return lista.stream()
                .filter(d -> d.getTermin().equals(data))
                .collect(Collectors.toList());
    }

    public List<Dane> filtrujPoModelu(String model) {
        return lista.stream()
                .filter(d -> d.getModel().equalsIgnoreCase(model))
                .collect(Collectors.toList());
    }

    private void zapiszDoPliku() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(PLIK))) {
            for (Dane d : lista) {
                pw.println(d.toCSV());
            }
        } catch (IOException e) {
            System.out.println("Błąd zapisu do pliku: " + e.getMessage());
        }
    }

    private void wczytajZPliku() {
        File plik = new File(PLIK);
        if (!plik.exists()) return;

        try (BufferedReader br = new BufferedReader(new FileReader(plik))) {
            String linia;
            while ((linia = br.readLine()) != null) {
                try {
                    lista.add(Dane.fromCSV(linia));
                } catch (Exception e) {
                    System.out.println("Pominięto linijkę z błędem: " + linia);
                }

            }
        } catch (IOException e) {
            System.out.println("Błąd odczytu z pliku: " + e.getMessage());
        }
    }
    public boolean usunPoID(int id) {
        boolean usunieto = lista.removeIf(d -> d.getId() == id);
        if (usunieto) {
            zapiszDoPliku();
        }
        return usunieto;
    }

}
