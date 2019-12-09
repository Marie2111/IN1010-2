public class Telegrafist implements Runnable {

    private Kanal kanal;
    private Monitor krypterteMeldinger;

    public Telegrafist(Kanal kanal, Monitor krypterteMeldinger) {
        this.kanal = kanal;
        this.krypterteMeldinger = krypterteMeldinger;
    }

    public void run() {
        String melding = kanal.lytt();
        while (melding != null) {
            krypterteMeldinger.send(new Melding(melding, kanal.hentId()));
            melding = kanal.lytt();
        }
        krypterteMeldinger.signaliserFerdigTrad();
    }
}
