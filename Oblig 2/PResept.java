public class PResept extends HvitResept {

    public PResept(Legemiddel legemiddel, Lege utskrivendeLege, int pasientID) {
        super(legemiddel, utskrivendeLege, pasientID, 3);
    }

    public String farge() {
        return "hvit";
    }

    public double prisAaBetale() {
        if (legemiddel.hentPris() - 116 < 0) {
            return 0.0;
        }
        return legemiddel.hentPris() - 116;
    }

    public void skrivUt() {
        super.skrivUt();
        System.out.println("Farge: " + this.farge());
        System.out.println("Pris å betale: " + this.prisAaBetale());
    }
}
