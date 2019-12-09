public class TestLegemiddel {

    // Tester at Legemiddel fungerer som det skal (dette er også en test for Legemiddel C)
    public static void testLegemiddel() {
        Legemiddel a = new LegemiddelC("Et Legemiddel", 100, 3);

        // Test ID - Forventet ID er 0 (Her antar vi at dette er det første objektet av legemiddel som opprettes)
        if (a.hentID() == 0) { System.out.println("Test: ID - Riktig");
        } else { System.out.println("Test: ID - Feil"); }

        // Test Navn - Forventet Navn er det konstruktøren til Legemiddel tar inn
        if (a.hentNavn() == "Et Legemiddel") { System.out.println("Test: Navn - Riktig");
        } else { System.out.println("Test: Navn - Feil"); }

        // Test Pris - Forventet Pris er det konstruktøren til Legemiddel tar inn
        if (a.hentPris() == 100) { System.out.println("Test: Pris - Riktig");
        } else { System.out.println("Test: Pris - Feil"); }

        // Test Virkestoff - Forventet Virkestoff er det konstruktøren til Legemiddel tar inn
        if (a.hentVirkestoff() == 3) { System.out.println("Test: Virkestoff - Riktig");
        } else { System.out.println("Test: Virkestoff - Feil"); }
    }

    // Test at Legemiddel A fungerer som det skal
    public static void testLegemiddelA() {
        LegemiddelA l = new LegemiddelA("Et Legemiddel A", 125, 5, 8);

        // Test Narkostisk Styrke - Forventet resultat er det konstruktøren tar inn
        if (l.hentNarkotiskStyrke() == 8) { System.out.println("Test: Narkotisk Styrke - Riktig");
        } else { System.out.println("Test: Narkotisk Styrke - Feil"); }
    }

    // Tester at Legemiddel B fungerer som det skal
    public static void testLegemiddelB() {
        LegemiddelB l = new LegemiddelB("Et Legemiddel B", 125, 5, 10);

        if (l.hentVanedannendeStyrke() == 10) { System.out.println("Test: Vanedannende Styrke - Riktig");
        } else { System.out.println("Test: Vanedanndende Styrke - Feil"); }
    }

}
