import java.net.*;
public class DatagramServer {
	public static void main(String[] args) {
		DatagramSocket skt = null;
		
		try
		{
			skt = new DatagramSocket(6788);
			byte[] buffer = new byte[1000];
			while(true)
			{
				DatagramPacket request = new DatagramPacket(buffer,buffer.length);
				skt.receive(request);
				String[] message = (new String(request.getData())).split(" ");
				byte[] sendMsg = (message[1] + "Network Laboratory").getBytes();
				DatagramPacket reply = new DatagramPacket(sendMsg, sendMsg.length, request.getAddress(), request.getPort());
				skt.send(reply);
				System.out.println("System communicated.");
			}
		}
		catch(Exception e) {}
	}
}
