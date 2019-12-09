public class Melding implements Comparable<Melding> {

    private int kanalId;
    private int sekvensnummer;
    private String innhold;
    private static int globaltSekvensnummer = 0;

    public Melding(String innhold, int kanalId) {
        this.innhold = innhold;
        this.kanalId = kanalId;
        sekvensnummer = globaltSekvensnummer;
        globaltSekvensnummer++;
    }

    @Override
    public int compareTo(Melding m) {
        return Integer.compare(this.hentSekvensnummer(), m.hentSekvensnummer());
    }

    public void endreInnhold(String innhold) {
        this.innhold = innhold;
    }

    public String hentInnhold() {
        return innhold;
    }

    public int hentKanalId() {
        return kanalId;
    }

    public int hentSekvensnummer() {
        return sekvensnummer;
    }
}
