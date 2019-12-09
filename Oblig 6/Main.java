public class Main {

    public static final int MAKS_TELEGRAFER = 3;
    public static final int ANTALL_KRYPTOGRAFER = 20;

    public static void main(String[] args) {
        Operasjonssentral ops = new Operasjonssentral(MAKS_TELEGRAFER);
        Kanal[] kanaler = ops.hentKanalArray();
        int antallTelegrafer = kanaler.length; // Antall kanaler = Antall telegrafer

        Monitor krypterteMeldinger = new Monitor(antallTelegrafer);
        Monitor dekrypterteMeldinger = new Monitor(ANTALL_KRYPTOGRAFER);

        Thread[] telegrafer = new Thread[antallTelegrafer];
        Thread[] kryptografer = new Thread[ANTALL_KRYPTOGRAFER];

        // Opprett og sett i gang telegrafer
        for (int i = 0; i < antallTelegrafer; i++) {
            Telegrafist telegraf = new Telegrafist(kanaler[i], krypterteMeldinger);
            telegrafer[i] = new Thread(telegraf);
            telegrafer[i].start();
        }

        // Opprett og sett i gang kryptografer
        for (int i = 0; i < ANTALL_KRYPTOGRAFER; i++) {
            Kryptograf kryptograf = new Kryptograf(krypterteMeldinger, dekrypterteMeldinger);
            kryptografer[i] = new Thread(kryptograf);
            kryptografer[i].start();
        }

        // Opprett og sett i gang operasjonsleder
        Operasjonsleder opsLeder = new Operasjonsleder(dekrypterteMeldinger, antallTelegrafer);
        Thread leder = new Thread(opsLeder);
        leder.start();

        krypterteMeldinger.vent();
        System.out.println("=====> Telegrafer er ferdig");
        dekrypterteMeldinger.vent();
        System.out.println("=====> Kryptografer er ferdig");
    }
}
