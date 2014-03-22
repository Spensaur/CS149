package hw2;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

public class HighestPriorityFirstPRE extends Scheduler implements AlgorithmInterface{
	ArrayList<LinkedList<Process>> priorityStack;
	public HighestPriorityFirstPRE() 
	{
		super();
		priorityStack = new ArrayList<LinkedList<Process>>();
		for(int i = 0; i < 4; i++)
		{
			priorityStack.add(new LinkedList<Process>());
		}
	}

	public void ScheduleOperations() {
		
		while(globalQuanta < 100)
		{
			helper();
		}
		for (LinkedList<Process> list: priorityStack)
		{
			Iterator<Process> it = list.iterator();
			//testreadyQ();
			while (it.hasNext()) 
			{
			    Process p = it.next();
			    if(p.touched == false) it.remove();
			}
		}

		for (LinkedList<Process> list : priorityStack)
		{
			while(!list.isEmpty())
			{

				if (list.peek() != null && list.peek().arrivalTime < globalQuanta)
				{
					currentProcess = list.pop();
				}
				for (Process p: list)
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
						list.add(currentProcess);
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
		
	}
	public void helper()
	{
		while (processQ.peekFirst() != null && processQ.peekFirst().arrivalTime < globalQuanta)
		{
			int priority = processQ.peekFirst().priority;
			priorityStack.get(priority -1).add(processQ.pop());
		}
		boolean available = false;
		int check = -1;
		while(available == false)
		{
			check++;
			if (!priorityStack.get(check).isEmpty()) available = true;
			if (check == 3 && available == false)
			{
				available = true;
				check = -1;
			}
		}
		if (check != -1)
		{
			currentProcess = priorityStack.get(check).pop();
		}
		for (LinkedList<Process> list: priorityStack)
		{
			for (Process p: list)
			{
				p.turnAroundTime++;
				p.waitingTime++;
			}
		}
		if (currentProcess != null)
		{
			currentProcess.turnAroundTime++;
			timechart.add(currentProcess.name);
			currentProcess.touched = true;
			if(currentProcess.responseTime == 0) currentProcess.responseTime = globalQuanta;
			if (currentProcess.expectedRT -1 < 0)
			{
				completed.add(currentProcess);
			}
			else 
			{
				currentProcess.expectedRT--;
				priorityStack.get(currentProcess.priority -1).add(currentProcess);
			}
		}
		if (currentProcess == null) timechart.add("none");
		currentProcess = null;
		globalQuanta++;
	}
}
