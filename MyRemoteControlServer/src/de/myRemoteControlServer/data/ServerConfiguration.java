package de.myRemoteControlServer.data;

import java.awt.Dimension;

public class ServerConfiguration {
	
	private ServerConfiguration(){}
	
	public static ServerConfiguration getInstance(){
		if(instance==null){
			instance = new ServerConfiguration();			
		}
		return instance;
	}
	
	public Dimension getResolution() {
		return resolution;
	}
	
	public void setResolution(Dimension resolution) {
		this.resolution = resolution;
	}
	
	public void setResolution(int x, int y) {
		this.resolution = new Dimension(x,y);
	}
	
	private static ServerConfiguration instance;
	private Dimension resolution;
}
