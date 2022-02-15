package Project4;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class MaxHeapTest {

	public static void main(String[] args) throws IOException 
	{
		MaxHeap seqHeap = new MaxHeap(100);
		
		int[] enteries = new int[100];
		
		File input = new File("data.txt");
		Scanner scan = new Scanner(input);

		while(scan.hasNextInt())
		{
			seqHeap.add(scan.nextInt());
		}
		
		Scanner scan1 = new Scanner(input);
		for(int k = 0; k < 100; k++)
		{
			enteries[k] = scan1.nextInt();
		}
		MaxHeap optHeap = new MaxHeap(enteries);
		
		FileWriter output = new FileWriter("output.txt");
		output.write("====================================================================="
				+ "\nHeap built using sequential insertions: " + seqHeap.getFirstTen()
				+ "\nNumber of swaps in the heap creation: " + seqHeap.getaddSwap() );
		for(int i = 1; i <= 10; i++)
		{
			seqHeap.removeMax();
		}
		output.write("\nHeap after 10 removals: " + seqHeap.getFirstTen()
				+ "\n\nHeap built using optimal method: " + optHeap.getFirstTen()
				+ "\nNumber of swaps in the heap creation: " + optHeap.getreheapSwap());
		for(int i = 1; i <= 10; i++)
		{
			optHeap.removeMax();
		}
		output.write("\nHeap after 10 removals: " + optHeap.getFirstTen()
				+ "\n=====================================================================");
		output.close();
		scan.close();
		scan1.close();
	}

}
