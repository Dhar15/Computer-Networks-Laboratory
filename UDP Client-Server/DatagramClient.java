//Run the server first, and after the connection is established proceed with running the client side.

import java.net.*;
import java.io.*;
public class DatagramClient {
	public static void main(String[] args) {
		DatagramSocket skt;
		
		try
		{
			skt = new DatagramSocket();
			String msg = "a udp";
			byte[] b = msg.getBytes();
			int port = 6788;
			InetAddress host = InetAddress.getByName("127.0.0.1");
			DatagramPacket request = new DatagramPacket(b,b.length,host,port);
			skt.send(request);
			byte buffer[] = new byte[1000];
			DatagramPacket reply = new DatagramPacket(buffer,buffer.length);
			skt.receive(reply);
			System.out.println("Client Recieved: " + new String(reply.getData()));
			skt.close();
		} catch(Exception e) {}
	}
}
