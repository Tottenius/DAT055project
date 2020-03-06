package viewer;

/**
 * A class that can track time for a task . 
 * 
 * @author Group 10
 * @version 2020-02-19
 */

public class StopWatch {

	static long Start_time;
	static long End_time;

	/**
	 * This method starts the timer, can also be used to reset the timer
	 * 
	 * @return Nothing.
	 */
	public static void start() {

		Start_time = System.currentTimeMillis();
	}

	/**
	 * This method stops the timer
	 * 
	 * @return Time in seconds
	 */
	public static long stopTimer() {

		End_time = System.currentTimeMillis();
		return (End_time - Start_time) / 1000;
	}
}