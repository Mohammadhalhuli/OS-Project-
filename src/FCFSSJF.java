import java.util.ArrayList;
import java.util.List;

public class FCFSSJF extends AbstractBaseScheduler {

	private void CpuBurstTimeToRun(int pcount, CPU_scheduling_algorithm scheduling_algorithm) {
		// the S.J.F we shull order the remaining time for each
		// find size queue if
		// red >1--
		if (ready_queue.size_Q() > 1 &&
		// and cpu sc == sjf
				scheduling_algorithm.equals(CPU_scheduling_algorithm.SJF))
			Process.
			// sort prooo

					sortProcessByRemainingTime(ready_queue);
		// find if queue is empty
		if (run_queue.isEmpty_Q() &&
		// if not emp
				!ready_queue.isEmpty_Q()) {
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
			var jpro = (Process) ready_queue.dequeue_Q();

			switch (jpro.getProcessSituation_running()) {

			case CPU1 -> {
				if (jpro.getTime1_burst() == 0) {

					jpro.setProcessSituation_running(ProcessSituation.IO);

					jpro.setTime_start(pcount);

					jpro.setTime_is_finish(pcount + jpro.getTime_I_O());

					jpro.setUsed_is_last(pcount + jpro.getTime_I_O());

					queue_I_O.enqueue(jpro);

				} else {
					jpro.setProcessSituation_running(ProcessSituation.CPU1);

					jpro.setTime_start(pcount);

					jpro.setTime_is_finish(pcount + jpro.getTime1_burst());

					jpro.setTime_response(pcount - jpro.getTime_arrival());

					jpro.setTime_waiting(pcount - jpro.getTime_arrival());

					run_queue.enqueue(jpro);
				}
				break;// stop case
			}
			case CPU2 -> {
				if (jpro.getTime1_burst() == 0) {

					jpro.setProcessSituation_running(ProcessSituation.IO);

					jpro.setTime_start(pcount);

					jpro.setTime_is_finish(pcount + jpro.getTime_I_O());

					jpro.setUsed_is_last(pcount + jpro.getTime_I_O());

					queue_I_O.enqueue(jpro);

				} else {
					jpro.setProcessSituation_running(ProcessSituation.CPU1);

					jpro.setTime_start(pcount);
					jpro.setTime_is_finish(pcount + jpro.getTime1_burst());

					jpro.setTime_response(pcount - jpro.getTime_arrival());

					jpro.setTime_waiting(pcount - jpro.getTime_arrival());

					run_queue.enqueue(jpro);
				}

				break;// stop case
			}
			case FirstTime -> {

				if (jpro.getTime1_burst() == 0) {

					jpro.setProcessSituation_running(ProcessSituation.IO);

					jpro.setTime_start(pcount);

					jpro.setTime_is_finish(pcount + jpro.getTime_I_O());

					jpro.setUsed_is_last(pcount + jpro.getTime_I_O());

					queue_I_O.enqueue(jpro);

				} else {
					jpro.setProcessSituation_running(ProcessSituation.CPU1);

					jpro.setTime_start(pcount);

					jpro.setTime_is_finish(pcount + jpro.getTime1_burst());

					jpro.setTime_response(pcount - jpro.getTime_arrival());

					jpro.setTime_waiting(pcount - jpro.getTime_arrival());

					run_queue.enqueue(jpro);

				}

				break;// stop case
			}
			case IO -> {
				jpro.setProcessSituation_running(ProcessSituation.CPU2);

				jpro.setTime_is_finish(pcount + jpro.getTime_burst());

				jpro.setTime_waiting(jpro.getTime_waiting() + pcount - jpro.getUsed_is_last());

				run_queue.enqueue(jpro);
				break;// stop case
			}

			default -> throw new IllegalArgumentException("error ");
			}
		}
	}

	private void IsFinished_I_O(int Jcoun, CPU_scheduling_algorithm cpu_s) {
		// true inv-------------------------------
		// ----------------
		while (true) {

			if (!queue_I_O.isEmpty_Q() && ((Process) queue_I_O.first_Q()).getTime_burst() == Jcoun) {
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
				var jpro = (Process) queue_I_O.dequeue_Q();

				if (jpro.getProcessSituation_running().equals(ProcessSituation.IO)) {

					if (cpu_s.equals(CPU_scheduling_algorithm.SJF))

						jpro.setWorkTime_remaining(jpro.getTime_burst());

					ready_queue.enqueue(jpro);
				}

			} else
				// stop loop
				break;// stop case
		}
	}

