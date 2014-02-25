package hw2;

import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;

public class ShortestJobFirst extends Scheduler implements AlgorithmInterface 
{
	public ShortestJobFirst(){
        super();
      }
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
//		float shortestTime; 
//		Process temp;
//		while (globalQuanta<100)
//		{
//		while (processQ.peek()!=null)
//		{
//			Iterator<Process> iter1= processQ.iterator();
//			while (iter1.hasNext())
//			{
//				temp=iter1.next();
//				if (temp.arrivalTime<globalQuanta)
//					iter1.remove();
//				readyQ.add(temp);
//			}	
//			shortestTime=readyQ.peek().expectedRT;
//			Iterator<Process> iter= readyQ.iterator();
//			while (iter.hasNext())
//			{
//				temp=iter.next();
//				if (temp.expectedRT<shortestTime)
//				shortestTime=temp.expectedRT;
//			}
//			Iterator<Process> iter2= readyQ.iterator();
//			temp=iter2.next();
//			while ((iter2.hasNext())&&(temp.expectedRT!=shortestTime))
//		{
//				temp=iter2.next();
//			readyQ.remove();
//		}
//			
//			
//			
//		 }
			
			
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
		if (currentProcess == null) globalQuanta++;
		while(currentProcess != null && currentProcess.expectedRT > 0)
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
			currentProcess.expectedRT--;
			globalQuanta++;
		}
		if(currentProcess != null)
		{
			completed.add(currentProcess);
			currentProcess = null;
		}
	}
		
}
	
	
  /*  public static void main(String args[])
    {
        int process[] = new int[10];
        int ptime[] = new int[10];
        int wtime[] = new int[10];
        int temp, n, total=0;
        float avg=0;
        Scanner get = new Scanner(System.in);

        System.out.println("Enter Number of Processes:");
        n = get.nextInt();
        for(int i=0;i<n;i++)
        {
            System.out.println("Enter Process "+(i+1)+" ID: ");
            process[i] = get.nextInt();
            System.out.println("Enter Process "+(i+1)+" Burst Time: ");
            ptime[i] = get.nextInt();
        }

        for(int i=0;i<n-1;i++)
        {
            for(int j=i+1;j<n;j++)
            {
                if(ptime[i]>ptime[j])
            {
                temp = ptime[i];
                ptime[i] = ptime[j];
                ptime[j] = temp;
                temp = process[i];
                process[i] = process[j];
                process[j] = temp;
            }
            }
        }

        wtime[0] = 0;
        for(int i=1;i<n;i++)
        {
            wtime[i] = wtime[i-1]+ptime[i-1];
            total = total + wtime[i];
        }
        avg = (float)total/n;
        System.out.println("P_ID P_TIME W_TIME");
        for(int i=0;i<n;i++)
        {
            System.out.println(process[i]+"\t"+ptime[i]+"\t"+wtime[i]);
        }
        System.out.println("Total Waiting Time: "+total);
        System.out.println("Average Waiting Time: "+avg);
    }*/
