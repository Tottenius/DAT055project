package viewer;

//class to keep track of times.. for usercase Leaderboards and timings solves of puzzle ,
//java swing timer is probably better so we can display live timer during gmae! have to implemt buttons and gui first then
//will lveave this class here in case it finds use later / A :P
public abstract class Timer {
	 
	static long startTime = 0;
	 static long endTime = 0;
	 static long duration = 0 ;

	public static void startTimer() {
		
		startTime = System.nanoTime();
	}
	
	public static long ReturnTime() {
		
		endTime = System.nanoTime();
		duration = (endTime - startTime) /1000000; //divide by 1000000 to get milliseconds.
		return duration;
	}
	
}