	private void stopProcessOrEnterIo(int cpucount) {
		// prooc not empty run
		if (!run_queue.isEmpty_Q()) {
			// 00
			// 11
			// 22
			if (((Process) run_queue.first_Q()).getTime_is_finish() == cpucount) {
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
				var sc_P = (Process) run_queue.dequeue_Q();

				switch (sc_P.getProcessSituation_running()) {
				case CPU1 -> {
					if (sc_P.getTime_I_O() == 0) {
						sc_P.setProcessSituation_running(ProcessSituation.CPU2);
						sc_P.setTime_is_finish(cpucount + sc_P.getTime_burst());
						sc_P.setTime_waiting(sc_P.getTime_waiting() + cpucount - sc_P.getUsed_is_last());
						run_queue.enqueue(sc_P);
					} else {
						sc_P.setProcessSituation_running(ProcessSituation.IO);
						sc_P.setTime_is_finish(cpucount + sc_P.getTime_I_O());
						sc_P.setTime_waiting(cpucount + sc_P.getTime_I_O());
						queue_I_O.enqueue(sc_P);
					}
					break;// stop case
				}
				case CPU2 -> {
					sc_P.setProcessSituation_running(ProcessSituation.FINISHED);
					sc_P.setTime_end(cpucount);
					finishedQueue.enqueue(sc_P);
					break;// stop case
				}
				// exeption
				default -> throw new IllegalArgumentException("Error");
				}
			}
		}
	}

	public Queue<Process> Cpu_schedule(ArrayList<Process> sc_p, CPU_scheduling_algorithm cpu, int p_coun) {
		Process.sortProcessByArrivalTime(sc_p);
		// for each
		for (Process _p : sc_p) {
			Queue_new.enqueue(_p);
		}

		while (true) {
			ProcessesReadyQueue(p_coun, cpu);
			stopProcessOrEnterIo(p_coun);
			IsFinished_I_O(p_coun, cpu);
			CpuBurstTimeToRun(p_coun, cpu);
			IsFinished_I_O(p_coun, cpu);
			stopProcessOrEnterIo(p_coun);
			if (run_queue.isEmpty_Q())
				Timeidle++;
			p_coun++;// 1+1
			/// ---------------------------------------------------
			// ----------------------------------
			if (isFinished(p_coun))
				break;
		}
		return finishedQueue;
	}

