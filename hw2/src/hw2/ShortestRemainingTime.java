package hw2;

import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;

public class ShortestRemainingTime extends Scheduler implements AlgorithmInterface{

    public ShortestRemainingTime() {
        super();
    }
	
	public void ScheduleOperations() {
        while (globalQuanta < 100)
        {
            helper();
        }
        Iterator<Process> it = readyQ.iterator();

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
    	Collections.sort(readyQ, new Comparator<Process>(){

			public int compare(Process p1, Process p2) {
				if (p1.expectedRT > p2.expectedRT) return 1;
				else if(p1.expectedRT == p2.expectedRT) return 0;
				else return -1;
			}
			
		});
    	if (readyQ.peek() != null)
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
