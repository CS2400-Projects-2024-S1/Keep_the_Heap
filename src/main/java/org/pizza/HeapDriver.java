package org.pizza;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException; 
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
            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        } while (!hasFile);

        StringBuilder sb = new StringBuilder();
        int[] topTen;
        sb.append("=====================================================================\n");

        Heap sequentialHeap = new Heap(values.size());
        sequentialHeap.buildHeapSequential(values.stream().mapToInt(i -> i).toArray());
        sb.append("Heap built using sequential insertions: ");
        topTen = sequentialHeap.getFirstTenElements();
        for (int i = 0; i < topTen.length; i++) {
            sb.append(topTen[i] + ",");
        }
        sb.append("...\n");
        sb.append("Number of swaps in the heap creation: " + sequentialHeap.getSwaps() + "\n");
        for (int i = 0; i < 10; i++) {
            sequentialHeap.remove();
        }
        sb.append("Heap after 10 removals: ");
        topTen = sequentialHeap.getFirstTenElements();
        for (int i = 0; i < topTen.length; i++) {
            sb.append(topTen[i] + ",");
        }
        sb.append("...\n");

        sb.append("\n");

        Heap optimalHeap = new Heap(values.size());
        optimalHeap.buildHeapOptimal(values.stream().mapToInt(i -> i).toArray());
        sb.append("Heap built using optimal method: ");
        topTen = optimalHeap.getFirstTenElements();
        for (int i = 0; i < topTen.length; i++) {
            sb.append(topTen[i] + ",");
        }
        sb.append("...\n");
        sb.append("Number of swaps in the heap creation: " + optimalHeap.getSwaps() + "\n");
        for (int i = 0; i < 10; i++) {
            optimalHeap.remove();
        }
        sb.append("Heap after 10 removals: ");
        topTen = optimalHeap.getFirstTenElements();
        for (int i = 0; i < topTen.length; i++) {
            sb.append(topTen[i] + ",");
        }
        sb.append("...\n");

        sb.append("=====================================================================\n");

        System.out.println(sb.toString());

        scnr.close();
    }
}
