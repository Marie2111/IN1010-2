public class Main {
    public static void main(String[] args) {

        // Legemiddel
        LegemiddelA a = new LegemiddelA("Et Legemiddel", 95.90, 2.0, 5);
        LegemiddelB b = new LegemiddelB("Et annet legemiddel", 99.90, 5.0, 2);
        LegemiddelC c = new LegemiddelC("Enda et legemiddel", 149.90, 2.0);

        // Lege
        Lege lege = new Lege("En lege");
        Fastlege fastlege = new Fastlege("En annen lege", 3);

        // Resept
        BlaaResept blaaResept = new BlaaResept(a, lege, 1, 2);
        PResept pResept = new PResept(b, fastlege, 2);
        Militaerresept militaerResept = new Militaerresept(c, lege, 3, 1);

        // Skriv ut alt mulig
        System.out.println("Lege:");
        lege.skrivUt();
        System.out.println();

        System.out.println("Fastlege:");
        fastlege.skrivUt();
        System.out.println();

        System.out.println("Legemiddel A:");
        a.skrivUt();
        System.out.println();

        System.out.println("Legemiddel B:");
        b.skrivUt();
        System.out.println();

        System.out.println("Legemiddel C:");
        c.skrivUt();
        System.out.println();

        System.out.println("Blåresept:");
        blaaResept.skrivUt();
        System.out.println();

        System.out.println("P-Resept:");
        pResept.skrivUt();
        System.out.println();

        System.out.println("Militærresept");
        militaerResept.skrivUt();
        System.out.println();
    }
}
