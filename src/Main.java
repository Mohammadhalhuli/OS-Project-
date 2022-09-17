

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	//method read file
    private static List<Process> readFile(String name) throws FileNotFoundException {
        File inputs = new File(name);
        Scanner scannner = new Scanner(inputs);

        List p = new ArrayList<Process>();
        scannner.nextLine();
        //line next line 
        while(scannner.hasNextLine()) {
        	/***
    		 * 
    		 * var keyword was introduced in Java 10. Type inference is used in var keyword
    		 * in
    		 * 
    		 * which it detects automatically the datatype of a variable based on the
    		 * surrounding context. The below examples explain where var is used and also
    		 * where you can’t use it.
    		 **/
    		// Java program to explain that
    		// var can used to declare any datatype
            var row = 
            		scannner.nextLine().split(",");
            var process =
            		new Process(Integer.valueOf(row[0]),
            				Integer.valueOf(row[1]), 
            				Integer.valueOf(row[2]),
                    Integer.valueOf(row[3]),
                    Integer.valueOf(row[4]), ProcessSituation.FirstTime);
            p.add(process);
        }
        return p;
    }


    public static void main(String[] args) throws FileNotFoundException {
    	Scanner inp = new Scanner(System.in);
        ArrayList<Process> p =
                (ArrayList<Process>) readFile("inputs.csv");

        
        System.out.println
        (" \tFCFS -->1\n \tMLFQ-->2\n  \tSJF-->3\n  ");
        System.out.print
        ("Enter in the  Choice: ");
        int in = inp.nextInt();
        System.out.println
        ("Pleass Wait .............");
        for (int i = 0; i < 5; i++) {
			System.out.println
			(".............................");
			i++;
			
		}
        System.out.println
        ("Wait a one minute .............\nuntil it is calculated");

        // results
        Queue<Process> out;
        int totalTime = 0; 
        int Timeidle = 0;
        String algorithmName;
//        for (int i = 1; i <3; i++) {

            switch (in){
                case 1 -> {
                    FCFSSJF fcfs = new FCFSSJF();
                    out = fcfs.Cpu_schedule(p, CPU_scheduling_algorithm.FCFS, 0);
                    totalTime = fcfs.getTotalTime();
                    Timeidle = fcfs.getIdleTime();
                    algorithmName = "FirstComeFirstServed";
                    break;
                }

                

                case 2 -> {
                    MLFB mlfq = new MLFB();
                    out = mlfq.schedule(p);
                    totalTime = mlfq.getTotalTime();
                    Timeidle = mlfq.getIdleTime();
                    algorithmName = "MultiLevelFeedbackQueue";
                    break;

                }
                case 3 -> {
                    FCFSSJF sjf = new FCFSSJF();
                    out = sjf.Cpu_schedule(p, CPU_scheduling_algorithm.SJF, 0);
                    totalTime = sjf.getTotalTime();
                    Timeidle = sjf.getIdleTime();
                    algorithmName = "ShortestJobFirst";
                    break;
                }

                default -> throw new IllegalArgumentException("Error");
            }

            printOutput(out,algorithmName,totalTime,Timeidle);
		}
        
//    }
    public static void printOutput(Queue<Process> output,String algorithmName,int totalTime,int idleTime) {
    	String pr = "| P%-9d | %-16d | %-13d | %-12d |\n";
    	String AVG = "| Average %s %s  %s| %-16.2f | %-13.2f | %-12.2f |\n";
    	int totalResponseTime = 0;
    	int totalTurnAroundTime = 0;
    	int totalWaitingTime = 0;
    	
    	String ta = "";
    	ta += String.format("+------------+------------------+---------------+--------------+%n");
    	ta += String.format("+------------+------------------+---------------+--------------+%n");
    	ta += String.format("|            |         TAT      |        RT     |       WT     |\n");
    	ta += String.format("+------------+------------------+---------------+--------------+\n");
    	ta += String.format("+------------+------------------+---------------+--------------+%n");
    	float count = 0;
    	while (!output.isEmpty_Q()) {
    		var process = output.dequeue_Q();
    		totalWaitingTime += process.getTime_waiting();
    		totalResponseTime += process.getTime_response();
    		totalTurnAroundTime += process.getTime_end();

    		ta += String.format(pr, process.getId_process(),
    				process.getTime_waiting(),
    				process.getTime_end() - process.getTime_arrival(), process.getTime_response());
    		count++;
    	}
    	ta += String.format("+------------+------------------+---------------+--------------+%n");
    	ta += String.format("+------------+------------------+---------------+--------------+%n");
    	ta += String.format(AVG, "", "", "",
    			totalWaitingTime / count, totalTurnAroundTime / count, totalResponseTime / count);
    	ta += String.format("+------------+------------------+---------------+--------------+%n");
    	ta += String.format("+------------+------------------+---------------+--------------+%n");
    	ta += String.format("\nTotal Time: %-4d", totalTime);
    	ta += String.format("\nCPU Utilization: %-4.2f ", (float) (totalTime - idleTime) / totalTime);

    	System.out.println(ta+"\n");
    }

}







