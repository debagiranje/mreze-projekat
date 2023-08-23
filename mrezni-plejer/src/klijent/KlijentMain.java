package klijent;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.MulticastSocket;
import java.net.NetworkInterface;
import java.net.SocketAddress;
import java.net.SocketException;

import server.Constants;

public class KlijentMain {
	
	MulticastSocket socket = null;
	byte[] buffer = null;
	DatagramPacket packet = null;
	InetAddress ip = null;
	SocketAddress sockaddr = null;
 	
	public static void main(String[] args) throws SocketException {
		
		KlijentMain klijent = new KlijentMain();
		klijent.initVariables();
		klijent.connecting();
		
	}
	
	private void initVariables()
	{
			try {
				socket = new MulticastSocket(Constants.PORT);
				ip = InetAddress.getByName(Constants.IP);
				buffer = new byte[Constants.VELICINA_BUFFERA];
				
				sockaddr = new InetSocketAddress(ip, Constants.PORT);
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}
	
	private String recieveData()
	{
		String line = "";
		try
		{
			packet = new DatagramPacket(buffer, buffer.length);
			socket.receive(packet);
			line = new String(packet.getData(), 0, packet.getLength());
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return line;
	}
	
	/*
	 * Ovo moram prouciti, naslijepo sam zbrljala ovaj joingroup metod - jer je sa 1 argumentom deprecated 
	 * Pojma nemam sta je ovaj network interface, i da li mi uopste treba, ali hej! radi
	 * 
	 * Valjda je deprecated jer radimo samo na IP... damn ok 
	 * */
	private void joinGroup() throws SocketException
	{
		 NetworkInterface ni = NetworkInterface.getByName("hme0");
		try {
			socket.joinGroup(sockaddr, ni);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private void connecting() throws SocketException
	{
		joinGroup();
		while(true)
		{
			String line = recieveData();
			log("primio" + line);
			
		}
	}
	
	private void log(String porukica)
	{
		System.out.println("Juhu: " + porukica);
	}
	
	

}
