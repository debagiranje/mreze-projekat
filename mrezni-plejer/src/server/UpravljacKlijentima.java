package server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class UpravljacKlijentima implements Runnable {
	
	protected Socket socketKlijent;

	public UpravljacKlijentima(Socket klijent) {
		socketKlijent = klijent;
	}

	@Override
	public void run() {
		try {
			// у/и токови за комуникацију са клијентом
			BufferedReader citac = new BufferedReader(new InputStreamReader(socketKlijent.getInputStream()));
			PrintWriter pisac = new PrintWriter(socketKlijent.getOutputStream(), true);
			
			// музичка жеља
			String zahtjev = citac.readLine();
			System.out.println("NG pls napravi loger ---  pjesma je: " + zahtjev);
			
			// е, овдје ће ићи испитивање да ли постоји та пјесма на серверу и да ли је већ једном (два три) пута додата у ред
			// углавном, не знам да ли да онемогућимо кориснику да спамује са пјесмом
			// да не буде да слушамо жарета и гоција док је ресурса (што можда и није лоше...)
			
			// TODO: обрада захтјева, враћање одговора кориснику (кроз ГКИ)?
			
			// TODO: нек писач штогод врати кориснику
			
			ubaci(zahtjev);
			
			pisac.println("Jupi, ubacio sam pjesmicu!");
			
			citac.close();
			pisac.close();
			
			
			socketKlijent.close();
			
		}catch (Exception e) {
			// TODO: handle exception
		}

	}
	
	private void ubaci(String zahtjev)
	{
		// if zahtjev dobar...
		
		MuzickeZelje.ubaciPjesmu(zahtjev);
	}

}
