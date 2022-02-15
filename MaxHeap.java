package Project4;

import java.util.*;

public final class MaxHeap 
{
	private int[] heap;
	private int lastIndex;
	private boolean initialized = false;
	private static final int DEFAULT_CAPACITY = 25;
	private static final int MAX_CAPACITY = 10000;
	
	int addSwap = 0;
	int reheapSwap = 0;
	
	public MaxHeap()
	{
		this(DEFAULT_CAPACITY);
	}//end default constructor
	
	public MaxHeap(int initialCapacity)
	{
		if(initialCapacity < DEFAULT_CAPACITY)
			initialCapacity = DEFAULT_CAPACITY;
		else
			checkCapacity(initialCapacity);
		
		int[] tempHeap = new int[initialCapacity + 1];
		heap = tempHeap;
		lastIndex = 0;
		initialized = true;
	}//end constructor
	
    public MaxHeap(int[] entries)
    {
        checkCapacity(entries.length);
        
        int[] tempHeap = new int[entries.length + 1];
        heap = tempHeap;
        lastIndex = entries.length;
        
        // Copy given array to data field
        for (int index = 0; index < entries.length; index++)
        {
            heap[index + 1] = entries[index];
        }
        
        // Create heap
        for (int rootIndex = lastIndex/2; rootIndex > 0; rootIndex--)
        {
            reheap(rootIndex);
        }
        initialized = true;
    }// end constructor
	
	private void checkCapacity(int capacity)
	{
		if (capacity > MAX_CAPACITY)
			throw new IllegalStateException("Attempt to create a bag whose " +
											"capacity exeeds allowed " +
											"maximum of " + MAX_CAPACITY);
	} // end checkCapacity
	
	private void checkInitialization()
	{
		if (!initialized)
	    {
			throw new SecurityException ("MaxHeap object is not initialized properly.");
	    }
	}//end checkInitialization
	
    private void ensureCapacity()
    {
        if (lastIndex >= heap.length)
        {
            int newCapacity = 2 * heap.length;
            checkCapacity(newCapacity);
            heap = Arrays.copyOf(heap, newCapacity);
        }
    }//end ensureCapacity
	
    public void add(int newEntry)
    {
        checkInitialization();
        int newIndex = lastIndex + 1;
        int parentIndex = newIndex / 2;

        while ((parentIndex > 0) && (newEntry - heap[parentIndex]) > 0)
        {
            heap[newIndex] = heap[parentIndex];
            newIndex = parentIndex;
            parentIndex = newIndex / 2; 
            addSwap++;
        }

        heap[newIndex] = newEntry;
        lastIndex++;
        ensureCapacity();
        
    }//end add
    
    public int removeMax()
    {
       checkInitialization();
       int root = 0;

       if (!isEmpty())
       {
           root = heap[1];               
           heap[1] = heap[lastIndex]; 
           lastIndex--;                  
           reheap(1);                    
       }

       return root;
    }//end removeMax
    
    private void reheap(int rootIndex)
    {
        boolean done = false;
        int orphan = heap[rootIndex];
        int leftChildIndex = 2 * rootIndex;

        while (!done && (leftChildIndex <= lastIndex) )
        {
            int largerChildIndex = leftChildIndex; 
            int rightChildIndex = leftChildIndex + 1;

            if ( (rightChildIndex <= lastIndex) && 
                  (heap[rightChildIndex] - heap[largerChildIndex]) > 0)
            {
                largerChildIndex = rightChildIndex;
            }

            if ((orphan - heap[largerChildIndex]) < 0)
            {
                heap[rootIndex] = heap[largerChildIndex];
                rootIndex = largerChildIndex;
                leftChildIndex = 2 * rootIndex;
                reheapSwap++;
            } 
            else
            {
                done = true;
            }
        }

        heap[rootIndex] = orphan;
    }//end reheap
	
    public int getMax()
    {
        checkInitialization();
        int root = 0;

        if (!isEmpty())
        {
            root = heap[1];
        }

        return root;
    }//end getMax

    public boolean isEmpty()
    {
        return lastIndex < 1;
    }//end isEmpty

    public int getSize()
    {
        return lastIndex;
    }//end getSize

    public void clear()
    {
        checkInitialization();
        while (lastIndex > -1)
        {
            heap[lastIndex] = 0;
            lastIndex--;
        }
      
        lastIndex = 0;
    }//end clear
    
    public int getaddSwap()
    {
    	return addSwap;
    }//end getaddSwap
    
    public int getreheapSwap()
    {
    	return reheapSwap;
    }//end getreheapSwap
    
    public String getFirstTen()
    {
    	String firstTen = "" + heap[1] + "," + heap[2] 
    			+ "," + heap[3] + "," + heap[4] + "," 
    			+ heap[5] + "," + heap[6] + "," + heap[7] + "," 
    			+ heap[8] + "," + heap[9] + "," + heap[10] + ",...";
    	return firstTen;

    }//end setFirstTen
    

}
