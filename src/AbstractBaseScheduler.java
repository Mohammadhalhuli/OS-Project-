

public abstract class AbstractBaseScheduler {
   

    Queue Queue_new = 
    		new Queue<Process>();
    //ready Qu
    Queue ready_queue =
    		new Queue<Process>();
    //runnning Qu
    Queue run_queue =
    		new Queue<Process>();
    //inp out Qu
    Queue queue_I_O =
    		new Queue<Process>();
    //fin Qu
    Queue finishedQueue =
    		new Queue<Process>();
    /*****/
    protected int Timetotal;
    protected int Timeidle;
    protected boolean isFinished(int counter) {
    	//true or false
    	 boolean result = ready_queue.isEmpty_Q()&& 
    			 run_queue.isEmpty_Q()&&
    			 queue_I_O.isEmpty_Q()&&
    			 Queue_new.isEmpty_Q();
          //
    	 if (result){//if true
    		 //-----
        	  Timetotal = --counter;
        	  //-----------
        	  Timeidle--;
        }

        return result;
    }
    public int getTotalTime() {
        return Timetotal;
    }

    public int getIdleTime() {
        return Timeidle;
    }

    

	public void setTotalTime(int Timetotal) {
		this.Timetotal = Timetotal;
	}

	public void setIdleTime(int Timeidle) {
		this.Timeidle = Timeidle;
	}

	
    
    public Queue getQueue_new() {
		return Queue_new;
	}
	public void setQueue_new(Queue queue_new) {
		Queue_new = queue_new;
	}
	public int getTimeidle() {
		return Timeidle;
	}
	public Queue getReady_queue() {
		return ready_queue;
	}
	
	public void setReady_queue(Queue ready_queue) {
		this.ready_queue = ready_queue;
	}
	public Queue getRun_queue() {
		return run_queue;
	}
	public void setTimetotal(int timetotal) {
		Timetotal = timetotal;
	}
	public void setRun_queue(Queue run_queue) {
		this.run_queue = run_queue;
	}
	public void setTimeidle(int timeidle) {
		Timeidle = timeidle;
	}
	public Queue getQueue_I_O() {
		return queue_I_O;
	}
	public void setQueue_I_O(Queue queue_I_O) {
		this.queue_I_O = queue_I_O;
	}
	public int getTimetotal() {
		return Timetotal;
	}
	public Queue getFinishedQueue() {
		return finishedQueue;
	}
	public void setFinishedQueue(Queue finishedQueue) {
		this.finishedQueue = finishedQueue;
	}
	
	
	
	@Override
	public String toString() {
		return "AbstractBaseScheduler [Queue_new=" + Queue_new + ", ready_queue=" + ready_queue + ", run_queue="
				+ run_queue + ", queue_I_O=" + queue_I_O + ", finishedQueue=" + finishedQueue + ", Timetotal="
				+ Timetotal + ", Timeidle=" + Timeidle + "]";
	}
	//print in queue 
	public void printQueue(Queue<Process> queue) {
    	System.out.println(queue);
    }
    
}
