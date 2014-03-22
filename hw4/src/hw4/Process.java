package hw4;
import java.util.*;

public class Process {
	int memSize;
	int duration;
	int name;
	public Process()
	{
		Random rGen = new Random();
		memSize = (int) Math.pow(2, (rGen.nextInt(4) + 2)) - 1;
		duration = rGen.nextInt(5) + 1;
		System.out.printf("Created process:  memSize:  %2d duration: %2d\n", memSize, duration);
	}
	
	public Process(Process p)
	{
		this.memSize = p.memSize;
		this.duration = p.duration;
		this.name = p.name;
	}
}
