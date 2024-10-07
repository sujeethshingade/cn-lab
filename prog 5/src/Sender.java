import java.net.*;

public class Sender {
	public static void main(String[] args) throws Exception {
		DatagramSocket socket = new DatagramSocket();
		InetAddress receiverAddress = InetAddress.getByName("localhost");
		int receiverPort = 9876;
		int windowSize = 4;
		byte[] data = "Hello, SlidingWindow".getBytes();
		int base = 0;

		while (base < data.length) {
			for (int i = base; i < base + windowSize && i < data.length; i++) {
				DatagramPacket packet = new DatagramPacket(data, i, 1, receiverAddress, receiverPort);
				System.out.println("Sending packet with index: " + i);
				socket.send(packet);
			}

			DatagramPacket ackPacket = new DatagramPacket(new byte[1], 1);
			System.out.println("Waiting for Ack");
			socket.receive(ackPacket);
			int ack = ackPacket.getData()[0];

			base = ack;
			//if (ack >= base) {
			//	base = ack + 1;
			//}
			
			System.out.println("Received ack:" + ack + " base:" + base + " data length:" + data.length);

		}

		socket.close();
	}
}