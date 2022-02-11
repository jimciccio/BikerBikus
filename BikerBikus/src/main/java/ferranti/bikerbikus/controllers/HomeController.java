package ferranti.bikerbikus.controllers;

import ferranti.bikerbikus.utils.Utils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

import java.io.IOException;

import ferranti.bikerbikus.data.UserData;

public class HomeController {

	private Stage stage;
	private Parent parent;

	@FXML
	Label lblUserName;
	@FXML
	Button btnLezioni;
	@FXML
	Button btnPrenotazioni;
	@FXML
	Button btnGare;
	@FXML
	Button btnEscursioni;

	public HomeController() {
	}

	public void showScene(Stage stage) {
		this.stage = stage;
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/home-view.fxml"));
		fxmlLoader.setController(this);
		try {
			parent = fxmlLoader.load();
		} catch (IOException ex) {
			System.err.println("ERRORE: " + ex.getCause());
		}
		stage.getScene().setRoot(parent);
		lblUserName.setText(
				Utils.uppercase(UserData.getInstance().getUser().getNome()) + " " + Utils.uppercase(UserData.getInstance().getUser().getCognome()));
		btnLezioni.setOnAction(event -> onActionLezioni());
		btnPrenotazioni.setOnAction(event -> onActionPrenotazioni());
		btnGare.setOnAction(event -> onActionGare());
		btnEscursioni.setOnAction(event -> onActionEscursioni());

	}

	public void onActionLezioni() {
		new LezioniController().showScene(stage);
	}
	
	public void onActionPrenotazioni() {
		// TODO: Implementare
		new Alert(AlertType.INFORMATION, "TODO", ButtonType.OK).show();
	}
	
	public void onActionGare() {
		// TODO: Implementare
		new Alert(AlertType.INFORMATION, "TODO", ButtonType.OK).show();
	}
	
	public void onActionEscursioni() {
		// TODO: Implementare
		new Alert(AlertType.INFORMATION, "TODO", ButtonType.OK).show();
	}
}
