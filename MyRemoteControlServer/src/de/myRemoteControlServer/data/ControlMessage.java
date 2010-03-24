package de.myRemoteControlServer.data;

public class ControlMessage {
	
	private ControlMessage(){
		infoMessage = InformationMessage.getInstance();
	}
	
	public static ControlMessage getInstance(){
		if(instance == null){
			instance = new ControlMessage();
		}
		return instance;
	}
	
	public long getTime() {
		return shutdownTime;
	}
	public void setTime(long time) {
		this.shutdownTime = time;
	}
	public int getVolume() {
		return volume;
	}
	public void setVolume(int volume) {
		this.volume = volume;
	}
	public boolean isMute() {
		return mute;
	}
	public void setMute(boolean mute) {
		this.mute = mute;
	}
	public int getMousePosX() {
		return mousePosX;
	}
	public void setMousePosX(int mousePosX) {
		this.mousePosX = mousePosX;
	}
	public int getMousePosY() {
		return mousePosY;
	}
	public void setMousePosY(int mousePosY) {
		this.mousePosY = mousePosY;
	}
	public boolean isMouseChanged() {
		return mouseChanged;
	}
	public void setMouseChanged(boolean mouseChanged) {
		this.mouseChanged = mouseChanged;
	}
	public boolean isKeyChanged() {
		return keyChanged;
	}
	public void setKeyChanged(boolean keyChanged) {
		this.keyChanged = keyChanged;
	}
	public String getMouse() {
		return mouse;
	}
	public void setMouse(String mouse) {
		this.mouse = mouse;
	}
	public char getKey() {
		return key;
	}
	public void setKey(char key) {
		this.key = key;
	}	
	public boolean isShutdownChanged() {
		return shutdownChanged;
	}
	public void setShutdownChanged(boolean shutdownChanged) {
		this.shutdownChanged = shutdownChanged;
	}
	public boolean isVolumeChanged() {
		return volumeChanged;
	}
	public void setVolumeChanged(boolean volumeChanged) {
		this.volumeChanged = volumeChanged;
	}
	public long getShutdownTime() {
		return shutdownTime;
	}
	public void setShutdownTime(long shutdownTime) {
		this.shutdownTime = shutdownTime;
	}
	public String getShutdownStatus() {
		return shutdownStatus;
	}
	public void setShutdownStatus(String shutdownStatus) {
		this.shutdownStatus = shutdownStatus;
	}
	public int getMouseClicked() {
		return mouseClicked;
	}
	public void setMouseClicked(int mouseClicked) {
		this.mouseClicked = mouseClicked;
	}
	public void setMouseWheelChanged(boolean mouseWheelChanged) {
		this.mouseWheelChanged = mouseWheelChanged;
	}
	public boolean isMouseWheelChanged() {
		return mouseWheelChanged;
	}
	public boolean isMouseClickedChanged() {
		return mouseClickedChanged;
	}
	public void setMouseClickedChanged(boolean mouseClickedChanged) {
		this.mouseClickedChanged = mouseClickedChanged;
	}
	public void setShutdownStatusChanged(boolean shutdownStatusChanged) {
		this.shutdownStatusChanged = shutdownStatusChanged;
	}
	public boolean isShutdownStatusChanged() {
		return shutdownStatusChanged;
	}
	
	public String getControlMessage(){
		
//		msg = createControlMessage();
		return msg;
	}
	
	public void setControlMessage(String message){
		setControlMsgToObject(message);
	}
	