	private void ProcessesReadyQueue(int n_c, CPU_scheduling_algorithm cpu) {
		while (true) {
			// isEmpty_Q() && ((Process) Queue_new.first_Q()).getTime_arrival() == counter
			if (!Queue_new.isEmpty_Q() &&
			// not emp and = count
					((Process) Queue_new.first_Q()).getTime_arrival() == n_c) {
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
				var process = (Process) Queue_new.dequeue_Q();

				if (cpu.equals(CPU_scheduling_algorithm.SJF))
					/**
					 * .
					 * 
					 * 
					 */
					process.setWorkTime_remaining(process.getTime1_burst());

				ready_queue.enqueue(process);
			} else
				break;
		}
	}
	/*
	 * FCFS
	 * 
	 * typedef struct process{ int at,bt,ct,tat,wt,id; }process; process
	 * Atthemoment[20]; process hastocome[20]; process completed[20]; void
	 * swap(process *a,process *b){ process c=*a; a=*b; b=c; } int idnotincomp(int
	 * id,int d){ int c=0,i,j; for(i=0;i<d;i++){ if(completed[i].id==id){ c++; } }
	 * if(c==0){ return 1; } else{ return 0; } } void initatthemoment(){ int i;
	 * for(i=0;i<20;i++){ Atthemoment[i].at='\0'; Atthemoment[i].bt='\0';
	 * Atthemoment[i].ct='\0'; Atthemoment[i].tat='\0'; Atthemoment[i].wt='\0';
	 * 
	 * } } void inithastocome(){ int i; for(i=0;i<20;i++){ hastocome[i].at='\0';
	 * hastocome[i].bt='\0'; hastocome[i].ct='\0'; hastocome[i].tat='\0';
	 * hastocome[i].wt='\0';
	 * 
	 * } }
	 * 
	 * 
	 * process shortestarrival(int z){ int i,j; process shor=hastocome[0];
	 * for(i=1;i<z;i++){ if(hastocome[i].at<shor.at){ shor=hastocome[i]; } } return
	 * shor;
	 * 
	 * } process shortest(int m){ int i; process shor=Atthemoment[0];
	 * for(i=1;i<m;i++){ if(Atthemoment[i].at<shor.at){ shor=Atthemoment[i]; } }
	 * return shor; }
	 * 
	 * int main(){ process p[20]; process shor; int i,j,k,n,m=0,d=0,z,samear=0;
	 * float awt=0,atat=0; printf("Enter the number of processes:"); scanf("%d",&n);
	 * for(i=0;i<n;i++){ p[i].id=i+1;
	 * printf("Enter the arrival time for process[%d]:",i+1); scanf("%d",&p[i].at);
	 * printf("Enter the burst time for process[%d]:",i+1); scanf("%d",&p[i].bt); }
	 * for(i=0;i<n-1;++i){ for(j=0;j<n-i-1;++j){ if(p[j].at>p[j+1].at){
	 * swap(&p[j],&p[j+1]); } } } Atthemoment[m++]=p[0]; for(i=1;i<n;i++){ samear=0;
	 * if(p[0].at==p[i].at){ samear++; Atthemoment[m++]=p[i];
	 * 
	 * } } if(samear>0){ shor=shortest(m); shor.wt=0; shor.ct=shor.bt+shor.at;
	 * shor.tat=shor.ct-shor.at; completed[d++]=shor;} while(d<n){
	 * 
	 * m=0; z=0; for(i=0;i<n;i++){ if(idnotincomp(p[i].id,d)==1){
	 * 
	 * if(p[i].at<=completed[d-1].ct){ Atthemoment[m++]=p[i]; } } }
	 * if(Atthemoment[0].bt=='\0'){ for(i=0;i<n;i++){ if(idnotincomp(p[i].id,d)==1){
	 * hastocome[z++]=p[i]; } } shor=shortestarrival(z); shor.wt=0;
	 * shor.ct=shor.at+shor.bt; shor.tat=shor.ct-shor.at; completed[d++]=shor;
	 * inithastocome(); initatthemoment(); } else{ shor=shortest(m);
	 * shor.wt=completed[d-1].ct-shor.at; shor.ct=completed[d-1].ct+shor.bt;
	 * shor.tat=shor.ct-shor.at; completed[d++]=shor; inithastocome();
	 * initatthemoment(); } } printf("******************\n");
	 * 
	 * printf("\nProcess\t Arrival\t Burst\t Waiting\t Turn-around");
	 * for(i=0;i<d;i++){
	 * printf("\n p%d\t %d\t\t %d\t %d\t\t\t%d\n",completed[i].id,completed[i].at,
	 * completed[i].bt,completed[i].wt,completed[i].tat); awt=awt+completed[i].wt;
	 * atat=atat+completed[i].tat; } awt=awt/n; atat=atat/n;
	 * printf("\nAverage waiting time: %.3f\n",awt);
	 * printf("Average turn-around time: %.3f\n",atat);
	 * printf(" 17BCI0048 A.M.SAMARA SIMHA REDDY\n"); return 0;
	 * 
	 * }
	 * 
	 * 
	 ****/

