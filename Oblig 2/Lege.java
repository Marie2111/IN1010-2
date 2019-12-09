public class Lege {

    private String navn;

    public Lege(String navn) {
        this.navn = navn;
    }

    public String hentNavn() {
        return navn;
    }

    public void skrivUt() {
        System.out.println("Navn: " + this.hentNavn());
    }
}
