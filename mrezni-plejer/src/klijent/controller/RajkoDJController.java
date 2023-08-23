package klijent.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import javafx.scene.paint.Color;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;

import javafx.scene.control.Label;

import javafx.scene.control.ListView;

import javafx.scene.text.Font;
import javafx.stage.Stage;

public class RajkoDJController implements Initializable{
	@FXML
	private Font x1;
	@FXML
	private Label lvIme;
	@FXML
	private Font x11;
	@FXML
	private ListView<String> lvMuzickeZelje;
	@FXML
	private Label lblTrenutnaPjesma;
	@FXML
	private Button btnDodaj;
	@FXML
	private ListView<String> lvDrugari;
	@FXML
	private Button btnIzadji;
	@FXML
	private Font x3;
	@FXML
	private Color x4;
	@FXML
	private Label lblIP;
	@FXML
	private Font x31;
	@FXML
	private Color x41;

	// Event Listener on Button[#btnDodaj].onAction
	@FXML
	public void dodajPjesmu(ActionEvent event) throws IOException{
		FXMLLoader ubaciLoader = new FXMLLoader();
		ubaciLoader.setLocation(getClass().getResource("/klijent/view/UbaciPjesmu.fxml"));
		Parent root =  ubaciLoader.load();
		
		//UbaciPjesmuController pc = ubaciLoader.getController();
		//lvMuzickeZelje.getSelectionModel().getSelectedItem();
		
		Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	// Event Listener on Button[#btnIzadji].onAction
	@FXML
	public void izadji(ActionEvent event) throws IOException {
		FXMLLoader izadjiLoader = new FXMLLoader();
		izadjiLoader.setLocation(getClass().getResource("/klijent/view/KlijentLogin.fxml"));
		Parent root =  izadjiLoader.load();
		
		Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	public void initKorisnik(String IP, String nick) throws IOException {
		lvIme.setText(nick);
		lblIP.setText(IP);
		procitajZelje();
		procitajImena();
		
	}
	
	
	private void procitajZelje() throws IOException
	    {
	        File file = new File("C:\\Users\\libor\\OneDrive\\Documents\\GitHub\\mreze\\mrezni-plejer\\src\\muzicica\\SpisakZelja.txt");
	        //TODO: провјерити због чега мора апсолутна путања...
	        FileReader fr = new FileReader(file);
	      
	        BufferedReader br = new BufferedReader(fr);
	        String pjesma;
	        
	        while ((pjesma = br.readLine()) != null) {
	        	lvMuzickeZelje.getItems().add(pjesma);
	        	System.out.println(pjesma);
	        }
	      
	        br.close();
	        fr.close();
	    }
	
	private void procitajImena() throws IOException
    {
        File file = new File("C:\\Users\\libor\\OneDrive\\Documents\\GitHub\\mreze\\mrezni-plejer\\src\\klijent\\SpisakKlijenata.txt");
        //TODO: провјерити због чега мора апсолутна путања...
        FileReader fr = new FileReader(file);
      
        BufferedReader br = new BufferedReader(fr);
        String pjesma;
        
        while ((pjesma = br.readLine()) != null) {
        	lvDrugari.getItems().add(pjesma);
        	System.out.println(pjesma);
        }
      
        br.close();
        fr.close();
    }
	 
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
	}
	
}
