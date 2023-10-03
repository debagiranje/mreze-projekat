package klijent.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import javafx.scene.control.TextField;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;

import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.Parent;


public class KlijentLoginController implements Initializable{
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
	
	public Socket socket;

	private void upisiIme(String nick)
	{
		try(FileWriter fw = new FileWriter("C:\\Users\\libor\\OneDrive\\Documents\\GitHub\\mreze\\mrezni-plejer\\src\\klijent\\SpisakKlijenata.txt", true);
			    BufferedWriter bw = new BufferedWriter(fw);
			    PrintWriter out = new PrintWriter(bw))
			{
			    out.println(nick);
			} catch (IOException e) {
			    e.printStackTrace();
			}
	}
	
	
	public void juhu()
	{
		Konekcija clientManager = Konekcija.getInstance();

        try {
      
            clientManager.connectToServer("localhost", 5555);
            clientManager.sendMessage("ime:"+tfNick.getText().strip());
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	public Socket getSocket() {
		return socket;
	}
	
	// Event Listener on Button[#btnUdji].onAction
	@FXML
	public void udji(ActionEvent event) throws IOException {
		String IP = tfIP.getText().strip();
		String nick = tfNick.getText().strip();
	
		
		FXMLLoader glavniLoader = new FXMLLoader();
		glavniLoader.setLocation(getClass().getResource("/klijent/view/RajkoDJ.fxml"));
		Parent root =  glavniLoader.load();
		
		RajkoDJController rdc = glavniLoader.getController();
		rdc.initKorisnik(IP, nick);
		
		upisiIme(nick);
		
		juhu();
		
		Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
		
	}


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		tfIP.setText("localhost");
		
	}
}
