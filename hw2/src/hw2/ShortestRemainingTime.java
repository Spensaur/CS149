package hw2;

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
    public void helper(){

    }



}
