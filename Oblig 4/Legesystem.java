import java.util.ArrayList;

public class Legesystem {

    private GUI grensesnitt;

    private Lenkeliste<Legemiddel> legemidler;
    private Lenkeliste<Resept> resepter;
    private Lenkeliste<Pasient> pasienter;
    private SortertLenkeliste<Lege> leger;

    private int antallVanedannende;
    private int antallVanedannendeMilitaer;

    private final String[] gyldigeLegemiddelTyper = new String[]{"a", "b", "c"};
    private final String[] gyldigeReseptTyper = new String[]{"blaa", "hvit", "prevensjon", "militaer"};

    public Legesystem() {
        legemidler = new Lenkeliste<Legemiddel>();
        resepter = new Lenkeliste<Resept>();
        pasienter = new Lenkeliste<Pasient>();
        leger = new SortertLenkeliste<Lege>();

        grensesnitt = new GUI();
    }

    public void kjor() {
        // Les inn fra fil
        String filnavn = grensesnitt.lesTekst("Oppgi filnavn");
        lesInnFil(filnavn);

        while (true) {
            grensesnitt.skrivUtOverskrift("Hovedmeny");

            int valg = grensesnitt.skrivUtValg(new String[]{
                "Skriv ut oversikt",
                "Opprett element",
                "Bruk resept",
                "Skriv ut statistikk",
                "Skriv data til fil"
            });
            System.out.println();

            switch (valg) {
                case 0:
                    skrivUtOversikt();
                    break;
                case 1:
                    opprett();
                    break;
                case 2:
                    brukResept();
                    break;
                case 3:
                    skrivUtStatistikk();
                    break;
                case 4:
                    grensesnitt.skrivUtMelding("Dette ble ikke helt ferdig!");
                    break;
            }

            // Clear
        }
    }

    // Skriver ut en oversikt over alle elementer
    public void skrivUtOversikt() {

        grensesnitt.skrivUtOverskrift("Oversikt");
        System.out.println();

        grensesnitt.skrivUtUnderoverskrift("Legemidler");
        for (Legemiddel legemiddel : legemidler) {
            System.out.println(legemiddel.hentID() + ": " + legemiddel.hentNavn());
        }
        System.out.println();

        grensesnitt.skrivUtUnderoverskrift("Resepter");
        for (Resept resept: resepter) {
            System.out.println(resept.hentID() + ": " + resept.hentLegemiddel().hentNavn() + " - " +  resept.hentPasient().hentNavn());
        }
        System.out.println();

        grensesnitt.skrivUtUnderoverskrift("Leger");
        for (Lege lege : leger) {
            System.out.println(lege.hentNavn());
        }
        System.out.println();

        grensesnitt.skrivUtUnderoverskrift("Pasienter");
        for (Pasient pasient : pasienter) {
            System.out.println(pasient.hentID() + ": " + pasient.hentNavn());
        }
        System.out.println();
    }

    // Oppretter et nytt element
    public void opprett() {
        grensesnitt.skrivUtOverskrift("Opprett Element");
        int valg = grensesnitt.skrivUtValg(new String[]{
            "Lege",
            "Pasient",
            "Resept",
            "Legemiddel"
        });
        System.out.println();

        switch (valg) {
            case 0:
                opprettLege();
                break;
            case 1:
                opprettPasient();
                break;
            case 2:
                opprettResept();
                break;
            case 3:
                opprettLegemiddel();
                break;
        }
    }

    // Oppretter en ny pasient
    private void opprettPasient() {
        grensesnitt.skrivUtOverskrift("Opprett Pasient");
        String navn = grensesnitt.lesTekst("Navn");
        String fdnr = grensesnitt.lesTekst("Fnr");

        Pasient pasient = new Pasient(navn, fdnr);
        pasienter.leggTil(pasient);
        grensesnitt.skrivUtMelding("La til ny pasient: " + pasient.hentNavn());
    }

    // Oppretter et nytt legemiddel
    private void opprettLegemiddel() {
        grensesnitt.skrivUtOverskrift("Opprett Legemiddel");
        String navn = grensesnitt.lesTekst("Navn");
        String type = grensesnitt.lesTekst("Type", gyldigeLegemiddelTyper);
        double pris = grensesnitt.lesFlyttall("Pris");
        double virkestoff = grensesnitt.lesFlyttall("Virkestoff");

        Legemiddel legemiddel = null;
        if (type.equals("a") || type.equals("b")) {
            int styrke = grensesnitt.lesTall("Styrke");
            legemiddel = opprettLegemiddel(navn, type, pris, virkestoff, styrke);
        } else {
            legemiddel = opprettLegemiddel(navn, type, pris, virkestoff, -1);
        }

        grensesnitt.skrivUtMelding("La til nytt legemiddel: " + legemiddel.hentNavn());
    }

