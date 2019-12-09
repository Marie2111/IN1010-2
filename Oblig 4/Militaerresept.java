public class Militaerresept extends HvitResept {

    public Militaerresept(Legemiddel legemiddel, Lege utskrivendeLege, Pasient pasient, int reit) {
        super(legemiddel, utskrivendeLege, pasient, reit);
    }

    public String farge() {
        return "hvit";
    }

    public double prisAaBetale() {
        return 0.0;
    }

    public void skrivUt() {
        super.skrivUt();
        System.out.println("Farge: " + this.farge());
        System.out.println("Pris å betale: " + this.prisAaBetale());
    }
}
