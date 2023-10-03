package server;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class MuzickeZelje {
	
	public static BlockingQueue<String> muzickeZelje;
	
	public MuzickeZelje() {
		muzickeZelje = new LinkedBlockingQueue<>(); // ено га, блокирајући ред! ако неко има бољу идеју
	}
	
	public static void ubaciPjesmu(String naziv)
	{
		muzickeZelje.offer(naziv);
		System.out.println(muzickeZelje.peek());
	}
	
	public static String dohvatiPjesmu()
	{
		try {
			return muzickeZelje.take(); // а што нисам полл? 
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Thread.currentThread().interrupt(); 
			return null;
		}
	}

}
