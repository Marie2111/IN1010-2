public abstract class Rute {

    // Retninger
    private enum Retning {
        NORD,
        SOR,
        OST,
        VEST
    }

    // Maze Runner
    protected Labyrint labyrint;

    // Koordinater
    protected int x;
    protected int y;

    // Naboruter
    protected Rute nord;
    protected Rute sor;
    protected Rute ost; // Jarlsberg?
    protected Rute vest;

    public Rute(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // Magic
    public abstract char tilTegn();

    public void gaa(Retning forrige, String koordinater) {
        // Er ruten allerede besokt? (For sykliske labyrinter)
        if (koordinater.contains(koordinaterTilTekst()))
            return;

        koordinater += " --> " + koordinaterTilTekst();

        // Utgang?
        if (nord == null || sor == null || ost == null || vest == null) {
            labyrint.leggTilUtvei(koordinater);
            return;
        }

        if (forrige != Retning.NORD) {
            if (nord.tilTegn() != '#')
                nord.gaa(Retning.SOR, koordinater);
        }

        if (forrige != Retning.SOR) {
            if (sor.tilTegn() != '#')
                sor.gaa(Retning.NORD, koordinater);
        }

        if (forrige != Retning.OST) {
            if (ost.tilTegn() != '#')
                ost.gaa(Retning.VEST, koordinater);
        }

        if (forrige != Retning.VEST) {
            if (vest.tilTegn() != '#')
                vest.gaa(Retning.OST, koordinater);
        }
    }

    public void finnUtvei() {
        // Har vi begynt i en utgang?
        if (nord == null || sor == null || ost == null || vest == null) {
            if (this.tilTegn() != '#')
                labyrint.leggTilUtvei(koordinaterTilTekst());
            return;
        }

        if (nord.tilTegn() != '#')
            nord.gaa(Retning.SOR, koordinaterTilTekst());

        if (sor.tilTegn() != '#')
            sor.gaa(Retning.NORD, koordinaterTilTekst());

        if (ost.tilTegn() != '#')
            ost.gaa(Retning.VEST, koordinaterTilTekst());

        if (vest.tilTegn() != '#')
            vest.gaa(Retning.OST, koordinaterTilTekst());
    }

    private String koordinaterTilTekst() {
        return "(" + x + ", " + y + ")";
    }

    public void settLabyrint(Labyrint labyrint) {
        this.labyrint = labyrint;
    }

    public void settNord(Rute nord) {
        this.nord = nord;
    }

    public void settSor(Rute sor) {
        this.sor = sor;
    }

    public void settOst(Rute ost) {
        this.ost = ost;
    }

    public void settVest(Rute vest) {
        this.vest = vest;
    }
}
