import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Labyrint {

    private Rute[][] ruter;
    private int antallRader;
    private int antallKolonner;
    private Liste<String> utveier;

    private Labyrint(Rute[][] ruter, int antallRader, int antallKolonner) {
        this.antallRader = antallRader;
        this.antallKolonner = antallKolonner;
        this.ruter = ruter;
        utveier = new Lenkeliste<String>();
    }

    public static Labyrint lesFraFil(File fil) throws FileNotFoundException {
        Scanner scanner = new Scanner(fil);

        // Les Antall Rader og Kolonner
        String[] data = scanner.nextLine().split(" ");
        int rader = Integer.parseInt(data[1]);
        int kolonner = Integer.parseInt(data[0]);

        // Les Ruter
        Rute[][] ruteData = new Rute[rader][kolonner];
        int kolonne = 0;
        while (scanner.hasNextLine()) {
            String linje = scanner.nextLine();
            for (int rad = 0; rad < linje.length(); rad++) {
                char tegn = linje.charAt(rad);

                switch (tegn) {
                    case '#':
                        ruteData[rad][kolonne] = new SortRute(rad, kolonne);
                        break;
                    case '.':
                        if (erAapning(rad, kolonne, rader, kolonner))
                            ruteData[rad][kolonne] = new Aapning(rad, kolonne);
                        else
                            ruteData[rad][kolonne] = new HvitRute(rad, kolonne);
                        break;
                }
            }
            kolonne++;
        }

        // Opprett labyrint
        Labyrint labyrint = new Labyrint(ruteData, rader, kolonner);

        // Naboruter og Referense til Labyrinten
        for (int y = 0; y < kolonner; y++) {
            for (int x = 0; x < rader; x++) {
                labyrint.hentRuter()[x][y].settLabyrint(labyrint);

                if (y > 0)
                    labyrint.hentRuter()[x][y].settNord(labyrint.hentRuter()[x][y - 1]);
                if (y < (kolonner - 1))
                    labyrint.hentRuter()[x][y].settSor(labyrint.hentRuter()[x][y + 1]);
                if (x > 0)
                    labyrint.hentRuter()[x][y].settOst(labyrint.hentRuter()[x - 1][y]);
                if (x < (rader - 1))
                    labyrint.hentRuter()[x][y].settVest(labyrint.hentRuter()[x + 1][y]);
            }
        }

        return labyrint;
    }

    // Sjekker om en Rute er en Aapning
    private static boolean erAapning(int x, int y, int antRader, int antKolonner) {
        if (x == 0 || y == 0 || x == (antRader - 1) || y == (antRader - 1))
            return true;
        return false;
    }

    public Liste<String> finnUtveiFra(int kol, int rad) {
        // Fjern alt fra listen
        while (!utveier.erTom()) {
            utveier.fjern();
        }

        ruter[rad][kol].finnUtvei();
        return utveier;
    }

    public String toString() {
        String s = "";
        for (int y = 0; y < antallKolonner; y++) {
            for (int x = 0; x < antallRader; x++) {
                s += ruter[x][y].tilTegn();
            }
            s += "\n";
        }
        return s;
    }

    public void leggTilUtvei(String utvei) {
        utveier.leggTil(utvei);
    }

    public Rute hentRute(int x, int y) {
        return ruter[x][y];
    }

    public Rute[][] hentRuter() {
        return ruter;
    }

    public int hentAntallRader() {
        return antallRader;
    }

    public int hentAntallKolonner() {
        return antallKolonner;

    }
}
