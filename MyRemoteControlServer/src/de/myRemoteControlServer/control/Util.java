package de.myRemoteControlServer.control;

public class Util {

	public static String getTimeString(long time){
		time = time / 1000;
		int h = (int)(time / 3600);
		time = time - (int)(h * 3600);
		int m = (int)(time / 60);
		time = time - (int)(m * 60);
		int s = (int)time;
		
		return "hours: "+h+" minutes: "+m+" seconds: "+s;
	}
}
