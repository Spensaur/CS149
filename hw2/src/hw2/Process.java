package hw2;
import java.util.*;

public class Process implements Comparable<Process>{
	
	float arrivalTime;
	float expectedRT;
	int priority;
	String name;
	float turnAroundTime;
	float waitingTime;
	
	public Process (){
		Random rGen = new Random();
		arrivalTime = rGen.nextFloat() * 99;
		expectedRT = (float) (.1 + rGen.nextFloat() * 9.9);
		priority = 1 + rGen.nextInt(3);
		name = "";
		turnAroundTime = 0;
		waitingTime = 0;
	}

	@Override
	public int compareTo(Process p) {
		// TODO Auto-generated method stub
		//Process compto = (Process) p;
		if (this.arrivalTime == p.arrivalTime) return 0;
		else if(this.arrivalTime > p.arrivalTime) return 1;
		else return -1;
		//return this.arrivalTime - compto.arrivalTime;
	}

}
