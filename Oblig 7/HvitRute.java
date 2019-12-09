public class HvitRute extends Rute {

    public HvitRute(int x, int y) {
        super(x, y);
    }

    public char tilTegn() {
        return '.';
    }

    public void gaa(Retning forrige, String koordinater) {
        // Er ruten allerede besokt? (For sykliske labyrinter)
        if (koordinater.contains(koordinaterTilTekst()))
            return;

        koordinater += " --> " + koordinaterTilTekst();

        if (forrige != Retning.NORD) {
            nord.gaa(Retning.SOR, koordinater);
        }

        if (forrige != Retning.SOR) {
            sor.gaa(Retning.NORD, koordinater);
        }

        if (forrige != Retning.OST) {
            ost.gaa(Retning.VEST, koordinater);
        }

        if (forrige != Retning.VEST) {
            vest.gaa(Retning.OST, koordinater);
        }
    }
}