	/**
	 * SJF
	 * typedef struct process{ int at,bt,ct,tat,wt,id; }process; process
	 * Atthemoment[20]; process hastocome[20]; process completed[20]; process
	 * more[20]; process shortburst(process more[],int k){ int i,j; process
	 * shor=more[0]; for(i=1;i<k;i++){ if(more[i].bt<shor.bt){ shor=more[i]; } }
	 * return shor; } void swap(process *a,process *b){ process c=*a; a=*b; b=c; }
	 * int idnotincomp(int id,int d){ int c=0,i,j; for(i=0;i<d;i++){
	 * if(completed[i].id==id){ c++; } } if(c==0){ return 1; } else{ return 0; } }
	 * void initatthemoment(){ int i; for(i=0;i<20;i++){ Atthemoment[i].at='\0';
	 * Atthemoment[i].bt='\0'; Atthemoment[i].ct='\0'; Atthemoment[i].tat='\0';
	 * Atthemoment[i].wt='\0';
	 * 
	 * } } void inithastocome(){ int i; for(i=0;i<20;i++){ hastocome[i].at='\0';
	 * hastocome[i].bt='\0'; hastocome[i].ct='\0'; hastocome[i].tat='\0';
	 * hastocome[i].wt='\0';
	 * 
	 * } }
	 * 
	 * 
	 * process shortestarrival(int z){ int i,j,c=0; process shor=hastocome[0];
	 * for(i=1;i<z;i++){ if(hastocome[i].at<shor.at){ shor=hastocome[i]; } }
	 * for(i=0;i<z;i++){ if(shor.at==hastocome[i].at){ more[c++]=hastocome[i]; } }
	 * shor=shortburst(more,c);
	 * 
	 * 
	 * return shor;
	 * 
	 * } process shortest(int m){ int i; process shor=Atthemoment[0];
	 * for(i=1;i<m;i++){ if(Atthemoment[i].bt<shor.bt){ shor=Atthemoment[i]; } }
	 * return shor; }
	 * 
	 * int main(){ process p[20]; process shor; int i,j,k,n,m=0,d=0,z,samear=0;
	 * float awt=0,atat=0; printf("Enter the number of processes:"); scanf("%d",&n);
	 * for(i=0;i<n;i++){ p[i].id=i+1; printf("Enter the arrival time for
	 * process[%d]:",i+1); scanf("%d",&p[i].at); printf("Enter the burst time for
	 * process[%d]:",i+1); scanf("%d",&p[i].bt); } for(i=0;i<n-1;++i){
	 * for(j=0;j<n-i-1;++j){ if(p[j].at>p[j+1].at){ swap(&p[j],&p[j+1]); } } }
	 * Atthemoment[m++]=p[0]; samear=0; for(i=1;i<n;i++){
	 * 
	 * if(p[0].at==p[i].at){ samear++; Atthemoment[m++]=p[i];
	 * 
	 * } } shor=shortest(m); shor.wt=0; shor.ct=shor.bt+shor.at;
	 * shor.tat=shor.ct-shor.at; completed[d++]=shor;
	 * 
	 * while(d<n){
	 * 
	 * m=0; z=0; for(i=0;i<n;i++){ if(idnotincomp(p[i].id,d)==1){
	 * 
	 * if(p[i].at<=completed[d-1].ct){ Atthemoment[m++]=p[i]; } } }
	 * if(Atthemoment[0].bt=='\0'){ for(i=0;i<n;i++){ if(idnotincomp(p[i].id,d)==1){
	 * hastocome[z++]=p[i]; } } shor=shortestarrival(z); shor.wt=0;
	 * shor.ct=shor.at+shor.bt; shor.tat=shor.ct-shor.at; completed[d++]=shor;
	 * inithastocome(); initatthemoment(); } else{ shor=shortest(m);
	 * shor.wt=completed[d-1].ct-shor.at; shor.ct=completed[d-1].ct+shor.bt;
	 * shor.tat=shor.ct-shor.at; completed[d++]=shor; inithastocome();
	 * initatthemoment(); } } printf("******************\n");
	 * 
	 * printf("\nProcess\t Arrival\t Burst\t Waiting\t Turn-around");
	 * for(i=0;i<d;i++){ printf("\n p%d\t %d\t\t %d\t
	 * %d\t\t\t%d\n",completed[i].id,completed[i].at,completed[i].bt,completed[i].wt,completed[i].tat);
	 * awt=awt+completed[i].wt; atat=atat+completed[i].tat; } awt=awt/n;
	 * atat=atat/n; printf("Average waiting time: %.3f\n",awt); printf("Average
	 * turn-around time: %.3f\n",atat); printf(" 17BCI0048 A.M.SAMARA SIMHA
	 * REDDY\n"); return 0;
	 * 
	 * }
	 ***/

}
