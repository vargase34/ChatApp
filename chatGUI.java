import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.InetAddress;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class chatGUI extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	private int theirP;
	private InetAddress theirIP;
	private String messageOut;
	private JButton SendButton = new JButton("Send");
	private JTextField userInput = new JTextField(10);
	private JTextArea ChatBox = new JTextArea(20, 30);

	public chatGUI(InetAddress theirAddress, int theirPort) {
		theirIP = theirAddress;
		theirP = theirPort;

		GridBagConstraints c = new GridBagConstraints();
		JPanel pane = new JPanel(new GridBagLayout());
		JFrame frame = new JFrame(theirIP + " - " + theirP);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.setSize(600, 400);
		pane.setLayout(new GridBagLayout());

		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		pane.add(ChatBox, c);

		ChatBox.setEditable(false);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 1;
		pane.add(userInput, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 2;
		pane.add(SendButton, c);

		SendButton.addActionListener(this);

		frame.add(pane);
		frame.setVisible(true);

	}

	public void theirMessages(String messageIn) {
		ChatBox.append(theirIP + ": " + messageIn + "\n");
	}

	private void myMessages() {
		messageOut = userInput.getText();
		ChatBox.append("Me: " + messageOut + "\n");
		userInput.setText("");
		Test.mySocket.send(messageOut, theirIP, theirP);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == SendButton) {
			myMessages();
		}
	}
}