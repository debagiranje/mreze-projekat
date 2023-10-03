package server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class UpravljacKlijentima implements Runnable {
	
	protected Socket socketKlijent;
	 private String username;
	 private ServerZahtjevi server;

	public UpravljacKlijentima(Socket klijent, ServerZahtjevi server) {
		socketKlijent = klijent;
		this.server = server;
	}

	@Override
	public void run() {
		try {
			// у/и токови за комуникацију са клијентом
			BufferedReader citac = new BufferedReader(new InputStreamReader(socketKlijent.getInputStream()));
			//PrintWriter pisac = new PrintWriter(socketKlijent.getOutputStream(), true);
			
			// музичка жеља
			String poruka = citac.readLine();
			if (poruka.startsWith("ime:")) {
				System.out.println("NG pls napravi logger ---  ime je: " + poruka.split("ime:")[1]);
				server.addConnectedClient(this);
            } else if (poruka.startsWith("pjesma:")) {
                ubaci(poruka.split("pjesma:")[1]);
                System.out.println("Jupi, ubacio sam pjesmicu!");
            }
			
			
			citac.close();
			//pisac.close();
			
			
			socketKlijent.close();
			
		}catch (Exception e) {
			// TODO: handle exception
		}

	}
	
	private void ubaci(String zahtjev)
	{
		// if zahtjev dobar... a samo moze i biti dobar 
		server.dodaj(zahtjev);
	}
	
	public String getUsername() {
        return username;
    }

}
