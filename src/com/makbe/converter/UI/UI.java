package com.makbe.converter.UI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class UI extends Application {

	@Override
	public void start(Stage stage) throws IOException {
		stage.setTitle("CSV to SQL Converter");

		Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("main.fxml")));

		Scene scene = new Scene(root);
		stage.setResizable(false);
		stage.centerOnScreen();
		stage.setScene(scene);
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
