package pl.edu.agh.dsrg.sr.protos;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class ClientPanel extends JFrame{
		
	public ClientPanel(){
	}
	
		
	public void visualize(){
		Container contentPane = getContentPane();
		
		JPanel left = new JPanel(new BorderLayout());
		JPanel right = new JPanel(new BorderLayout());
		JPanel parent = new JPanel(new GridLayout(1,2));
		JPanel menu = new JPanel(new GridLayout(2,1));

		JTextArea viewInfo = new JTextArea("Type your personal data to the fields to the right. If you do not exist in database, you will be added.Then type the user you want to speak with. He must exist!");
		viewInfo.setLineWrap(true);
		viewInfo.setEditable(false);
		viewInfo.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		JTextArea chatArea = new JTextArea();
		chatArea.setLineWrap(true);
		chatArea.setEditable(true);
		chatArea.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		JTextArea selectionArea = new JTextArea();
		chatArea.setLineWrap(true);
		chatArea.setEditable(true);
		chatArea.setBorder(BorderFactory.createLineBorder(Color.BLACK));
				
		JButton sendButton = new JButton(new AbstractAction("Send"){
			public void actionPerformed(ActionEvent evt){
				
			}
		});
		
		
		JButton Exit = new JButton(new AbstractAction("Exit"){
			public void actionPerformed(ActionEvent evt){
				System.exit(0);
			}
		});	
		left.add(chatArea, BorderLayout.CENTER);
		left.add(sendButton, BorderLayout.SOUTH);
		right.add(viewInfo, BorderLayout.CENTER);
		right.add(Exit,BorderLayout.NORTH);
		menu.add(right);
		menu.add(selectionArea);
		parent.add(left);
		parent.add(menu);
		contentPane.add(parent);
		pack();
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	setVisible(true);
    	setSize(800, 500);
	}
}

