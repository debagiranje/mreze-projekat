package server;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import server.controller.DJRajkoController;

/**
 * Дио сервера задужен за музичке жеље. :)
 * 
 **/

public class ServerZahtjevi {
	
	private ServerSocket socketServer;
	public MuzickeZelje red;
	public List<UpravljacKlijentima> clients = new ArrayList<>();
	public BlockingQueue<String> muzickeZelje;
	private AtomicInteger modificationCounter = new AtomicInteger(0);
	private volatile boolean isPlaying = false;
	private MediaPlayer mediaPlayer;
	
	public ServerZahtjevi()
	{
		this.muzickeZelje = new LinkedBlockingQueue<String>();
	}

	
	public void pokreni() {
		try {
			socketServer = new ServerSocket(5555);
			
			//TODO: можда онемогућити да се сервер инстанцира више од једног пута... a sto ng??
			
			System.out.println("Server slusa na portu 5555. --- NG, dodaj logger, olaksaj sebi");
			
			while(true)
			{
				Socket klijent = socketServer.accept();
				System.out.println("Yippee! klijent povezan");
				
				
				//е, ево га! сваки клијент је засебна нит!
				Thread nitKlijent = new Thread(new UpravljacKlijentima(klijent, this));
				nitKlijent.start();
				
				
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void stop()
	{
		try {
			if(socketServer != null)
			{
				socketServer.close();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void addConnectedClient(UpravljacKlijentima clientHandler) {
        clients.add(clientHandler);
       // System.out.println(clientHandler.getUsername() + " usao");
    }

    public void removeClient(UpravljacKlijentima clientHandler) {
        clients.remove(clientHandler);
        //System.out.println(clientHandler.getUsername() + " izasao");
    }
    
    public synchronized void playSongs() throws MalformedURLException {
    	int localCounter = modificationCounter.get();
        while (true) {
            try {
            	 if (localCounter != modificationCounter.get()) {
            		 if (!isPlaying) {
                     System.out.println("ping! pong!");
                     localCounter = modificationCounter.get();
                     
                     String songFileName = muzickeZelje.take();

           
                     //File f = new File("C:\\Users\\libor\\OneDrive\\Documents\\GitHub\\mreze\\mrezni-plejer\\src\\muzicica\\" + songFileName);

                     String filePath = "C:\\Users\\libor\\OneDrive\\Desktop\\altzika\\" + songFileName;
                     System.out.println(filePath);
                  
                     Media media = new Media(new File(filePath).toURI().toURL().toString());

                     if (mediaPlayer != null) {
                         mediaPlayer.dispose();
                     }

                     mediaPlayer = new MediaPlayer(media);
                     mediaPlayer.play();
                     isPlaying = true;

                     System.out.println("ide: " + songFileName);

                
                     mediaPlayer.setOnEndOfMedia(() -> {
                         mediaPlayer.dispose();
                         isPlaying = false;
                         System.out.println("gotova: " + songFileName);
                     });
                     
                     mediaPlayer.setOnError(() -> {
                    	    System.out.println("ojoj.");
                    	    mediaPlayer.dispose();
                    	    isPlaying = false;
                    	  
                    	});

                 }
            	 }
               

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    
    public void dodaj(String songFileName) {
    	muzickeZelje.offer(songFileName);
        modificationCounter.incrementAndGet();
        System.out.println("ubacena: " + songFileName);

    }


}
