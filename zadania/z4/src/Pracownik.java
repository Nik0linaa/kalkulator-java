public class Pracownik {
    private String imie;
    private String stanowisko;
    private int liczbaGodzin;
    private int wykonaneBombki;

    public Pracownik(String imie, String stanowisko, int godziny, int bombki) {
        this.imie = imie;
        this.stanowisko = stanowisko;
        this.liczbaGodzin = godziny;
        this.wykonaneBombki = bombki;
    }


    public String getImie() {
        return imie;
    }

    public String getStanowisko() {
        return stanowisko;
    }

    public int getLiczbaGodzin() {
        return liczbaGodzin;
    }

    public int getWykonaneBombki() {
        return wykonaneBombki;
    }

    public void setStanowisko(String stanowisko) {
        this.stanowisko = stanowisko;
    }

    public void setLiczbaGodzin(int liczbaGodzin) {
        this.liczbaGodzin = liczbaGodzin;
    }

    public void setWykonaneBombki(int wykonaneBombki) {
        this.wykonaneBombki = wykonaneBombki;
    }

    @Override
    public String toString() {
        return "Imię: " + imie +
                ", Stanowisko: " + stanowisko +
                ", Godzin: " + liczbaGodzin +
                ", Bombki: " + wykonaneBombki;
    }
}
