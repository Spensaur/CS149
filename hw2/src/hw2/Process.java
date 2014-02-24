package hw2;
import java.util.*;

public class Process implements Comparable<Process>{
	
	float arrivalTime;
	float expectedRT;
	int priority;
	String name;
	float turnAroundTime;
	float waitingTime;
	float responseTime;
	boolean touched;
	
	public Process (){
		Random rGen = new Random();
		arrivalTime = rGen.nextFloat() * 99;
		expectedRT = (float) (.1 + rGen.nextFloat() * 9.9);
		priority = 1 + rGen.nextInt(4);
		name = "";
		turnAroundTime = 0;
		waitingTime = 0;
		responseTime = 0;
		touched = false;
	}

	@Override
	public int compareTo(Process p) {
		if (this.arrivalTime == p.arrivalTime) return 0;
		else if(this.arrivalTime > p.arrivalTime) return 1;
		else return -1;
	}

}
