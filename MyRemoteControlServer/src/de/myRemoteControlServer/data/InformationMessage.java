package de.myRemoteControlServer.data;

public class InformationMessage {

	private InformationMessage(){}
	
	public static InformationMessage getInstance(){
		if(instance==null){
			instance = new InformationMessage();
		}
		return instance;
	}
		
	public boolean isConnected() {
		return connected;
	}

	public void setConnected(boolean connected) {
		this.connected = connected;
	}

	public long getShutdowntime() {
		return shutdowntime;
	}

	public void setShutdowntime(long shutdowntime) {
		this.shutdowntime = shutdowntime;
	}

	public String getShutdownStatus() {
		return shutdownStatus;
	}

	public void setShutdownStatus(String shutdownStatus) {
		this.shutdownStatus = shutdownStatus;
	}

	public void setRatio(double ratio) {
		this.ratio = ratio;
	}
	
	public double getRatio() {
		return ratio;
	}

	public String getMessage(){
		msg = createMessage();
		return msg;
	}
	
	private String createMessage(){
		String msg = "<mrc:info ";
		ServerConfiguration serverConfig = ServerConfiguration.getInstance();
		
		if(serverConfig.getResolution()!=null){
			ratio = serverConfig.getResolution().width / serverConfig.getResolution().height; 
		}else{
			ratio = -1;
		}
		
		msg += "ratio=\""+ratio+"\"";
		msg += "connected=\""+connected+"\"";
		msg += "shutdownTime=\""+shutdowntime+"\"";
		msg += "shutdownStatus=\""+shutdownStatus+"\"";
		
		return msg+" />";
	}
	
	private String msg =  "<mrc:info />";
	private double ratio = -1;
	private boolean connected = false;
	private long shutdowntime = -1;
	private String shutdownStatus = "na";
	private static InformationMessage instance = null;
}
