package hw2;

import java.util.Iterator;

public class FirstComeFirstServe extends Scheduler implements AlgorithmInterface{
	public FirstComeFirstServe() {super();}
	
	public void ScheduleOperations() {
		while (globalQuanta < 100)
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
		while(processQ.peek() != null && processQ.peekFirst().arrivalTime < globalQuanta)
		{
			readyQ.add(processQ.pop());
		}
		if (readyQ.peek() != null) currentProcess = readyQ.pop();
		if (currentProcess == null) 
			{
				timechart.add("none");
				globalQuanta++;
			}
		while(currentProcess != null)
		{
			for (Process p: readyQ)
			{
				p.turnAroundTime++;
				p.waitingTime++;
			}
			currentProcess.turnAroundTime++;
			currentProcess.touched = true;
			timechart.add(currentProcess.name);
			if(currentProcess.responseTime == 0) currentProcess.responseTime = globalQuanta;
			if (currentProcess.expectedRT -1 < 0)
			{
				completed.add(currentProcess);
				currentProcess = null;
			}
			else 
			{
				currentProcess.expectedRT--;
			}
			globalQuanta++;
		}
	}
}
