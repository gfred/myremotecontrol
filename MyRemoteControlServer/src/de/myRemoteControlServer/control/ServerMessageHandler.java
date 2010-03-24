package de.myRemoteControlServer.control;

import java.awt.Robot;
import java.awt.event.InputEvent;
import java.util.Timer;
import de.myRemoteControlServer.data.ControlMessage;
import de.myRemoteControlServer.data.InformationMessage;

public class ServerMessageHandler {
	
	private ServerMessageHandler(){
		crtlMsg = ControlMessage.getInstance();
		infoMsg = InformationMessage.getInstance();
		logger = Logger.getInstance();
		timer = new Timer();
		
		try{
			robot = new Robot();	
		}catch (Exception e) {}
	}
	
	public static ServerMessageHandler getInstance(){
		if(instance == null){
			instance = new ServerMessageHandler();		
		}
		return instance;
	}
	
	public void crtlMessageChanged(){		
		if(crtlMsg.isShutdownStatusChanged()){
			if(crtlMsg.getShutdownStatus().equals("start")){
				logger.addMessage("Shutdown Server in "+Util.getTimeString(crtlMsg.getShutdownTime()));
				task = new ServerShutdownTask(System.currentTimeMillis());
				timer.schedule(task, crtlMsg.getShutdownTime());
			}else{
				timer.cancel();
				timer.purge();
				crtlMsg.setShutdownTime(System.currentTimeMillis()-task.getStarttime());
			}
			
			crtlMsg.setShutdownStatusChanged(false);
		}
		
		if(crtlMsg.isVolumeChanged()){
			//TODO VOLUME CONTROL für java ...?	
		}
		
		if(crtlMsg.isMouseChanged()){
			if(robot!=null){
				robot.mouseMove(crtlMsg.getMousePosX(), crtlMsg.getMousePosY());
				logger.addMessage("Mouse move to "+crtlMsg.getMousePosX()+","+crtlMsg.getMousePosY());								
				crtlMsg.setMouseChanged(false);
			}
		}
		
		if(crtlMsg.isMouseClickedChanged()){
			if(robot!=null){
				switch(crtlMsg.getMouseClicked()){
					case 1:
						robot.mousePress(InputEvent.BUTTON1_MASK);
						logger.addMessage("Mouse clicked: left");
						break;
					case 2:
						robot.mousePress(InputEvent.BUTTON2_MASK);
						logger.addMessage("Mouse clicked: right");
						break;
					case 3:
						robot.mousePress(InputEvent.BUTTON3_MASK);
						logger.addMessage("Mouse clicked: middle");
						break;
					default:
						break;							
				}		
				crtlMsg.setMouseClickedChanged(false);
			}
		}
		
		if(crtlMsg.isMouseWheelChanged()){
			if(robot!=null){
				if(crtlMsg.getMouse().equals("u")){
					robot.mouseWheel(3);
					logger.addMessage("Mousewheel: up");
				}else{
					logger.addMessage("Mousewheel: down");
					robot.mouseWheel(-3);
				}
				crtlMsg.setMouseWheelChanged(false);
			}
		}
		
		if(crtlMsg.isKeyChanged()){
			if(robot!=null){
				robot.keyPress(crtlMsg.getKey());
				logger.addMessage("Key pressed: "+Character.toString(crtlMsg.getKey()));
			}
		}
	}
	
	private ServerShutdownTask task = null;
	private Timer timer = null; 
	private Logger logger = null;
	private Robot robot = null;
	private ControlMessage crtlMsg = null;
	private InformationMessage infoMsg = null;
	private static ServerMessageHandler instance = null;
}