package com.makbe.converter.UI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class GenerateSqlController {
	@FXML
	public TextField tableField;

	@FXML
	public TextField outputField;

	@FXML
	public void handleGenerate(ActionEvent actionEvent) {

	}

	@FXML
	public void handleBack(ActionEvent actionEvent) throws IOException {
		Stage stage = (Stage) ((javafx.scene.Node) actionEvent.getSource()).getScene().getWindow();
		Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("main.fxml")));

		Scene scene = new Scene(root);
		stage.setScene(scene);
	}
}
