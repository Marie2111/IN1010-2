public class Pasient {

    private static int globalID = 0;

    private int id;
    private String navn;
    private String fdnr;
    private Stabel<Resept> resepter;
    private int antallNarkotiskeLegemidler;

    public Pasient(String navn, String fdnr) {
        id = globalID;
        globalID++;
        this.navn = navn;
        this.fdnr = fdnr;
        resepter = new Stabel();
    }

    public void leggTilResept(Resept resept) {
        resepter.leggPaa(resept);
    }

    public int hentID() {
        return id;
    }

    public String hentNavn() {
        return navn;
    }

    public String hentFdnr() {
        return fdnr;
    }

    public int hentAntallNarkotiskeLegemidler() {
        int antall = 0;
        for (Resept resept : resepter) {
            if (resept.hentLegemiddel() instanceof LegemiddelA && resept.erGyldig())
                antall++;
        }
        return antall;
    }

    public Stabel<Resept> hentResepter() {
        return resepter;
    }
}
