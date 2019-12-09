import java.util.ArrayList;
import java.util.concurrent.*;
import java.util.concurrent.locks.*;

public class Monitor {

    private ArrayList<Melding> meldinger;
    private CountDownLatch barriere;
    private boolean mottarMeldinger;
    private Lock laas;
    private Condition ikkeTom;
    private Condition ikkeFull;

    public Monitor(int antallTrader) {
        meldinger = new ArrayList<Melding>();
        barriere = new CountDownLatch(antallTrader);
        mottarMeldinger = true;
        laas = new ReentrantLock();
        ikkeTom = laas.newCondition();
        ikkeFull = laas.newCondition();
    }

    public void signaliserFerdigTrad() {
        barriere.countDown();
    }

    // Vent til alle trader er ferdig
    public void vent() {
        try {
            barriere.await();
            mottarMeldinger = false;
        } catch (InterruptedException ex) {}
    }

    public void send(Melding melding) {
        laas.lock();
        try {
            while (meldinger.size() >= 10) {
                ikkeFull.await();
            }

            meldinger.add(melding);
            ikkeTom.signal();
        } catch (InterruptedException ex) {
        } finally {
            laas.unlock();
        }
    }

    public Melding hent() {
        laas.lock();
        try {
            while (meldinger.isEmpty()) {
                ikkeTom.await();
            }

            Melding melding = meldinger.remove(meldinger.size() - 1);
            ikkeFull.signal();
            return melding;
        } catch (InterruptedException ex) {
        } finally {
            laas.unlock();
        }
        return null;
    }

    public boolean erFerdigMottatt() {
        return (!mottarMeldinger && meldinger.isEmpty());
    }
}