    private Legemiddel opprettLegemiddel(String navn, String type, double pris, double virkestoff, int styrke) {
        Legemiddel legemiddel = null;
        if (type.equals("a")) {
            legemiddel = new LegemiddelA(navn, pris, virkestoff, styrke);
        } else if (type.equals("b")) {
            legemiddel = new LegemiddelB(navn, pris, virkestoff, styrke);
        } else if (type.equals("c")) {
            legemiddel = new LegemiddelC(navn, pris, virkestoff);
        }

        legemidler.leggTil(legemiddel);
        return legemiddel;
    }

    // Oppretter en ny lege
    private void opprettLege() {
        grensesnitt.skrivUtOverskrift("Opprett Lege");
        String navn = grensesnitt.lesTekst("Navn");
        int avtaleNr = grensesnitt.lesTall("Avtalenummer");

        Lege lege = opprettLege(navn, avtaleNr);

        grensesnitt.skrivUtMelding("La til ny lege: " + lege.hentNavn());
    }

    private Lege opprettLege(String navn, int avtaleNr) {
        Lege lege = null;
        if (avtaleNr > 0) {
            lege = new Fastlege(navn, avtaleNr);
        } else {
            lege = new Lege(navn);
        }

        leger.leggTil(lege);
        return lege;
    }

    // Oppretter en ny resept
    private void opprettResept() {
        grensesnitt.skrivUtOverskrift("Opprett Resept");
        String type = grensesnitt.lesTekst("Type", gyldigeReseptTyper);
        int legemiddelNr = grensesnitt.lesTall("Legemiddel (ID)");
        String legeNavn = grensesnitt.lesTekst("Lege (Navn)");
        int pasientNr = grensesnitt.lesTall("Pasient (ID)");
        int reit = grensesnitt.lesTall("Reit");

        opprettResept(type, legemiddelNr, legeNavn, pasientNr, reit);

        grensesnitt.skrivUtMelding("La til nytt resept!");
    }

    private void opprettResept(String type, int legemiddelID, String legeNavn, int pasientID, int reit) {
        if (!finnesLegemiddel(legemiddelID)) {
            System.out.println("Fant ingen legemiddel!");
            return;
        }

        if (!finnesLege(legeNavn)) {
            System.out.println("Fant ingen lege!");
            return;
        }

        if (!finnesPasient(pasientID)) {
            System.out.println("Fant ingen pasient!");
            return;
        }

        Resept resept = null;
        Legemiddel legemiddel = legemidler.hent(legemiddelID);
        Pasient pasient = pasienter.hent(pasientID);
        Lege lege = null;
        for (Lege l : leger) {
            if (l.hentNavn().equals(legeNavn))
                lege = l;
        }

        if (type.equals("blaa")) {
            resept = new BlaaResept(legemiddel, lege, pasient, reit);
        } else if (type.equals("hvit")) {
            //resept = new HvitResept(legemiddel, lege, pasient, reit);
        } else if (type.equals("prevensjon")) {
            resept = new PResept(legemiddel, lege, pasient);
        } else if (type.equals("militaer")) {
            resept = new Militaerresept(legemiddel, lege, pasient, reit);
        }

        if (legemiddel instanceof LegemiddelB) {
            antallVanedannende++;
            if (resept instanceof Militaerresept)
                antallVanedannendeMilitaer++;
        }

        lege.skrivUtResept(resept);
        pasient.leggTilResept(resept);
        resepter.leggTil(resept);
    }

