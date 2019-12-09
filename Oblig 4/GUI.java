import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;

public class GUI {

    private Scanner sc;
    private Scanner innFil;

    public GUI() {
        sc = new Scanner(System.in);
    }

    public ArrayList<String> lesFil(String filnavn) {
        try {
            File fil = new File(filnavn);
            innFil = new Scanner(fil);
        } catch (FileNotFoundException ex) {
            skrivUtMelding("Kunne ikke lese fil: " + filnavn);
            return null;
        }

        ArrayList<String> linjer = new ArrayList<String>();

        while (innFil.hasNextLine()) {
            String linje = innFil.nextLine();
            linjer.add(linje);
        }

        return linjer;
    }

    public void skrivUtOverskrift(String tekst) {
        System.out.println("=====================");
        System.out.println(tekst);
        System.out.println("=====================");
    }

    public void skrivUtUnderoverskrift(String tekst) {
        System.out.println(tekst);
        System.out.println("--------------------");
    }

    public void skrivUtMelding(String tekst) {
        System.out.println();
        System.out.println(tekst);
        System.out.println();
    }

    // Skriv ut og les inn valg
    public int skrivUtValg(String[] valg) {
        for (int i = 0; i < valg.length; i++) {
            System.out.println(i + ": " + valg[i]);
        }

        return lesValg(valg.length);
    }

    // Les inn valg
    public int lesValg(int antallValg) {
        while (true) {
            int indeks = lesTall("Skriv inn valg");
            if (indeks >= 0 && indeks < antallValg)
                return indeks;
            System.out.println("Ugyldig valg!");
        }
    }

    // Les inn tall
    public int lesTall(String melding) {
        while (true) {
            try {
                System.out.print(melding + ": ");
                String in = sc.nextLine();
                int input = Integer.parseInt(in);
                return input;
            } catch (NumberFormatException ex) {
                System.out.println("Dette er ikke et heltall!");
            }
        }
    }

    // Les inn flyttall
    public double lesFlyttall(String melding) {
        while (true) {
            try {
                System.out.print(melding + ": ");
                String in = sc.nextLine();
                double input = Double.parseDouble(in);
                return input;
            } catch (NumberFormatException ex) {
                System.out.println("Dette er ikke et tall!");
            }
        }
    }

    // Les inn tekst
    public String lesTekst(String melding) {
        System.out.print(melding + ": ");
        return sc.nextLine();
    }

    // Les inn tekst (med validering)
    public String lesTekst(String melding, String[] validering) {
        while (true) {
            System.out.print(melding + ": ");
            String in = sc.nextLine();
            for (int i = 0; i < validering.length; i++) {
                if (validering[i].equals(in))
                    return in;
            }
            System.out.println("Ugyldig input!");
        }
    }
}
