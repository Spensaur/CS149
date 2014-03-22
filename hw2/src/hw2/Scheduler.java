package hw2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class Scheduler{
	public int globalQuanta;//in general I think you want to increment this one at a time
	public LinkedList<Process> processQ; //Pull processes from processq until 100quanta then only use readyQ
	public LinkedList<Process> completed; //put completed processes in here
	public LinkedList<Process> readyQ; //You can use this as the main queue for the processes
	public ArrayList<String> timechart;//add the currentProcess you are working on to the timechart
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
	
	public float printThroughput()
	{
		System.out.println("Throughput: " + ((float)completed.size()/(float)globalQuanta));
		return (float)completed.size()/(float)globalQuanta;
	}
	
	public float printAVGTurnaround()
	{
		float turnAroundTotal = 0;
		for (Process p: completed)
		{
			turnAroundTotal += p.turnAroundTime;
		}
		System.out.println("Average Turnaround Time: " + (turnAroundTotal/completed.size()));
		return turnAroundTotal/completed.size();
	}
	
	public float printAVGWait()
	{
		float waitTotal = 0;
		for (Process p: completed)
		{
			waitTotal += p.waitingTime;
		}
		System.out.println("Average Wait Time: " + (waitTotal/completed.size()));
		return waitTotal/completed.size();
	}
	
	public float printAVGResponse()
	{
		float responseTotal = 0;
		for (Process p: completed)
		{
			responseTotal += p.responseTime;
		}
		System.out.println("Average Response Time: " + (responseTotal/completed.size()));
		return responseTotal/completed.size();
	}
	
	public void testProcessQOrder(){
		for (Process p: processQ)
		{
			System.out.println("Name: " + p.name);
			System.out.println("Arrival Time: " + p.arrivalTime);
			System.out.println("Expected Runtime: " + p.expectedRT);
			System.out.println("priority: " + p.priority + "\n");
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
	
	public void testSuite()
	{
		printTimeChart();
		printAVGWait();
		printAVGResponse();
		printAVGTurnaround();
		printThroughput();
		testcompleted();
	}
	
	
	public static void main(String[] args){
//		float rrAVGWait = 0;
//		float rrAVGRResponse = 0;
//		float rrtAVGTurnaround = 0;
//		float rrThroughput = 0;
//		
//		for (int i = 0; i < 30; i++)
//		{
//			RoundRobin robby = new RoundRobin();
//			rrAVGWait += robby.printAVGWait();
//			rrAVGRResponse += robby.printAVGResponse();
//			rrtAVGTurnaround += robby.printAVGTurnaround();
//			rrThroughput += robby.printThroughput();
//		}
//		
//		System.out.println("Average wait:" + rrAVGWait/30);
//		System.out.println("Average response:" + rrAVGWait/30);
//		System.out.println("Average Turnaround:" + rrAVGWait/30);
//		System.out.println("Throughput:" + rrAVGWait/30);
//		
		
		for (int i = 0; i < 5; i++)
		{
			RoundRobin robby = new RoundRobin();
			HighestPriorityFirstPRE HPFPRE = new HighestPriorityFirstPRE();
			FirstComeFirstServe FCFS = new FirstComeFirstServe();
			ShortestJobFirst SJF = new ShortestJobFirst();
			ShortestRemainingTime SRT = new ShortestRemainingTime();
			HighestPriorityFirstNONPRE  HPFNONPRE = new HighestPriorityFirstNONPRE();
			System.out.println("\n\nRound Robin");
			robby.testProcessQOrder();
			robby.ScheduleOperations();
			robby.testSuite();
//			robby.printTimeChart();
//			robby.printAVGWait();
//			robby.printAVGResponse();
//			robby.printAVGTurnaround();
//			robby.printThroughput();
//			robby.testcompleted();
			System.out.println("\n\nHighest Priority First Preemptive");
			HPFPRE.testProcessQOrder();
			HPFPRE.ScheduleOperations();
			HPFPRE.testSuite();
//			HPFPRE.printTimeChart();
//			HPFPRE.printAVGWait();
//			HPFPRE.printAVGResponse();
//			HPFPRE.printAVGTurnaround();
//			HPFPRE.printThroughput();
//			HPFPRE.testcompleted();
			System.out.println("\n\nFirst Come First Serve");
			FCFS.testProcessQOrder();
			FCFS.ScheduleOperations();
			FCFS.testSuite();
			System.out.println("\n\nShortest Job First");
			SJF.testProcessQOrder();
			SJF.ScheduleOperations();
			SJF.testSuite();
			System.out.println("\n\nShortest Remaining Time");
			SRT.testProcessQOrder();
			SRT.ScheduleOperations();
			SRT.testSuite();
			System.out.println("\n\nHighest Priority First Non-Preemptive");
			HPFNONPRE.testProcessQOrder();
			HPFNONPRE.ScheduleOperations();
			HPFNONPRE.testSuite();
		}
	}

}