    // Bruker resept på en pasient
    public void brukResept() {
        // Pasientting
        if (pasienter.stoerrelse() <= 0) {
            grensesnitt.skrivUtMelding("Det er ingen pasienter i systemet enda!");
            return;
        }

        System.out.println("Hvilken pasient vil du se resepter for?");
        for (Pasient pasient : pasienter) {
            System.out.println(pasient.hentID() + ": " + pasient.hentNavn() + " (fnr " + pasient.hentFdnr() + ")");
        }
        int valgtPasient = grensesnitt.lesValg(pasienter.stoerrelse());
        Pasient pasient = pasienter.hent(valgtPasient);

        // Reseptting
        if (pasient.hentResepter().stoerrelse() <= 0) {
            grensesnitt.skrivUtMelding(pasient.hentNavn() + " har ingen resepter!");
            return;
        }

        System.out.println("Hvilken resept vil du bruke?");
        int teller = 0;
        for (Resept resept : pasient.hentResepter()) {
            System.out.println(teller + ": " + resept.hentLegemiddel().hentNavn() + " (" + resept.hentReit() + " reit)");
            teller++;
        }
        int valgtResept = grensesnitt.lesValg(pasient.hentResepter().stoerrelse());
        Resept resept = pasient.hentResepter().hent(valgtResept);

        // Bruk resept
        if (resept.bruk()) {
            grensesnitt.skrivUtMelding("Brukte resept " + resept.hentLegemiddel().hentNavn() + ". Antall reit igjen: " + resept.hentReit());
        } else {
            grensesnitt.skrivUtMelding("Kunne ikke bruke resept " + resept.hentLegemiddel().hentNavn() + ". Ingen reit igjen.");
        }
    }

    public void skrivUtStatistikk() {
        grensesnitt.skrivUtMelding("Antall utskrevne resepter for vanedannende legemidler: " + antallVanedannende);
        grensesnitt.skrivUtMelding("Antall vanedannende resepter utskrevet til militaere: " + antallVanedannendeMilitaer);

        grensesnitt.skrivUtUnderoverskrift("Leger");
        for (Lege lege : leger) {
            if (lege.hentAntallNarkotiskeLegemidler() > 0)
                System.out.println(lege.hentNavn() + " har skrevet ut " + lege.hentAntallNarkotiskeLegemidler() + " narkotiske legemidler");
        }
        System.out.println();

        grensesnitt.skrivUtUnderoverskrift("Pasienter");
        for (Pasient pasient : pasienter) {
            if (pasient.hentAntallNarkotiskeLegemidler() > 0)
                System.out.println(pasient.hentNavn() + " har " + pasient.hentAntallNarkotiskeLegemidler() + " gyldige narkotiske legemidler");
        }
        System.out.println();
    }

    public void lesInnFil(String filnavn) {
        ArrayList<String> filInnhold = grensesnitt.lesFil(filnavn);

        if (filInnhold == null)
            return;

        int teller = 0;

        // Parsering av filen
        for (String linje : filInnhold) {
            if (linje.startsWith("#")) {
                teller++;
                continue;
            }

            String[] data = linje.split(", ");

            // Pasienter
            if (teller == 1 && data.length == 2) {
                pasienter.leggTil(new Pasient(data[0], data[1]));
            // Legemidler
            } else if (teller == 2 && (data.length == 4 || data.length == 5)) {
                if (data.length == 4) {
                    opprettLegemiddel(data[0], data[1], Double.parseDouble(data[2]), Double.parseDouble(data[3]), -1);
                } else {
                    opprettLegemiddel(data[0], data[1], Double.parseDouble(data[2]), Double.parseDouble(data[3]), Integer.parseInt(data[4]));
                }
            // Leger
            } else if (teller == 3 && data.length == 2) {
                opprettLege(data[0], Integer.parseInt(data[1]));
            // Resepter
            } else if (teller == 4 && (data.length == 4 || data.length == 5)) {
                if (data.length == 4) {
                    opprettResept(data[0], Integer.parseInt(data[1]), data[2], Integer.parseInt(data[3]), -1);
                } else {
                    opprettResept(data[0], Integer.parseInt(data[1]), data[2], Integer.parseInt(data[3]), Integer.parseInt(data[4]));
                }
            }

        }
    }

    public void skrivTilFil() {

    }

    private boolean finnesLegemiddel(int id) {
        for (Legemiddel legemiddel : legemidler) {
            if (legemiddel.hentID() == id)
                return true;
        }
        return false;
    }

    private boolean finnesPasient(int id) {
        for (Pasient pasient : pasienter) {
            if (pasient.hentID() == id)
                return true;
        }
        return false;
    }

    private boolean finnesLege(String navn) {
        for (Lege lege : leger) {
            if (lege.hentNavn().equals(navn))
                return true;
        }
        return false;
    }
}
