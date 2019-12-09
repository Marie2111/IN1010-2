public class LegemiddelA extends Legemiddel {

    private int styrke;

    public LegemiddelA(String navn, double pris, double virkestoff, int styrke) {
        super(navn, pris, virkestoff);
        this.styrke = styrke;
    }

    public int hentNarkotiskStyrke() {
        return styrke;
    }

    public void skrivUt() {
        super.skrivUt();
        System.out.println("Narkotisk Styrke: " + this.hentNarkotiskStyrke());
    }
}
