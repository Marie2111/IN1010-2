public abstract class Legemiddel {

    private static int globalID = 0;

    protected int id;
    protected String navn;
    protected double pris;
    protected double virkestoff;

    public Legemiddel(String navn, double pris, double virkestoff) {
        id = globalID;
        globalID++;

        this.navn = navn;
        this.pris = pris;
        this.virkestoff = virkestoff;
    }

    public int hentID() {
        return id;
    }

    public String hentNavn() {
        return navn;
    }

    public double hentPris() {
        return pris;
    }

    public double hentVirkestoff() {
        return virkestoff;
    }

    public void settNyPris(double pris) {
        this.pris = pris;
    }

    public void skrivUt() {
        System.out.println("ID: " + this.hentID());
        System.out.println("Navn: " + this.hentNavn());
        System.out.println("Pris: " + this.hentPris());
        System.out.println("Virkestoff: " + this.hentVirkestoff());
    }
}
