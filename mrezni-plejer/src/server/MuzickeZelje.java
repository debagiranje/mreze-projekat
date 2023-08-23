package server;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class MuzickeZelje {
	
	private static BlockingQueue<String> muzickeZelje;
	
	public MuzickeZelje() {
		muzickeZelje = new LinkedBlockingQueue<>(); // ено га, блокирајући ред! ако неко има бољу идеју
	}
	
	public static void ubaciPjesmu(String naziv)
	{
		muzickeZelje.offer(naziv);
	}
	
	public String dohvatiPjesmu()
	{
		try {
			return muzickeZelje.take(); // а што нисам полл? не баца изузетак, овако испадамо паметнији...
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Thread.currentThread().interrupt();  // notice me senpai --- 
			return null;
		}
	}

}
