public class Kryptograf implements Runnable {

    private Monitor krypterteMeldinger;
    private Monitor dekrypterteMeldinger;

    public Kryptograf(Monitor krypterteMeldinger, Monitor dekrypterteMeldinger) {
        this.krypterteMeldinger = krypterteMeldinger;
        this.dekrypterteMeldinger = dekrypterteMeldinger;
    }

    public void run() {
        while (!krypterteMeldinger.erFerdigMottatt()) {
            Melding melding = krypterteMeldinger.hent();
            String dekryptertMelding = Kryptografi.dekrypter(melding.hentInnhold());
            melding.endreInnhold(dekryptertMelding);
            dekrypterteMeldinger.send(melding);
        }
        dekrypterteMeldinger.signaliserFerdigTrad();
    }
}
