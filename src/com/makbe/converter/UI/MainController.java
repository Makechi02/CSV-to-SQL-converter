package com.makbe.converter.UI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class MainController {
	@FXML
	private TextField filePathField;

	private FileChooser fileChooser;

	@FXML
	private void initialize() {
		fileChooser = new FileChooser();
		fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV Files", "*.csv"));
	}

	@FXML
	private void browseCsvFile(ActionEvent actionEvent) {
		Stage stage = (Stage) ((javafx.scene.Node) actionEvent.getSource()).getScene().getWindow();
		File selectedFile = fileChooser.showOpenDialog(stage);
		if (selectedFile != null) {
			filePathField.setText(selectedFile.getAbsolutePath());
		}
	}

	@FXML
	private void generateSqlFile(ActionEvent actionEvent) throws IOException {
		String csvFilePath = filePathField.getText();

		if (csvFilePath.isBlank()) {
			Alert alert = new Alert(Alert.AlertType.WARNING, "CSV Path is blank");
			alert.showAndWait();
			filePathField.requestFocus();
			return;
		}

		Stage stage = (Stage) ((javafx.scene.Node) actionEvent.getSource()).getScene().getWindow();
		Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("generate-sql.fxml")));

		Scene scene = new Scene(root);
		stage.setScene(scene);
	}

	@FXML
	private void populateDatabase(ActionEvent actionEvent) throws IOException {
		String csvFilePath = filePathField.getText();

		if (csvFilePath.isBlank()) {
			Alert alert = new Alert(Alert.AlertType.WARNING, "CSV Path is blank");
			alert.showAndWait();
			filePathField.requestFocus();
			return;
		}

		Stage stage = (Stage) ((javafx.scene.Node) actionEvent.getSource()).getScene().getWindow();
		Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("populate-database.fxml")));

		Scene scene = new Scene(root);
		stage.setScene(scene);
	}

}
