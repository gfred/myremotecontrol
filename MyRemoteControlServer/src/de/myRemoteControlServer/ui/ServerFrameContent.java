package de.myRemoteControlServer.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import de.myRemoteControlServer.control.Logger;

@SuppressWarnings("serial")
public class ServerFrameContent extends JPanel{
	public ServerFrameContent() {		
		information = new ServerInformation();
		textArea = new JTextArea();		
		logger = Logger.getInstance();
		
		textArea.setEditable(false)	;
		textArea.setBackground(Color.BLACK);
		textArea.setForeground(Color.WHITE);
		
		
		
		JScrollPane scrollpane = new JScrollPane(textArea);
		scrollpane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);	
		
//		setLayout(new GridLayout(1,2));
		setLayout(new BorderLayout());
		add(scrollpane,BorderLayout.CENTER);
		add(information,BorderLayout.EAST);
		
		new LogUpdateClass().start();
	}
	
	private JTextArea textArea = null;
	private ServerInformation information = null;
	private Logger logger = null;
	
	private class LogUpdateClass extends Thread{		
		@Override
		public void run() {
			while(true){
				if(logger!=null && textArea!=null && information!=null){
					List<String> log = logger.getLog();
					String textLog = "";
					for(int i=0;i<log.size();i++){
						textLog +=log.get(i)+"\n";
					}
					textArea.setText(textLog);
					textArea.setCaretPosition(textArea.getText().length());
				}
			}
		}		
	}
}

