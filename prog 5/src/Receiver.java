import java.net.*;

public class Receiver {
	public static void main(String[] args) throws Exception {
		try (DatagramSocket socket = new DatagramSocket(9876)) {
            int expectedSeqNum = 0;
            int windowSize = 4;
            while (true) {
            	DatagramPacket packet = new DatagramPacket(new byte[1], 1);
            	System.out.println("Waiting for packet");
            	socket.receive(packet);
            	int data = packet.getData()[0];
            	expectedSeqNum++;
            	System.out.println("Received data:" + (char)data + "  ExpectedSeqNum:" +  expectedSeqNum);

            	if (expectedSeqNum%windowSize == 0) {
            		System.out.println("Sending expectedSeqNum : " + expectedSeqNum);
            		DatagramPacket ackPacket = new DatagramPacket(new byte[] { (byte) expectedSeqNum }, 1, packet.getAddress(), packet.getPort());
            		socket.send(ackPacket);
            	}
            }
        }
	}
}