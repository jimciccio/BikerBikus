package ferranti.bikerbikus.controllers;

import ferranti.bikerbikus.data.UserData;
import ferranti.bikerbikus.models.TipoLezione;
import ferranti.bikerbikus.models.Utente;
import ferranti.bikerbikus.queries.LezioniQuery;
import ferranti.bikerbikus.utils.Utils;
import javafx.beans.Observable;
import javafx.beans.value.ObservableObjectValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.*;
import java.time.format.TextStyle;
import java.util.Locale;

import ferranti.bikerbikus.models.Lezione;

public class LezioniController {
	
    private Stage stage;
    private Parent parent;
    private YearMonth currentYearMonth = YearMonth.now();
    private ObservableList<Lezione> lezioni = FXCollections.observableArrayList();

    @FXML
    Button btnBack;
    @FXML
    Button btnPrevMonth;
    @FXML
    Button btnNextMonth;
    @FXML
    Label lblMese;
    @FXML
    Label lblAnno;
    @FXML
    TableView<Lezione> tableLezioni;
    @FXML
    TableColumn<Lezione, LocalDateTime> colGiorno;
    @FXML
    TableColumn<Lezione, LocalDateTime> colOrario;
    @FXML
    TableColumn<Lezione, TipoLezione> colTipo;
    @FXML
    TableColumn<Lezione, Utente> colMaestro;
    @FXML
    TableColumn<Lezione, Integer> colPrenotazione;
    
    public LezioniController() {}

    public void showScene(Stage stage) {
    	this.stage = stage;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/lezioni-view.fxml"));
        fxmlLoader.setController(this);
        try {
            parent = fxmlLoader.load();
        } catch (IOException ex) {
            System.err.println("ERRORE: " +  ex.getCause());
        }
        stage.getScene().setRoot(parent);
        btnBack.setOnAction(event -> onActionBack());
        btnPrevMonth.setOnAction(event -> onActionPrevMonth());
        btnNextMonth.setOnAction(event -> onActionNextMonth());
        colGiorno.setCellFactory(param -> new TableCell<>() {
            @Override
            protected void updateItem(LocalDateTime item, boolean empty) {
                super.updateItem(item, empty);
                setText(item == null ? "" : Utils.uppercase(item.getDayOfWeek().getDisplayName(TextStyle.SHORT, Locale.getDefault())) + " " + item.getDayOfMonth());
            }
        });
        colGiorno.setCellValueFactory(new PropertyValueFactory<>("data"));
        colOrario.setCellFactory(param -> new TableCell<>() {
            @Override
            protected void updateItem(LocalDateTime item, boolean empty) {
                super.updateItem(item, empty);
                setText(item == null ? "" : Utils.formatTime(item.getHour(), item.getMinute()));
            }
        });
        colOrario.setCellValueFactory(new PropertyValueFactory<>("data"));
        colTipo.setCellFactory(param -> new TableCell<>() {
            @Override
            protected void updateItem(TipoLezione item, boolean empty) {
                super.updateItem(item, empty);
                setText(item == null ? "" : item.getNome());
            }
        });
        colTipo.setCellValueFactory(new PropertyValueFactory<>("tipo"));
        colMaestro.setCellFactory(param -> new TableCell<>() {
            @Override
            protected void updateItem(Utente item, boolean empty) {
                super.updateItem(item, empty);
                setText(item == null ? "" : item.getNome() + " " + item.getCognome());
            }
        });
        colMaestro.setCellValueFactory(new PropertyValueFactory<>("maestro"));
        colPrenotazione.setCellFactory(param -> new TableCell<>() {
            @Override
            protected void updateItem(Integer item, boolean empty) {
                super.updateItem(item, empty);
                Button btnPrenota = new Button("Prenota");
                btnPrenota.setPrefSize(150,20);
                btnPrenota.setOnAction(event -> prenotaLezione(item));
                setGraphic(item == null ? null : btnPrenota);
            }
        });
        colPrenotazione.setCellValueFactory(new PropertyValueFactory<>("id"));
        loadLezioni();
        tableLezioni.setItems(lezioni);
    }
    
    public void onActionBack() {
    	new HomeController().showScene(stage);
    }

    public void onActionPrevMonth() {
        currentYearMonth = currentYearMonth.minusMonths(1);
        loadLezioni();
    }

    public void onActionNextMonth() {
        currentYearMonth = currentYearMonth.plusMonths(1);
        loadLezioni();
    }

    private void loadLezioni() {
        lblMese.setText(Utils.uppercase(currentYearMonth.getMonth().getDisplayName(TextStyle.FULL, Locale.getDefault())));
        lblAnno.setText(Integer.toString(currentYearMonth.getYear()));
        retrieveLezioni();
    }

    private void retrieveLezioni() {
        lezioni.clear();
        lezioni.addAll(LezioniQuery.execute(currentYearMonth, UserData.getInstance().getUser().getId()));
    }

    private void prenotaLezione(int idLezione) {
        new Alert(Alert.AlertType.INFORMATION, idLezione + "", ButtonType.OK).show();
    }
}
