import java.util.ArrayList;
import java.io.PrintWriter;
import java.io.FileNotFoundException;
import java.util.Collections;

public class Operasjonsleder implements Runnable {

    private Monitor dekrypterteMeldinger;
    private ArrayList<ArrayList<Melding>> kanaler;

    public Operasjonsleder(Monitor dekrypterteMeldinger, int antallKanaler) {
        this.dekrypterteMeldinger = dekrypterteMeldinger;
        kanaler = new ArrayList<ArrayList<Melding>>();

        for (int i = 0; i < antallKanaler; i++) {
            kanaler.add(new ArrayList<Melding>());
        }
    }

    public void run() {
        while (!dekrypterteMeldinger.erFerdigMottatt()) {
            Melding m = dekrypterteMeldinger.hent();
            kanaler.get(m.hentKanalId() - 1).add(m);
            System.out.println("Mottar meldinger...");
        }

        for (int i = 0; i < kanaler.size(); i++) {
            Collections.sort(kanaler.get(i));
            skrivTilFil(i + 1);
        }

        System.out.println("=====> Operasjonsleder er ferdig");
    }

    public void skrivTilFil(int kanalId) {
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(kanalId + ".kanal", "utf-8");
        } catch (Exception e) {
            System.out.println("Klarte ikke å skrive til fil.");
        }

        ArrayList<Melding> meldinger = kanaler.get(kanalId - 1);
        for (int i = 0; i < meldinger.size(); i++) {
            writer.println(meldinger.get(i).hentInnhold() + "\n \n");
        }

        writer.close();
    }
}
