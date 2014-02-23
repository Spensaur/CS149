package hw2;

import java.util.ArrayList;
import java.util.LinkedList;

public class HighestPriorityFirstPRE extends Scheduler implements AlgorithmInterface{
//	LinkedList<Process> PriorityOne = new LinkedList<Process>();
//	LinkedList<Process> PriorityTwo = new LinkedList<Process>();
//	LinkedList<Process> PriorityThree = new LinkedList<Process>();
//	LinkedList<Process> PriorityFour = new LinkedList<Process>();
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
			while (processQ.peekFirst().arrivalTime < globalQuanta && processQ.peekFirst() != null)
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
			globalQuanta++;
		}
		
	}
}
