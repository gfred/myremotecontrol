package de.myRemoteControlServer.ui;

import java.awt.BorderLayout;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.UIManager;

import de.myRemoteControlServer.control.Logger;
import de.myRemoteControlServer.data.ServerConfiguration;

@SuppressWarnings("serial")
public class ServerFrame extends JFrame{
	
	public ServerFrame() {
		super("My Remote Control Server");
		setSize(1200, 500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		serverConfig = ServerConfiguration.getInstance();		
		serverConfig.setResolution(getToolkit().getScreenSize().width, getToolkit().getScreenSize().height);
		
		try{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}catch (Exception e) {}
		
		addWindowListener(new WindowListener() {			
			@Override
			public void windowOpened(WindowEvent arg0) {}			
			@Override
			public void windowIconified(WindowEvent arg0) {}			
			@Override
			public void windowDeiconified(WindowEvent arg0) {}			
			@Override
			public void windowDeactivated(WindowEvent arg0) {}
			@Override
			public void windowClosed(WindowEvent arg0) {}			
			@Override
			public void windowActivated(WindowEvent arg0) {}
			@Override
			public void windowClosing(WindowEvent arg0) {
				Logger.getInstance().addMessage("Shutdown Server");
				Logger.getInstance().writeLog();
			}			
		});
		
		content = new ServerFrameContent();
		
		add(content,BorderLayout.CENTER);
		
		setVisible(true);
	}
	
	private ServerConfiguration serverConfig;
	private ServerFrameContent content;
}

