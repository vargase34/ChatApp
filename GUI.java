import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GUI extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	private int theirPort = 0;
	private InetAddress theirAddress;
	private JButton ChatButton = new JButton("Chat");
	private JTextField ip1 = new JTextField(3);
	private JTextField ip2 = new JTextField(3);
	private JTextField ip3 = new JTextField(3);
	private JTextField ip4 = new JTextField(3);
	private JTextField port1 = new JTextField(5);

	public GUI() {
		GridBagConstraints c = new GridBagConstraints();
		JPanel pane = new JPanel(new GridBagLayout());
		JFrame frame = new JFrame("Connect IP");
		JLabel IP = new JLabel("IP Address: ");
		JLabel PORT = new JLabel("Port Number: ");

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(500, 200);
		pane.setLayout(new GridBagLayout());

		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		pane.add(IP, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 0;
		pane.add(ip1, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 2;
		c.gridy = 0;
		pane.add(ip2, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 3;
		c.gridy = 0;
		pane.add(ip3, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 4;
		c.gridy = 0;
		pane.add(ip4, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 1;
		pane.add(PORT, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 1;
		c.gridwidth = 4;
		pane.add(port1, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 5;
		pane.add(ChatButton, c);

		ChatButton.addActionListener(this);

		frame.add(pane);
		frame.setVisible(true);
	}

	private void recordConnect() {
		theirPort = Integer.parseInt(port1.getText());
		String fullIP = ip1.getText() + "." + ip2.getText() + "." + ip3.getText() + "." + ip4.getText();
		try {
			theirAddress = InetAddress.getByName(fullIP);
			System.out.println("Destination IP Address: " + theirAddress);
		} catch (UnknownHostException uhe) {
			uhe.printStackTrace();
			System.exit(-1);
		}
		System.out.println("Destination Port Number: " + theirPort);
		ip1.setText("");
		ip2.setText("");
		ip3.setText("");
		ip4.setText("");
		port1.setText("");
	}

	public void openChatGUI(InetAddress targetAddress, int targetPort) {
		chatGUI chat = new chatGUI(targetAddress, targetPort);
		chat.setVisible(true);
		Test.openConnect.put(targetAddress, chat);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == ChatButton) {
			recordConnect();
			openChatGUI(theirAddress, theirPort);
		}
	}
}