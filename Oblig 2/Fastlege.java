public class Fastlege extends Lege implements Kommuneavtale {

    private int avtaleNummer;

    public Fastlege(String navn, int avtaleNummer) {
        super(navn);
        this.avtaleNummer = avtaleNummer;
    }

    public int hentAvtalenummer() {
        return avtaleNummer;
    }

    public void skrivUt() {
        super.skrivUt();
        System.out.println("Avtalenummer: " + this.hentAvtalenummer());
    }

}
