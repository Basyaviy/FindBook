package examples;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class SmtpClient {
	 public static void main(String[] args) {
		 
	        String hostname = "smtp.gmail.com";
	        int port = 25;
	 
	        try (Socket socket = new Socket(hostname, port)) {
	 
	            InputStream input = socket.getInputStream();
	 
	            OutputStream output = socket.getOutputStream();
	            PrintWriter writer = new PrintWriter(output, true);
	 
	            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
	 
	            String line = reader.readLine();
	            System.out.println(line);
	 
	            writer.println("helo " + hostname);
	 
	            line = reader.readLine();
	            System.out.println(line);
	 
	            writer.println("quit");
	            line = reader.readLine();
	            System.out.println(line);
	 
	        } catch (UnknownHostException ex) {
	 
	            System.out.println("Server not found: " + ex.getMessage());
	 
	        } catch (IOException ex) {
	 
	            System.out.println("I/O error: " + ex.getMessage());
	        }
	    }
}
