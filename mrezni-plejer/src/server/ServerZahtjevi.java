package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Дио сервера задужен за музичке жеље. :)
 * 
 **/

public class ServerZahtjevi {
	
	private ServerSocket socketServer;
	public MuzickeZelje red;
	
	public void start() {
		try {
			socketServer = new ServerSocket(5556);
			red = new MuzickeZelje(); // 1 инстанца сервера - 1 ред музичких жеља
			
			//TODO: можда онемогућити да се сервер инстанцира више од једног пута...
			
			System.out.println("Server slusa na portu 5556. --- NG, dodaj logger, olaksaj sebi");
			
			while(true)
			{
				Socket klijent = socketServer.accept();
				System.out.println("Yippee! klijent povezan");
				
				//е, ево га! сваки клијент је засебна нит!
				Thread nitKlijent = new Thread(new UpravljacKlijentima(klijent));
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

}
