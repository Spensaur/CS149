package hw2;

public class RoundRobin extends Scheduler implements AlgorithmInterface{
	public RoundRobin() {super();}
	
	public void ScheduleOperations() 
	{
		while(globalQuanta < 100)
		{
			while (processQ.peekFirst().arrivalTime < globalQuanta && processQ.peekFirst() != null)
			{
				readyQ.add(processQ.pop());
			}
			if (readyQ.peek().arrivalTime < globalQuanta)
			{
				currentProcess = readyQ.pop();
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
					readyQ.add(currentProcess);
				}
			}
			for (Process p: readyQ)
			{
				p.turnAroundTime++;
				p.waitingTime++;
			}
			globalQuanta++;
		}
	}
	
}
