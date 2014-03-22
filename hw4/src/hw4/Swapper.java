package hw4;

import java.util.ArrayList;
import java.util.LinkedList;

public class Swapper extends MemManager{

	int memPointer = 0;
	public Swapper(){super();};
	
	public void firstFit() {
		while (timeCounter < 60)
		{
			System.out.println("Time passed: "+ timeCounter);
			memPointer = 0;
			removeProcess();
			int minSize = processQ.peekFirst().memSize;
			int holeSize = 0;
			int counter = 0;
			while(holeSize < minSize && counter < 100)
			{
				if(memStorage[counter] == null) 
				{
					holeSize++;
				}
				else 
				{
					memPointer = counter + 1;
					holeSize = 0;
				}
				counter++;
			}
			if (holeSize >= minSize)
			{
				addProcess(memPointer, processQ.pollFirst());
				swapCounter++;
			}
			timeCounter++;
			//memPointer = 0;
			printMem();
		}
	}
	
	public void nextFit() {
		int nfPointer = 0;
		while (timeCounter < 60)
		{
			System.out.println("Time passed: "+ timeCounter);
			removeProcess();
			int minSize = processQ.peekFirst().memSize;
			memPointer = nfPointer;
			int holeSize = 0;
			int nfCounter = nfPointer;
			int counter = 0;
			while(holeSize < minSize && counter < 100)
			{
				if(nfPointer + counter > 99)
				{
					holeSize = 0;
					memPointer = 0;
					nfPointer = 0;
					nfCounter = 0;
				}
				else if(memStorage[nfCounter] == null) 
				{
					holeSize++;
				}
				else 
				{
					memPointer = nfCounter + 1;
					holeSize = 0;
				}
				counter++;
				nfCounter++;
			}
			if (holeSize >= minSize)
			{
				addProcess(memPointer, processQ.pollFirst());
				swapCounter++;
			}
			timeCounter++;
			nfPointer = memPointer+minSize;
			//memPointer = 0;
			printMem();
		}
	}
	
	public void bestFit() {
		while(timeCounter < 60)
		{
			System.out.println("Time passed: "+ timeCounter);
			removeProcess();
			ArrayList<Hole> holeStore = new ArrayList<Hole>();
			int minSize = processQ.peekFirst().memSize;
			int holeSize = 0;
			int memPointer = 0;
			for(int i = 0; i < 100; i++)
			{
				if(memStorage[i] == null) 
				{
					holeSize++;
				}
				else 
				{
					holeStore.add(new Hole(memPointer, holeSize));
					memPointer = i + 1;
					holeSize = 0;
				}
				if(i == 99)
				{
					holeStore.add(new Hole(memPointer, holeSize));
				}
	
			}
			Hole bestHole = new Hole(-1, 1010);
			for(Hole H: holeStore)
			{
				if (H.size > minSize && H.size < bestHole.size)
				{
					bestHole.size = H.size;
					bestHole.start = H.start;
				}
			}
			if(bestHole.start != -1)
			{
				addProcess(bestHole.start, processQ.pollFirst());
				swapCounter++;
			}
			printMem();
			timeCounter++;
		}
	}
}
