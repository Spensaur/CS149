package hw4;

import hw4.Process;

import java.util.LinkedList;

public class MemManager {
	
	int timeCounter;
	public LinkedList<Process> processQ;
	Process[] memStorage;
	int swapCounter;
	
	public MemManager()
	{
		timeCounter = 0;
		processQ = new LinkedList<Process>();
		memStorage = new Process[100];
		swapCounter = 0;
		for(int i = 0; i < 150; i++)
		{
			Process p = new Process();
			p.name = i;
			processQ.add(p);
		}
	}
	
	//prints the memory map
	public void printMem()
	{
		for(int i = 0; i < 100; i++)
		{
			if(memStorage[i] == null)
			{
				System.out.print(" - ");
			}
			else System.out.printf("%2d ",memStorage[i].name);
			if((i+1)%20 == 0) System.out.println("");
		}
		System.out.println("");
	}
	
	public void removeProcess() //call every second
	{
		for(int i = 0; i < 100; i++)
		{
			if(memStorage[i] != null)
			{
				memStorage[i].duration--;
				if (memStorage[i].duration == 0)
				{
					memStorage[i] = null;
				}
			}
		}
	}
	
	
	//call this at algorithmic specific place or whenever a new process is at head of queue
	public void addProcess(int memPlace, Process p) 
	{
		System.out.println("Adding Process! memPointer: " + memPlace + " Process: " + p.name + " Size: "+ p.memSize + " Duration: "+ p.duration);
		for(int i = 0; i < p.memSize; i++)
		{
			memStorage[i + memPlace] = new Process(p);
		}
	}
	
//	public void addProcess2(int memPlace, Process p) 
//	{
//		System.out.println("Adding Process! memPlace: " + memPlace + " Process: " + p.name + " Size: "+ p.memSize);
//		for(int i = 0; i < p.memSize; i++)
//		{
//			memStorage[memPlace - i -1] = new Process(p);
//		}
//	}
	
	//runs the test cases
	public static void main(String[] args)
	{
		double FFavg = 0;
		double NFavg = 0;
		double BFavg = 0;
		for(int i = 0; i < 5; i++)
		{
			Swapper FF = new Swapper();
			FF.firstFit();
			FFavg += FF.swapCounter;
			Swapper NF = new Swapper();
			NF.nextFit();
			NFavg += NF.swapCounter;
			Swapper BF = new Swapper();
			BF.bestFit();
			BFavg += BF.swapCounter;
		}
		System.out.println("First Fit avg: "+ FFavg/5 + " processes");
		System.out.println("Next Fit avg: "+ NFavg/5 + " processes");
		System.out.println("Best Fit avg: "+ BFavg/5 + " processes");
	}
}
