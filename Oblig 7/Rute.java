import javafx.scene.control.Button;

public abstract class Rute extends Button {

    // Retninger
    protected enum Retning {
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

    public abstract void gaa(Retning forrige, String koordinater);

    public void finnUtvei() {
        this.gaa(null, "");
    }

    protected String koordinaterTilTekst() {
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

    public int hentX() {
        return x;
    }

    public int hentY() {
        return y;
    }
}
