import java.util.Iterator;
import java.util.NoSuchElementException;

public class Lenkeliste<T> implements Liste<T> {

    private class Node {
        public Node neste;
        public Node forrige;
        public T data;

        public Node(T data) {
            this.data = data;
        }
    }

    private class LenkelisteIterator implements Iterator<T> {
        private Node node;

        public LenkelisteIterator(Node start) {
            node = start;
        }

        public boolean hasNext() {
            return node != null;
        }

        public T next() {
            if (this.hasNext()) {
                T data = node.data;
                node = node.neste;
                return data;
            }
            throw new NoSuchElementException();
        }

        public void remove() {}
    }


    private Node start;
    private Node slutt;
    private int lengde;

    public Iterator iterator() {
        return new LenkelisteIterator(start);
    }

    public boolean erTom() {
        return this.lengde == 0;
    }

    public int stoerrelse() {
        return this.lengde;
    }

    public void leggTil(int pos, T x) {
        if (pos == 0 && lengde == 0) {
            leggTil(x);
            return;
        }

        if (pos < 0 || pos > lengde)
            throw new UgyldigListeIndeks(pos);

        if (pos == lengde) {
            leggTil(x);
            return;
        }

        Node node = finnNode(pos);
        Node ny = new Node(x);

        if (node.forrige != null) {
            node.forrige.neste = ny;
            ny.forrige = node.forrige;
        }

        ny.neste = node;
        node.forrige = ny;

        // Oppdater Start og Slutt
        if (ny.forrige == null)
            start = ny;

        if (node.neste == null)
            slutt = node;

        lengde++;
    }

    public void leggTil(T x) {
        Node node = new Node(x);

        // Har vi en tom liste?
        if (lengde <= 0) {
            start = node;
            slutt = node;
        } else {
            node.forrige = slutt;
            slutt.neste = node;
            slutt = node;
        }

        lengde++;
    }

    public void sett(int pos, T x) {
        if (lengde <= 0 || pos < 0 || pos >= lengde)
            throw new UgyldigListeIndeks(pos);

        Node node = finnNode(pos);
        node.data = x;
    }

    public T hent(int pos) {
        if (lengde <= 0 || pos >= lengde || pos < 0)
            throw new UgyldigListeIndeks(pos);

        Node node = start;

        for (int i = 0; i < pos; i++) {
            node = node.neste;
        }

        return node.data;
    }

    public T fjern(int pos) {
        if (lengde <= 0 || pos < 0 || pos >= lengde)
            throw new UgyldigListeIndeks(pos);

        if (pos == 0)
            return fjern();

        Node node = finnNode(pos);
        Node forrige = node.forrige;
        Node neste = node.neste;

        if (forrige != null)
            forrige.neste = node.neste;

        if (neste != null)
            neste.forrige = node.forrige;

        if (forrige.neste == null)
            slutt = forrige;

        lengde--;
        return node.data;
    }

    public T fjern() {
        if (lengde <= 0)
            throw new UgyldigListeIndeks(-1);

        T data = start.data;
        start = start.neste;

        // Om Start og Slutt er samme node må slutt også fjernes
        if (lengde == 1)
            slutt = null;

        lengde--;
        return data;
    }

    private Node finnNode(int indeks) {
        if (indeks == 0)
            return start;

        if (indeks == lengde - 1)
            return slutt;

        Node n = null;
        boolean b = indeks < (lengde / 2); // Naermest start?

        // Om vi leter etter en node som er nærmere slutten enn starten av listen så er det raskere å lete fra slutten og framover mot starten i stedet
        if (b) {
            n = start;
            for (int i = 0; i < indeks; i++) {
                n = n.neste;
            }
        } else {
            n = slutt;
            for (int i = lengde - 1; i > indeks; i--) {
                n = n.forrige;
            }
        }

        return n;
    }
}
