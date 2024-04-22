package org.pizza;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class HeapDriver {
    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);
        Scanner fscnr;
        System.out.print("Enter the data file to open: ");
        String file = scnr.nextLine();
        Boolean hasFile = false;
        ArrayList<Integer> values;
        do {
            values = new ArrayList<Integer>();
            File f = new File(file);
            try {
                fscnr = new Scanner(f);
                while (fscnr.hasNextInt()) {
                    values.add(fscnr.nextInt());
                }
                hasFile = true;
                fscnr.close();
            } catch (FileNotFoundException e) {
                System.out.println("File not found.");
            }
        } while (!hasFile);

        int[] topTen;
        System.out.println("=====================================================================");

        Heap sequentialHeap = new Heap(values.size());
        sequentialHeap.buildHeapSequential(values.stream().mapToInt(i -> i).toArray());
        System.out.print("Heap built using sequential insertions: ");
        topTen = sequentialHeap.getFirstTenElements();
        for (int i = 0; i < topTen.length; i++) {
            System.out.print(topTen[i] + ",");
        }
        System.out.println("...");
        System.out.println("Number of swaps in the heap creation: " + sequentialHeap.getSwaps());
        for (int i = 0; i < 10; i++) {
            sequentialHeap.remove();
        }
        System.out.print("Heap after 10 removals: ");
        topTen = sequentialHeap.getFirstTenElements();
        for (int i = 0; i < topTen.length; i++) {
            System.out.print(topTen[i] + ",");
        }
        System.out.println("...");

        System.out.println();

        Heap optimalHeap = new Heap(values.size());
        optimalHeap.buildHeapOptimal(values.stream().mapToInt(i -> i).toArray());
        System.out.print("Heap built using optimal method: ");
        topTen = optimalHeap.getFirstTenElements();
        for (int i = 0; i < topTen.length; i++) {
            System.out.print(topTen[i] + ",");
        }
        System.out.println("...");
        System.out.println("Number of swaps in the heap creation: " + optimalHeap.getSwaps());
        for (int i = 0; i < 10; i++) {
            optimalHeap.remove();
        }
        System.out.print("Heap after 10 removals: ");
        topTen = optimalHeap.getFirstTenElements();
        for (int i = 0; i < topTen.length; i++) {
            System.out.print(topTen[i] + ",");
        }
        System.out.println("...");

        System.out.println("=====================================================================");

        scnr.close();
    }
}
