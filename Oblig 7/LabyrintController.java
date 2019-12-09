import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.application.Platform;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.event.*;
import java.io.File;
import java.io.FileNotFoundException;

import javafx.collections.ObservableList;

public class LabyrintController {

    @FXML private GridPane ruter;
    @FXML private Text status;
    @FXML private Button venstre;
    @FXML private Button hoyre;
    @FXML private Button velgFil;

    private Labyrint labyrint;
    private Liste<String> utveier;
    private int utvei;

    public void initialize() {
        status.setVisible(false);
        venstre.setVisible(false);
        hoyre.setVisible(false);
    }

    @FXML
    protected void velgLabyrintFil(ActionEvent event) {
        // Velg og les inn labyrintfil
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Åpne Labyrintfil");
        File file = fileChooser.showOpenDialog(velgFil.getScene().getWindow());

        try {
            labyrint = Labyrint.lesFraFil(file);
        } catch (FileNotFoundException ex) {}

        // Legg til ruter i rutenettet
        int bredde = (int) (velgFil.getScene().getWidth() / labyrint.hentAntallRader());
        int hoyde = (int) (velgFil.getScene().getWidth() / labyrint.hentAntallKolonner());
        if (hoyde < bredde)
            bredde = hoyde;
        System.out.println(bredde);
        RuteKlikkBehandler behandler = new RuteKlikkBehandler();
        for (int x = 0; x < labyrint.hentAntallRader(); x++) {
            for (int y = 0; y < labyrint.hentAntallKolonner(); y++) {
                Rute rute = labyrint.hentRute(x, y);
                rute.getStyleClass().add("rute");
                if (rute.tilTegn() == '#') {
                    rute.getStyleClass().add("sort");
                } else {
                    rute.getStyleClass().add("hvit");
                }
                rute.setPrefWidth(bredde);
                rute.setPrefHeight(bredde);
                rute.setMinWidth(bredde);
                rute.setMinHeight(bredde);
                rute.setOnAction(behandler);
                ruter.add(rute, x, y);
            }
        }

        status.setVisible(true);
        velgFil.setVisible(false);
    }

    @FXML
    protected void endreLosningMotVenstre() {
        if (utvei - 1 >= 0) {
            utvei--;
        } else {
            utvei = utveier.stoerrelse() - 1;
        }
        visUtvei(utveier.hent(utvei));
    }

    @FXML
    protected void endreLosningMotHoyre() {
        if (utvei + 1 < utveier.stoerrelse()) {
            utvei++;
        } else {
            utvei = 0;
        }
        visUtvei(utveier.hent(utvei));
    }

    private void visUtvei(String s) {
        boolean[][] losningArray = losningStringTilTabell(utveier.hent(utvei), labyrint.hentAntallRader(), labyrint.hentAntallKolonner());
        for (int x = 0; x < labyrint.hentAntallRader(); x++) {
            for (int y = 0; y < labyrint.hentAntallKolonner(); y++) {
                if (losningArray[y][x]) {
                    labyrint.hentRute(x, y).getStyleClass().add("markert");
                } else {

                    labyrint.hentRute(x, y).getStyleClass().removeIf(style -> style.equals("markert"));
                }
            }
        }
        status.setText("Løsning " + (utvei + 1) + "/" + utveier.stoerrelse());
    }

    private class RuteKlikkBehandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent e) {
            Rute r = (Rute) e.getSource();
            utveier = labyrint.finnUtveiFra(r.hentY(), r.hentX());

            if (!utveier.erTom()) {
                utvei = 0;
                visUtvei(utveier.hent(utvei));
                venstre.setVisible(true);
                hoyre.setVisible(true);
            } else {
                status.setText("Fant ingen utveier! Velg en ny rute");
            }
        }
    }

    private boolean[][] losningStringTilTabell(String losningString, int bredde, int hoyde) {
        boolean[][] losning = new boolean[hoyde][bredde];
        java.util.regex.Pattern p = java.util.regex.Pattern.compile("\\(([0-9]+),([0-9]+)\\)");
        java.util.regex.Matcher m = p.matcher(losningString.replaceAll("\\s",""));
        while (m.find()) {
            int x = Integer.parseInt(m.group(1));
            int y = Integer.parseInt(m.group(2));
            losning[y][x] = true;
        }
        return losning;
    }
}
