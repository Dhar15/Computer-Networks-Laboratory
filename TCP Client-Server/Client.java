/*Using TCP/IP sockets, write a client-server program to make the client send the file name and to make the server send back the
   contents of the requested file if present.
   
Note: Be sure to create a file (say Sample.txt) in the package directory, and pass "Sample.txt" as the file name when prompted to. */

import java.io.*;
import java.net.*;
public class Client {
	public static void main(String[] args) throws Exception {
		Socket sock = new Socket("127.0.0.1", 4999);
		System.out.print("Enter the filename-");
		BufferedReader keyRead = new BufferedReader(new InputStreamReader(System.in));
		String fname = keyRead.readLine();
		OutputStream ostream = sock.getOutputStream();
		PrintWriter pwrite = new PrintWriter(ostream, true);
		pwrite.println(fname);
		InputStream istream = sock.getInputStream();
		BufferedReader socketRead = new BufferedReader(new InputStreamReader(istream));
		String str;
		while((str = socketRead.readLine()) != null) {
				System.out.println(str);
		}
		sock.close();
		pwrite.close();
		socketRead.close();
		keyRead.close();
	}
}


