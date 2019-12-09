public class Aapning extends HvitRute {

    public Aapning(int x, int y) {
        super(x, y);
    }

    public void gaa(Retning forrige, String koordinater) {
        koordinater += " --> " + koordinaterTilTekst();
        labyrint.leggTilUtvei(koordinater);
    }

}