	private void setControlMsgToObject(String message){
		String tmpMsg = message.replace("<mrc:crtl", "");
		tmpMsg = message.replace("/>", "");
		
		if(tmpMsg.contains("shutdownTime")){
			String value = tmpMsg.substring(tmpMsg.indexOf("shutdownTime"));
			int j = 0;
			int begin = 0;
			int end = 0;
			
			for(int i = 0;i<value.length() && j!=2;i++){
				if(value.charAt(i)==34){
					if(j==0){
						begin = i+1;
					}else{
						end = i-2; // - ms
					}
					j++;
				}
			}
			
			shutdownChanged = true;
			shutdownTime = Long.parseLong(value.substring(begin,end));
		}
		
		if(tmpMsg.contains("shutdownStatus")){
			String value = tmpMsg.substring(tmpMsg.indexOf("shutdownStatus"));
			int j = 0;
			int begin = 0;
			int end = 0;
			
			for(int i = 0;i<value.length() && j!=2;i++){
				if(value.charAt(i)==34){
					if(j==0){
						begin = i+1;
					}else{
						end = i;
					}
					j++;
				}
			}
			
			shutdownStatusChanged = true;
			shutdownStatus = value.substring(begin,end);
		}
		
		if(tmpMsg.contains("volume")){
			String value = tmpMsg.substring(tmpMsg.indexOf("volume"));
			int j = 0;
			int begin = 0;
			int end = 0;
			
			for(int i = 0;i<value.length() && j!=2;i++){
				if(value.charAt(i)==34){
					if(j==0){
						begin = i+1;
					}else{
						end = i;
					}
					j++;
				}
			}
			
			volumeChanged = true;
			volume = Integer.parseInt(value.substring(begin,end));
		}
		
		if(tmpMsg.contains("mute")){
			String value = tmpMsg.substring(tmpMsg.indexOf("mute"));
			int j = 0;
			int begin = 0;
			int end = 0;
			
			for(int i = 0;i<value.length() && j!=2;i++){
				if(value.charAt(i)==34){
					if(j==0){
						begin = i+1;
					}else{
						end = i;
					}
					j++;
				}
			}
			
			volumeChanged = true;
			mute = value.substring(begin,end).equals("off");
		}
		
		if(tmpMsg.contains("mouseX")){
			String value = tmpMsg.substring(tmpMsg.indexOf("mouseX"));
			int j = 0;
			int begin = 0;
			int end = 0;
			
			for(int i = 0;i<value.length() && j!=2;i++){
				if(value.charAt(i)==34){
					if(j==0){
						begin = i+1;
					}else{
						end = i;
					}
					j++;
				}
			}
			
			mouseChanged = true;
			mousePosX = Integer.parseInt(value.substring(begin,end));
		}
		
		if(tmpMsg.contains("mouseY")){
			String value = tmpMsg.substring(tmpMsg.indexOf("mouseY"));
			int j = 0;
			int begin = 0;
			int end = 0;
			
			for(int i = 0;i<value.length() && j!=2;i++){
				if(value.charAt(i)==34){
					if(j==0){
						begin = i+1;
					}else{
						end = i;
					}
					j++;
				}
			}
			
			mouseChanged = true;
			mousePosY = Integer.parseInt(value.substring(begin,end));			
		}
		
		if(tmpMsg.contains("mouseClick")){
			String value = tmpMsg.substring(tmpMsg.indexOf("mouseClick"));
			int j = 0;
			int begin = 0;
			int end = 0;
			
			for(int i = 0;i<value.length() && j!=2;i++){
				if(value.charAt(i)==34){
					if(j==0){
						begin = i+1;
					}else{
						end = i;
					}
					j++;
				}
			}
			
			mouseClickedChanged = true;
			
			if(value.substring(begin,end).equals("l")){
				mouseClicked = 1;
			}else if(value.substring(begin,end).equals("r")){
				mouseClicked = 2;
			}else if(value.substring(begin,end).equals("m")){
				mouseClicked = 3;	
			} 
		}
		
		if(tmpMsg.contains("mouseScroll")){
			String value = tmpMsg.substring(tmpMsg.indexOf("mouseScroll"));
			int j = 0;
			int begin = 0;
			int end = 0;
			
			for(int i = 0;i<value.length() && j!=2;i++){
				if(value.charAt(i)==34){
					if(j==0){
						begin = i+1;
					}else{
						end = i;
					}
					j++;
				}
			}
			mouseWheelChanged = true;
			mouse = value.substring(begin,end);			
		}
		
		if(tmpMsg.contains("key")){
			String value = tmpMsg.substring(tmpMsg.indexOf("key"));
			int j = 0;
			int begin = 0;
			int end = 0;
			
			for(int i = 0;i<value.length() && j!=2;i++){
				if(value.charAt(i)==34){
					if(j==0){
						begin = i+1;
					}else{
						end = i;
					}
					j++;
				}
			}
			keyChanged = true;
			key = (char)Integer.parseInt(value.substring(begin,end));
		}
		
		if(tmpMsg.contains("connected")){
			String value = tmpMsg.substring(tmpMsg.indexOf("connected"));
			int j = 0;
			int begin = 0;
			int end = 0;
			
			for(int i = 0;i<value.length() && j!=2;i++){
				if(value.charAt(i)==34){
					if(j==0){
						begin = i+1;
					}else{
						end = i;
					}
					j++;
				}
			}
			infoMessage.setConnected(value.substring(begin,end).equals(true));
		}
	}	
	
	private InformationMessage infoMessage = null;
	private static ControlMessage instance = null;
	
	private boolean shutdownChanged = false;
	private boolean mouseChanged = false;
	private boolean mouseClickedChanged = false;
	private boolean mouseWheelChanged = false;
	private boolean keyChanged = false;
	private boolean volumeChanged = false;
	private boolean mute;
	private boolean shutdownStatusChanged = false;
	private String msg;
	private String shutdownStatus;
	private String mouse;
	private long shutdownTime;	
	private int volume;	
	private int mousePosX;
	private int mousePosY;
	private int mouseClicked;
	private char key;
}
