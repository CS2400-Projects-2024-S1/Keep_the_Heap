package org.pizza;
public class Heap {
    // priv
    private int[] heapArray; // Array to store heap elements
    private int size; // Current size of the heap
    private int swaps; // Number of swaps performed during operations

    // Constructor
    public Heap(int capacity) {
        heapArray = new int[capacity]; // Initialize heap array
        size = 0; // Initialize size to 0
        swaps = 0; // Initialize swaps to 0
    }

    // put new element into the heap
    public void insert(int value) {
        if (size == heapArray.length) { // check if heap is full
            System.out.println("Heap is full. Cannot insert " + value);
            return;
        }

        heapArray[size] = value; // new element
        int currentIndex = size; // Index new element
        while (currentIndex > 0) {
            int parentIndex = (currentIndex - 1) / 2; // parent index
            if (heapArray[currentIndex] > heapArray[parentIndex]) { // check if greater than parent
                // swap with parent
                int temp = heapArray[currentIndex];
                heapArray[currentIndex] = heapArray[parentIndex];
                heapArray[parentIndex] = temp;
                swaps++; // increment swaps 
                currentIndex = parentIndex; 
            } else {
                break; // Break loop if heap property is good
            }
        }
        size++; // increment size
    }

    // build heap from array sequentially
    public void buildHeapSequential(int[] values) {
        for (int value : values) {
            insert(value); 
        }
    }

    // build a heap from an array optimal
    public void buildHeapOptimal(int[] values) {
        heapArray = values; 
        size = values.length; 
        for (int i = (size / 2) - 1; i >= 0; i--) {
            heapify(i); // heapify starting from last non leaf node
        }
    }

    // Heapify subtree 
    private void heapify(int index) {
        int largest = index; // initialize largest as the root
        int left = 2 * index + 1; // calculate left 
        int right = 2 * index + 2; // calculate right 

        // check if left child is larger 
        if (left < size && heapArray[left] > heapArray[largest]) {
            largest = left;
        }

        // check if right child is larger than root or left 
        if (right < size && heapArray[right] > heapArray[largest]) {
            largest = right;
        }

        // If largest is not root
        if (largest != index) {
            // swap root with largest
            int temp = heapArray[index];
            heapArray[index] = heapArray[largest];
            heapArray[largest] = temp;
            swaps++; // increment swaps 
            heapify(largest); 
        }
    }

    // get rid of max element from heap
    public void remove() {
        if (size <= 0) { // check if heap is empty
            System.out.println("Heap is empty. Cannot remove.");
            return;
        }

        heapArray[0] = heapArray[size - 1]; // replace root with last element
        size--; // decrease 
        heapify(0); 
    }

    // Get first ten elements of the heap
    public int[] getFirstTenElements() {
        int[] firstTen = new int[Math.min(size, 10)]; // Create array to store first ten elements
        for (int i = 0; i < firstTen.length; i++) {
            firstTen[i] = heapArray[i]; 
        }
        return firstTen;
    }

    // number of swaps performed during operations
    public int getSwaps() {
        return swaps;
    }

    // String representation of the heap
    public String toString() {
        StringBuilder sb = new StringBuilder(); 
        for (int i = 0; i < size; i++) {
            if (i != 0) sb.append(",");
            sb.append(heapArray[i]); 
        }
        return sb.toString();
    }
}
