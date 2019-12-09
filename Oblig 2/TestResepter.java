public class TestResepter {

    public static void testResept() {
        Legemiddel legemiddel = new LegemiddelC("Drugs?", 250, 5);
        Lege lege = new Lege("Testlege");
        Resept resept = new BlaaResept(legemiddel, lege, 0, 3); // Jeg bruker blåresept siden Resept er en abstrakt klasse

        // Test ID - Forventet ID er 0 (Her antar vi at dette er det første objektet av resept som opprettes)
        if (resept.hentID() == 0) { System.out.println("Test: ID - Riktig");
        } else { System.out.println("Test: ID - Feil"); }

        // Test Reit og Bruk
        if (resept.bruk() && resept.hentReit() == 2) { System.out.println("Test: Reit / Bruk - Riktig");
        } else { System.out.println("Test: Reit / Bruk - Feil"); }

        // Test Legemiddel - denne sjekker bare at referansen til et legemiddel ikke er tomt
        if (resept.hentLegemiddel() != null) { System.out.println("Test: Legemiddel - Riktig");
        } else { System.out.println("Test: Legemiddel - Feil"); }

        // Test Lege - denne sjekker bare at referansen til et lege ikke er tomt
        if (resept.hentLege() != null) { System.out.println("Test: Lege - Riktig");
        } else { System.out.println("Test: Lege - Feil"); }

        // Teset Pasient ID - forventet resultat er 0
        if (resept.hentPasientID() == 0) { System.out.println("Test: Pasient ID - Riktig");
        } else { System.out.println("Test: Pasient ID - Feil"); }
    }

    public static void testMilitaerResept() {
        Legemiddel legemiddel = new LegemiddelC("Et veldig fiksjonelt legemiddel", 100, 3);
        Lege lege = new Lege("Leonard McCoy");
        Militaerresept resept = new Militaerresept(legemiddel, lege, 0, 5);

        // Test Farge - Forventet resultat er "hvit"
        if (resept.farge() == "hvit") { System.out.println("Test: Farge - Riktig");
        } else { System.out.println("Test: Farge - Feil"); }

        // Test Pris - Forventet resultat er 0 siden dette er et Militærresept
        if (resept.prisAaBetale() == 0) { System.out.println("Test: Pris - Riktig");
        } else { System.out.println("Test: Pris - Feil"); }
    }

    public static void testPResept() {
        Legemiddel legemiddel = new LegemiddelC("Testlegemiddel", 50, 2);
        Lege lege = new Lege("En veldig fiksjonell lege");
        PResept resept = new PResept(legemiddel, lege, 0);

        // Test Farge - Forventet resultat er "hvit"
        if (resept.farge() == "hvit") { System.out.println("Test: Farge - Riktig");
        } else { System.out.println("Test: Farge - Feil"); }

        // Test Pris - Forventet resultat er 0 siden dette er et P-Resept på et legemiddel som koster 50 kr
        if (resept.prisAaBetale() == 0) { System.out.println("Test: Pris - Riktig");
        } else { System.out.println("Test: Pris - Feil"); }

        // Test Reit - Forventet resultat er 3 siden dette er et P-Resept
        if (resept.hentReit() == 3) { System.out.println("Test: Reit - Riktig");
        } else { System.out.println("Test: Reit - Feil"); }
    }

    public static void testBlaaResept() {
        Legemiddel legemiddel = new LegemiddelC("Magical Kittens", 100, 5);
        Lege lege = new Lege("The Doctor");
        BlaaResept resept = new BlaaResept(legemiddel, lege, 0, 3);

        // Test Farge - Forventet resultat er "blaa"
        if (resept.farge() == "blaa") { System.out.println("Test: Farge - Riktig");
        } else { System.out.println("Test: Farge - Feil"); }

        // Test Pris - Forventet resultat er 25 siden dette er et Blåresept på et legemiddel som koster 100 kr
        if (resept.prisAaBetale() == 25) { System.out.println("Test: Pris - Riktig");
        } else { System.out.println("Test: Pris - Feil"); }
    }
}
