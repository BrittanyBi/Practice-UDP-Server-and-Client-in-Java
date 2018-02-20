/*
 * HW02 - Client App upon UDP
 * Base model by Weiying Zhu
 * Project modifications by Brittany Bianco
 * 
 */ 

import java.io.*;
import java.net.*;
import java.util.*;

public class HW2Client {
   static DatagramSocket udpSocket;
   static BufferedReader sysIn;
   
   public static void main(String[] args) throws IOException {
      String serverIP = getServer();
        
      printTable();
        
      udpSocket = new DatagramSocket();

      sysIn = new BufferedReader(new InputStreamReader(System.in));
      String fromServer, fromUser;
      
      
      System.out.println("Please enter an Item ID: ");
        
      while ((fromUser = sysIn.readLine()) != null) {
         if (!validateID(fromUser)){
            System.out.println("Invalid ID. Please enter a valid ID: ");
            continue;
            
         } else {
   			    // ready timers
                long start, finish;
                
                // prepare request
                InetAddress address = InetAddress.getByName(serverIP);
                //System.out.println("Requested item " + fromUser + ". Connecting...");
   			    byte[] buf = fromUser.getBytes();
                DatagramPacket udpPacket = new DatagramPacket(buf, buf.length, address, 5050);
                // record time right before sending the request
                start = System.nanoTime();
                // send request
                udpSocket.send(udpPacket);
       
                // get response
     		       byte[] buf2 = new byte[256];
                DatagramPacket udpPacket2 = new DatagramPacket(buf2, buf2.length);
                udpSocket.receive(udpPacket2);
                // record time right after getting the response
                finish = System.nanoTime();
                
     	          // display response
                fromServer = new String(udpPacket2.getData(), 0, udpPacket2.getLength());
                // find elapsed time
                System.out.println("Item ID \t | Item Description \t | Unit Price \t | Inventory \t | RTT of Query \n" 
                                   + fromServer + ((finish-start)/1000000.0) + " ms");
            }
            
            System.out.println("\nDo you wish to continue? Enter yes or no: ");
            if(!sysIn.readLine().toLowerCase().equals("yes")){
                break;
            }
            
            System.out.println("Please enter an Item ID: ");
	 	  }
		  
        udpSocket.close();
    }
    
    public static String getServer(){
        Scanner sys = new Scanner(System.in);
        System.out.println("Please enter the DNS or IP of the Server: ");
        return sys.nextLine();
    }
    
    public static void printTable(){
        System.out.println("Item ID \t Item Description \n"
                         + "00001 \t New Inspiron 15 \n"
                         + "00002 \t New Inspiron 17 \n"
                         + "00003 \t New Inspiron 15R \n"
                         + "00004 \t New Inspiron 15z Ultrabook \n"
                         + "00005 \t XPS 14 Ultrabook \n"
                         + "00006 \t New XPS 12 UltrabookXPS \n");
    }
    
    public static boolean validateID(String input){
        try{
            int id = Integer.parseInt(input);
            if (id > 0 && id < 7){
                return true;
            }
        } catch(NumberFormatException e){
            // do nothing
        }
        
        return false;
    }
}