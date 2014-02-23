package hw2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Scheduler{
	public int globalQuanta;
	public LinkedList<Process> processQ; //Pull processes from processq until 100quanta then only use readyQ
	public LinkedList<Process> completed; //put completed processes in here
	public LinkedList<Process> readyQ; //You can use this as the main queue for the processes
	public ArrayList<Process> timechart;
	public Process currentProcess;
	
	public Scheduler(){
		globalQuanta = 0;
		Process[] temp = new Process[100];
		for (int i = 0; i < 100; i++)
		{
			temp[i] = new Process();
		}
		Arrays.sort(temp);
		for (int i = 0; i < 100; i++)
		{
			temp[i].name = Integer.toString(i);
		}
		processQ = new LinkedList<Process>(Arrays.asList(temp));
		completed = new LinkedList<Process>();
		readyQ = new LinkedList<Process>();
		timechart = new ArrayList<Process>();
		currentProcess = null;
	}
	
	
	public void printTimeChart()
	{
		for(int i = 0; i < timechart.size(); i++)
		{
			if(i % 10 == 0) System.out.println();
			System.out.print(timechart.get(i).name+ " ");
		}
	}
	
	
	public void printThroughput()
	{
		System.out.println("Throughput: " + ((double)completed.size()/(double)globalQuanta));
	}
	
	public void printAVGTurnaround()
	{
		float turnAroundTotal = 0;
		for (Process p: completed)
		{
			turnAroundTotal += p.turnAroundTime;
		}
		System.out.println("Average Turnaround Time: " + (turnAroundTotal/completed.size()));
	}
	
	public void printAVGWait()
	{
		float waitTotal = 0;
		for (Process p: completed)
		{
			waitTotal += p.waitingTime;
		}
		System.out.println("Average Wait Time: " + (waitTotal/completed.size()));
	}
	
	public void printAVGResponse()
	{
		float responseTotal = 0;
		for (Process p: completed)
		{
			responseTotal += p.responseTime;
		}
		System.out.println("Average Response Time: " + (responseTotal/completed.size()));
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
