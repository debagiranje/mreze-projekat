package klijent.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import javafx.scene.paint.Color;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;

import javafx.scene.control.ListView;

import javafx.scene.text.Font;
import javafx.stage.Stage;

public class UbaciPjesmuController implements Initializable{
	@FXML
	private Font x1;
	@FXML
	private Font x11;
	@FXML
	private ListView<String> lvSvePjesme;
	@FXML
	private Button btnUbaci;
	@FXML
	private Font x3;
	@FXML
	private Color x4;
	
	public static ArrayList<String> imena = new ArrayList<>();

	// Event Listener on Button[#btnUbaci].onAction
	@FXML
	public void ubaci(ActionEvent event) throws IOException {
		juhu();
		dodaj();
		FXMLLoader glavniLoader = new FXMLLoader();
		glavniLoader.setLocation(getClass().getResource("/klijent/view/RajkoDJ.fxml"));
		Parent root =  glavniLoader.load();
		
		RajkoDJController rdc = glavniLoader.getController();
		rdc.procitajZelje();
		
		Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	private static void loadFileNames() {
        File directory = new File("C:\\Users\\libor\\OneDrive\\Desktop\\altzika\\");
        
        if (directory.isDirectory()) {
            File[] files = directory.listFiles();

            if (files != null) {
                for (File file : files) {
                    imena.add(file.getName());
                }
            }
        } else {
            System.out.println("napravi logger andjela");
        }
    }
	
	private void dodaj() throws IOException
	{
		String pjesma = lvSvePjesme.getSelectionModel().getSelectedItem();
		try(FileWriter fw = new FileWriter("C:\\Users\\libor\\OneDrive\\Documents\\GitHub\\mreze\\mrezni-plejer\\src\\muzicica\\SpisakZelja.txt", true);
			    BufferedWriter bw = new BufferedWriter(fw);
			    PrintWriter out = new PrintWriter(bw))
			{
			    out.println(pjesma);
			} catch (IOException e) {
			    e.printStackTrace();
			}
	}
	
	public void bezze()
	{	
		
	}
	
	public void juhu()
	{
		Konekcija clientManager = Konekcija.getInstance();

        try {
            clientManager.connectToServer("localhost", 5555);
            clientManager.sendMessage("pjesma:"+lvSvePjesme.getSelectionModel().getSelectedItem());

         
        } catch (IOException e) {
            e.printStackTrace();
        }
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		loadFileNames();
		lvSvePjesme.getItems().clear();
        for (String item : imena) {
            if (!lvSvePjesme.getItems().contains(item)) {
                lvSvePjesme.getItems().add(item);
            }
        }
	}
}
