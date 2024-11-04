import java.util.Scanner;
import java.net.*;

public class UDPS {
    public static void main(String[] args) {
        DatagramSocket skt = null;
        String ch;
        Scanner input = new Scanner(System.in);
        try {
            skt = new DatagramSocket(6788);
            byte[] buffer = new byte[1000];
            while (true) {
                DatagramPacket request = new DatagramPacket(buffer, buffer.length);
                skt.receive(request);
                System.out.print("enter the data");
                ch = input.nextLine();
                byte[] sendMsg = (ch + " server processed").getBytes();
                DatagramPacket reply = new DatagramPacket(sendMsg, sendMsg.length, request.getAddress(),
                        request.getPort());
                skt.send(reply);
            }
        } catch (Exception ex) {
        }
        input.close();
    }
}