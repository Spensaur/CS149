package hw2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Scheduler{
	public int globalQuanta;
	public LinkedList<Process> processQ;
	
	public Scheduler(){
		globalQuanta = 0;
		Process[] temp = new Process[100];
		for (int i = 0; i < 100; i++)
		{
			temp[i] = new Process();
			//temp.add(new Process());
		}
		Arrays.sort(temp);
		processQ = new LinkedList<Process>(Arrays.asList(temp));
	}
	public void testProcessQOrder(){
		for (Process p: processQ)
		{
			System.out.println("arrival time: " + p.arrivalTime);
		}
	}
	
	
	public static void main(String[] args){
		Scheduler s = new Scheduler();

		while (s.globalQuanta < 100)
		{
			
		}
	}

}
