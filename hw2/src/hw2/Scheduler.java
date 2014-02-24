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
	public ArrayList<String> timechart;
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
		timechart = new ArrayList<String>();
		currentProcess = null;
	}
	
	
	public void printTimeChart()
	{
		for(int i = 0; i < timechart.size(); i++)
		{
			if(i % 10 == 0) System.out.println();
			System.out.print(timechart.get(i)+ " ");
		}
		System.out.print("\n\n");
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
			System.out.println("expected Runtime: " + p.expectedRT);
			System.out.println("priority: " + p.priority);
			System.out.println("responseTime: " + p.responseTime);
			System.out.println("name: " + p.name);
		}
	}
	public void testcompleted()
	{
		System.out.println("number of processes completed: " +completed.size());
		for (Process p: completed)
		{
			if(p.touched == false) System.out.println("broken: nontouched processes in completed");
		}
	}
	public void testreadyQ()
	{
		for (Process p: readyQ)
		{
			if(p.touched == false) System.out.println("broken: nontouched processes in readyQ");
		}
	}
	
	
	public static void main(String[] args){
		for (int i = 0; i < 5; i++)
		{
			RoundRobin robby = new RoundRobin();
			robby.testProcessQOrder();
			robby.ScheduleOperations();
			robby.printTimeChart();
			robby.printAVGWait();
			robby.printAVGResponse();
			robby.printAVGTurnaround();
			robby.testcompleted();
		}
	}

}
