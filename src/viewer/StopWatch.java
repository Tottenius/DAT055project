package viewer;

public class StopWatch {

	static long Start_time;
	static long End_time;

 
	public static void start() {
	
		Start_time = System.currentTimeMillis();
	}

	public static long stopTimer() {
		
	  End_time = System.currentTimeMillis();
	  return  (End_time - Start_time ) / 1000;
	}
}