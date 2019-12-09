public class Lege implements Comparable<Lege> {

    private String navn;
    private Lenkeliste<Resept> resepter;
    private int antallNarkotiskeLegemidler;

    public Lege(String navn) {
        this.navn = navn;
        resepter = new Lenkeliste<Resept>();
        antallNarkotiskeLegemidler = 0;
    }

    public void skrivUtResept(Resept resept) {
        if (resept.hentLegemiddel() instanceof LegemiddelA)
            antallNarkotiskeLegemidler++;

        resepter.leggTil(resept);
    }

    public Lenkeliste<Resept> hentResepter() {
        return resepter;
    }

    public String hentNavn() {
        return navn;
    }

    public void skrivUt() {
        System.out.println("Navn: " + this.hentNavn());
    }

    public int hentAntallNarkotiskeLegemidler() {
        return antallNarkotiskeLegemidler;
    }

    @Override
    public int compareTo(Lege lege) {
        return navn.compareTo(lege.hentNavn());
    }
}
