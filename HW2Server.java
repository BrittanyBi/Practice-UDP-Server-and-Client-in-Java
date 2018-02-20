/*
 * HW02 - Server App upon UDP
 * Base model by Weiying Zhu
 * Project modifications by Brittany Bianco
 * 
 */ 
 
import java.io.*;
import java.net.*;
import java.util.*;

public class HW2Server {
   public static void main(String[] args) throws IOException {
      DatagramSocket udpServerSocket = null;
      BufferedReader in = null;
	   DatagramPacket udpPacket = null, udpPacket2 = null;
	   String fromClient = null, toClient = null;
      boolean morePackets = true;
	   byte[] buf = new byte[256];
      
	   udpServerSocket = new DatagramSocket(5050);
      System.out.println("Loaded and awaiting clients.");
	  	  
      while (morePackets) {
         try {
            // receive UDP packet from client
            udpPacket = new DatagramPacket(buf, buf.length);
            udpServerSocket.receive(udpPacket);
			   fromClient = new String(udpPacket.getData(), 0, udpPacket.getLength());
				System.out.println(fromClient);
               
				// get the response
				toClient = getTableData(fromClient);
				 
				// send the response to the client at "address" and "port"
            InetAddress address = udpPacket.getAddress();
            int port = udpPacket.getPort();
				byte[] buf2 = toClient.getBytes();
            udpPacket2 = new DatagramPacket(buf2, buf2.length, address, port);
            udpServerSocket.send(udpPacket2);
				 
         } catch (IOException e) {
            e.printStackTrace();
			   morePackets = false;
         }
      }
	  
      udpServerSocket.close();
   }
   
   public static String getTableData(String id){
      System.out.println("Obtaining data.");
      String itemData;
      int i = Integer.parseInt(id);
      i--;
      HW2Data d = new HW2Data();
       
      itemData = "0000" + (i+1) + "  \t | " 
               + d.items[i][0] + "\t | " 
               + d.items[i][1] + "\t | " 
               + d.items[i][2] + "\t | ";
       
      return itemData;
   }
}