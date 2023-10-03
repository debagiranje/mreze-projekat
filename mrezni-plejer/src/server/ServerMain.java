package server;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.MalformedURLException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ServerMain extends Application{
 

    @Override
	public void start(Stage primaryStage) {
		try {	
			FXMLLoader ServerLoader = new FXMLLoader(getClass().getResource("/server/view/DJRajko.fxml"));		
			
			Parent root =  ServerLoader.load();
			Scene scene = new Scene(root,900,600);
			primaryStage.setScene(scene);
			primaryStage.show();
			
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		//launch(args);
		isprazniDatoteke("C:\\Users\\libor\\OneDrive\\Documents\\GitHub\\mreze\\mrezni-plejer\\src\\klijent\\SpisakKlijenata.txt");
		isprazniDatoteke("C:\\Users\\libor\\OneDrive\\Documents\\GitHub\\mreze\\mrezni-plejer\\src\\muzicica\\SpisakZelja.txt");
		ServerZahtjevi server = new ServerZahtjevi();
		new Thread(() -> {
            server.pokreni();
        }).start();
		
		new Thread(() -> {
            try {
				server.playSongs();
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }).start();
        
		launch(args);
	}
	
	private static void isprazniDatoteke(String filePath) {
	    try {
	        RandomAccessFile file = new RandomAccessFile(new File(filePath), "rw");
	        file.setLength(0);
	        file.close();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
}


