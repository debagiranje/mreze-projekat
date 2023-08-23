package klijent.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import javafx.scene.control.TextField;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javafx.event.ActionEvent;

import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.Parent;


public class KlijentLoginController {
	@FXML
	private Font x1;
	@FXML
	private Font x11;
	@FXML
	private TextField tfIP;
	@FXML
	private TextField tfNick;
	@FXML
	private Button btnUdji;

	private void upisiIme(String nick)
	{
		try(FileWriter fw = new FileWriter("C:\\Users\\libor\\OneDrive\\Documents\\GitHub\\mreze\\mrezni-plejer\\src\\klijent\\SpisakKlijenata.txt", true);
			    BufferedWriter bw = new BufferedWriter(fw);
			    PrintWriter out = new PrintWriter(bw))
			{
			    out.println(nick);
			} catch (IOException e) {
			    //exception handling left as an exercise for the reader
			}
	}
	// Event Listener on Button[#btnUdji].onAction
	@FXML
	public void udji(ActionEvent event) throws IOException {
		String IP = tfIP.getText().strip();
		String nick = tfNick.getText().strip();
		
		upisiIme(nick);
		
		FXMLLoader glavniLoader = new FXMLLoader();
		glavniLoader.setLocation(getClass().getResource("/klijent/view/RajkoDJ.fxml"));
		Parent root =  glavniLoader.load();
		
		RajkoDJController rdc = glavniLoader.getController();
		rdc.initKorisnik(IP, nick);
		
		Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
		
	}
}
