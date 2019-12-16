import java.net.DatagramPacket;
import java.net.InetAddress;
import java.util.HashMap;

public class Test {

	static HashMap<InetAddress, chatGUI> openConnect = new HashMap<>();
	public static Socket mySocket = new Socket(64000);

	public static void main(String[] args) {
		DatagramPacket inPacket = null;
		GUI openFirstGUI = new GUI();
		openFirstGUI.setVisible(true);

		while (true) {
			do {
				inPacket = mySocket.receive();
			} while (inPacket == null);
			byte[] inBuffer = inPacket.getData();
			String inMessage = new String(inBuffer);
			InetAddress senderAddress = inPacket.getAddress();
			int senderPort = inPacket.getPort();
			System.out.println("This is the message sent to me: " + inMessage);
			if (openConnect.containsKey(senderAddress)) {
				openConnect.get(senderAddress).theirMessages(inMessage);
			} else {
				chatGUI chat = new chatGUI(senderAddress, senderPort);
				chat.setVisible(true);
				
				openConnect.put(senderAddress, chat);
				chat.theirMessages(inMessage);
			}
		}
	}
}