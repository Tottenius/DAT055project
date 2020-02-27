package viewer;

/**
 * A class that can track time for different task.
 *  
 * @author Anton(ska man skriva alla i sin gruppe eller den som gjord det?)
 * @param
 * @see 
 * @value
 * @version 1.0
 * */

public class StopWatch {

	static long Start_time;
	static long End_time;

	/**
	   * This method starts the timer
	   * @return Nothing.
	   */
	public static void start() {
	
		Start_time = System.currentTimeMillis();
	}

	/**
	   * This method stops the timer
	   * @return Time in seconds
	   */
	public static long stopTimer() {
		
	  End_time = System.currentTimeMillis();
	  return  (End_time - Start_time ) / 1000;
	}
}