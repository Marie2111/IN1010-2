public abstract class Resept {

    private static int globalID = 0;

    protected int id;
    protected Legemiddel legemiddel;
    protected Lege utskrivendeLege;
    protected Pasient pasient;
    protected int reit;

    public Resept(Legemiddel legemiddel, Lege utskrivendeLege, Pasient pasient, int reit) {
        id = globalID;
        globalID++;

        this.legemiddel = legemiddel;
        this.utskrivendeLege = utskrivendeLege;
        this.pasient = pasient;
        this.reit = reit;
    }

    public abstract String farge();

    public abstract double prisAaBetale();

    public boolean bruk() {
        if (reit > 0) {
            reit--;
            return true;
        }
        return false;
    }

    public boolean erGyldig() {
        return (reit > 0);
    }

    public int hentID() {
        return id;
    }

    public Legemiddel hentLegemiddel() {
        return legemiddel;
    }

    public Lege hentLege() {
        return utskrivendeLege;
    }

    public Pasient hentPasient() {
        return pasient;
    }

    public int hentReit() {
        return reit;
    }

    protected void skrivUt() {
        System.out.println("ID: " + this.hentID());
        System.out.println("Legemiddel: " + this.legemiddel.hentNavn());
        System.out.println("Lege: " + this.utskrivendeLege.hentNavn());
        System.out.println("Pasient: " + this.pasient.hentNavn());
        System.out.println("Reit: " + this.hentReit());
    }
}
