public class BlaaResept extends Resept {

    public BlaaResept(Legemiddel legemiddel, Lege utskrivendeLege, int pasientID, int reit) {
        super(legemiddel, utskrivendeLege, pasientID, reit);
    }

    public String farge() {
      return "blaa";
    }

    public double prisAaBetale() {
        return legemiddel.hentPris() / 4; // 25%
    }

    public void skrivUt() {
        super.skrivUt();
        System.out.println("Farge: " + this.farge());
        System.out.println("Pris å betale: " + this.prisAaBetale());
    }

}
