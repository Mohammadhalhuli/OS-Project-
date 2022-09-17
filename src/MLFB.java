import java.util.ArrayList;
import java.util.List;

public class MLFB extends AbstractBaseScheduler{

    private Queue fr = new Queue<Process>();

    public Queue<Process> schedule(ArrayList<Process> processes) {
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
        var p_temp = new ArrayList<Process>();
        var Queuefirst = new RR();
        var _1Result = Queuefirst.cpuschedule(0, 8, CPU_scheduling_algorithm.MLFQ, processes);
        var secondQueue = new RR();
        while (!Queuefirst.getTransferToNextQueue().isEmpty_Q())
            secondQueue.getReady_queue().enqueue(Queuefirst.getTransferToNextQueue().dequeue_Q());
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
        var Result = secondQueue.cpuschedule(Queuefirst.getTotalTime(), 16, CPU_scheduling_algorithm.MLFQ, p_temp);
        var lastQueue = new FCFSSJF();
        while (!secondQueue.getTransferToNextQueue().isEmpty_Q())
            lastQueue.getReady_queue().enqueue(secondQueue.getTransferToNextQueue().dequeue_Q());
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
        var _3Result = lastQueue.Cpu_schedule(p_temp, CPU_scheduling_algorithm.FCFS, secondQueue.getTotalTime());
        while (!_1Result.isEmpty_Q())
        	fr.enqueue(_1Result.dequeue_Q());
        while (!Result.isEmpty_Q())
        	fr.enqueue(Result.dequeue_Q());
        while (!_3Result.isEmpty_Q())
        	fr.enqueue(_3Result.dequeue_Q());
        
        //****************************
        //*********************
        /////*************
        //--------------
        Timetotal = lastQueue.getTotalTime();
        Timeidle = Queuefirst.getIdleTime() + secondQueue.Timeidle + lastQueue.Timeidle;

        return fr;
    }
}
