
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
//Mohammad
public class Process {
	// var option process
	private ProcessSituation ProcessSituation_running;
	private int WorkTime_remaining; //
	private int TimeQuantum_current;
	private int Time_is_finish;
	private int Used_is_last;
	private int id_process;
	private int Time_arrival;
	private int Time1_burst;
	private int Time_I_O;
	private int Time_burst;
	private int Time_waiting;
	private int Time_response;
	private int Time_end;
	private int Time_start;
	public Process(int id_process, int time_arrival, int time1_burst, int time_I_O, int time_burst, int time_waiting,
			int time_response, int time_end, int time_start, ProcessSituation processSituation_running,
			int workTime_remaining, int timeQuantum_current, int time_is_finish, int used_is_last) {
		super();
		this.id_process = id_process;
		Time_arrival = time_arrival;
		Time1_burst = time1_burst;
		Time_I_O = time_I_O;
		Time_burst = time_burst;
		Time_waiting = time_waiting;
		Time_response = time_response;
		Time_end = time_end;
		Time_start = time_start;
		ProcessSituation_running = processSituation_running;
		WorkTime_remaining = workTime_remaining;
		TimeQuantum_current = timeQuantum_current;
		Time_is_finish = time_is_finish;
		Used_is_last = used_is_last;
	}
	public Process() {

	}
	//constractor
	public Process(int id_process, int time_arrival, int time1_burst, int time_I_O, int time_burst,
			ProcessSituation processSituation_running) {
		super();
		this.id_process = id_process;
		Time_arrival = time_arrival;
		Time1_burst = time1_burst;
		Time_I_O = time_I_O;
		Time_burst = time_burst;
		ProcessSituation_running = processSituation_running;
	}
	//sort 
	public static void sortProcessByArrivalTime(ArrayList<Process> p) {
		//call method sort proc
		p.sort
		(new Comparator
				<Process>() {
			//comparrabel in the process
			@Override
			public int compare(Process p1, Process p2) {
				return Integer.compare(p1.Time_arrival, p2.Time_arrival);
			}
		});
	}
	public static void sortProcessByRemainingTime(Queue<Process> readyQueue) {
		/**
		 * var keyword was introduced in Java 10. 
		 * Type inference is used in var keyword in which it detects automatically the datatype of a variable based on the surrounding context. The below examples explain where var is used and also where you can’t use it.
		 * **/
		var processes = new ArrayList<Process>();

		while (!readyQueue.isEmpty_Q())
			processes.add(readyQueue.dequeue_Q());

		processes.sort(new Comparator<Process>() {
			@Override
			public int compare(Process pro1, Process pro2) {
				return
						Integer.compare(pro1.getWorkTime_remaining(),
								pro2.getWorkTime_remaining());
			}
		});
//if queue in not empty
		while (!processes.isEmpty())
			/**remo process*/
			readyQueue.enqueue(processes.remove(0));

	}
	/*** getar and setar**/
	public int getId_process() {
		return id_process;
	}

	public void setId_process(int id_process) {
		this.id_process = id_process;
	}

	public int getTime_arrival() {
		return Time_arrival;
	}

	public void setTime_arrival(int time_arrival) {
		Time_arrival = time_arrival;
	}

	public int getTime1_burst() {
		return Time1_burst;
	}

	public void setTime1_burst(int time1_burst) {
		Time1_burst = time1_burst;
	}

	public int getTime_I_O() {
		return Time_I_O;
	}

	public void setTime_I_O(int time_I_O) {
		Time_I_O = time_I_O;
	}

	public int getTime_burst() {
		return Time_burst;
	}

	public void setTime_burst(int time_burst) {
		Time_burst = time_burst;
	}

	public int getTime_waiting() {
		return Time_waiting;
	}

	public void setTime_waiting(int time_waiting) {
		Time_waiting = time_waiting;
	}

	public int getTime_response() {
		return Time_response;
	}

	public void setTime_response(int time_response) {
		Time_response = time_response;
	}

	public int getTime_end() {
		return Time_end;
	}

	public void setTime_end(int time_end) {
		Time_end = time_end;
	}

	public int getTime_start() {
		return Time_start;
	}

	public void setTime_start(int time_start) {
		Time_start = time_start;
	}

	public ProcessSituation getProcessSituation_running() {
		return ProcessSituation_running;
	}

	public void setProcessSituation_running(ProcessSituation processSituation_running) {
		ProcessSituation_running = processSituation_running;
	}

	public int getWorkTime_remaining() {
		return WorkTime_remaining;
	}

	public void setWorkTime_remaining(int workTime_remaining) {
		WorkTime_remaining = workTime_remaining;
	}

	public int getTimeQuantum_current() {
		return TimeQuantum_current;
	}

	public void setTimeQuantum_current(int timeQuantum_current) {
		TimeQuantum_current = timeQuantum_current;
	}

	public int getTime_is_finish() {
		return Time_is_finish;
	}

	public void setTime_is_finish(int time_is_finish) {
		Time_is_finish = time_is_finish;
	}

	public int getUsed_is_last() {
		return Used_is_last;
	}

	public void setUsed_is_last(int used_is_last) {
		Used_is_last = used_is_last;
	}
	/****
	 * print in to string 
	 * */
	@Override
	public String toString() {
		return "Process [id_process=" + id_process + ", Time_arrival=" + Time_arrival + ", Time1_burst=" + Time1_burst
				+ ", Time_I_O=" + Time_I_O + ", Time_burst=" + Time_burst + ", Time_waiting=" + Time_waiting
				+ ", Time_response=" + Time_response + ", Time_end=" + Time_end + ", Time_start=" + Time_start
				+ ", ProcessSituation_running=" + ProcessSituation_running + ", WorkTime_remaining="
				+ WorkTime_remaining + ", TimeQuantum_current=" + TimeQuantum_current + ", Time_is_finish="
				+ Time_is_finish + ", Used_is_last=" + Used_is_last + "]";
	}

}
