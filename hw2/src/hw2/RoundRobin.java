package hw2;

import java.util.Iterator;

public class RoundRobin extends Scheduler implements AlgorithmInterface{
	public RoundRobin() {super();}
	
	public void ScheduleOperations() 
	{
		while(globalQuanta < 100)
		{
			helper();
		}
		Iterator<Process> it = readyQ.iterator();
		//testreadyQ();
		while (it.hasNext()) 
		{
		    Process p = it.next();
		    if(p.touched == false) it.remove();
		}
		testreadyQ();
		while(!readyQ.isEmpty())
		{
			helper();
		}
	}
	
	public void helper()
	{
		
		while (processQ.peek() != null && processQ.peekFirst().arrivalTime < globalQuanta)
		{
			readyQ.add(processQ.pop());
		}

		if (readyQ.peek() != null && readyQ.peek().arrivalTime < globalQuanta)
		{
			currentProcess = readyQ.pop();
		}
		for (Process p: readyQ)
		{
			p.turnAroundTime++;
			p.waitingTime++;
		}
		if (currentProcess != null)
		{
			currentProcess.turnAroundTime++;
			currentProcess.touched = true;
			timechart.add(currentProcess.name);
			if(currentProcess.responseTime == 0) currentProcess.responseTime = globalQuanta;
			if (currentProcess.expectedRT -1 < 0)
			{
				completed.add(currentProcess);
			}
			else 
			{
				currentProcess.expectedRT--;
				readyQ.add(currentProcess);
			}
		}
		else
		{
			timechart.add("none");
		}
		currentProcess = null;
		globalQuanta++;
	}
	
}
