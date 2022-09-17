
import java.util.ArrayList;
import java.util.List;

public class RR extends AbstractBaseScheduler {

	public Queue transferToNextQueue = new Queue<Process>(5);

	public Queue<Process> cpuschedule(int Q_time, int c_cont, CPU_scheduling_algorithm cpu, ArrayList<Process> pr) {
		Process.sortProcessByArrivalTime(pr);

		for (Process p : pr) {
			getQueue_new().enqueue(p);
		}
		while (true) {
			ReadyQueue(c_cont);
			IOstop(c_cont, cpu, Q_time);
			IoFinished(c_cont);
			TimeToRun(c_cont);
			IoFinished(c_cont);
			TimeToRun(c_cont);
			if (getRun_queue().isEmpty_Q())
				setIdleTime(getIdleTime() + 1);
			c_cont++;
			if (isFinished(c_cont))
				break;
		}

		return getFinishedQueue();
	}

	public Queue getTransferToNextQueue() {
		return transferToNextQueue;
	}

	public void IOstop(int c_coun, CPU_scheduling_algorithm cpu, int Q_time) {
		if (!getRun_queue().isEmpty_Q()) {

			if (((Process) getRun_queue().first_Q()).getWorkTime_remaining() == 0) {
				Process c_P = (Process) getRun_queue().dequeue_Q();

				switch (c_P.getProcessSituation_running()) {
				case CPU1 -> {
					if (c_P.getTime_I_O() == 0) {
						c_P.setProcessSituation_running(ProcessSituation.CPU2);
						c_P.setWorkTime_remaining(c_P.getTime_burst() - 1);
						c_P.setTime_waiting(c_P.getTime_waiting() + c_coun - c_P.getUsed_is_last());
						c_P.setTimeQuantum_current(1); // because we count from 0 in our main loop
						getRun_queue().enqueue(c_P);
					}
					if (c_P.getTime_I_O() == 2) {
						c_P.setProcessSituation_running(ProcessSituation.IO);
						c_P.setTime_is_finish(c_coun + c_P.getTime_I_O());
						c_P.setUsed_is_last(c_coun + c_P.getTime_I_O());
						getQueue_I_O().enqueue(c_P);
					}
					if (c_P.getTime_I_O() == 1) {
						c_P.setProcessSituation_running(ProcessSituation.IO);
						c_P.setTime_is_finish(c_coun + c_P.getTime_I_O());
						c_P.setUsed_is_last(c_coun + c_P.getTime_I_O());
						getQueue_I_O().enqueue(c_P);
					} else {
						c_P.setProcessSituation_running(ProcessSituation.IO);
						c_P.setTime_is_finish(c_coun + c_P.getTime_I_O());
						c_P.setUsed_is_last(c_coun + c_P.getTime_I_O());
						getQueue_I_O().enqueue(c_P);
					}
					break;
				}
				case CPU2 -> {
					c_P.setProcessSituation_running(ProcessSituation.FINISHED);
					c_P.setTime_end(c_coun);
					getFinishedQueue().enqueue(c_P);
					break;
				}
				// exep
				default -> throw new IllegalArgumentException("Error");
				}

			}

			else {
				if (((Process) getRun_queue().first_Q()).getTimeQuantum_current() == Q_time) {
					var process = (Process) getRun_queue().dequeue_Q();
					process.setWorkTime_remaining(process.getWorkTime_remaining() - 1);
					process.setUsed_is_last(c_coun);
					process.setTimeQuantum_current(1);

					if (cpu.equals(CPU_scheduling_algorithm.RR))
						getReady_queue().enqueue(process);
					else if (cpu.equals(CPU_scheduling_algorithm.MLFQ))
						transferToNextQueue.enqueue(process);
				} else {
					Process process = (Process) getRun_queue().first_Q();
					process.setTimeQuantum_current(process.getTimeQuantum_current() + 1);
					process.setWorkTime_remaining(process.getWorkTime_remaining() - 1);
				}
			}
		}
	}

	private void IoFinished(int counter) {
		while (true) {
			if (!getQueue_I_O().isEmpty_Q() && ((Process) getQueue_I_O().first_Q()).getTime_is_finish() == counter) {
				var process = (Process) getQueue_I_O().dequeue_Q();

				if (process.getProcessSituation_running().equals(ProcessSituation.IO)) {
					process.setWorkTime_remaining(process.getTime_burst() - 1);
					getReady_queue().enqueue(process);
				}
			} else
				break;
		}
	}

	public void TimeToRun(int count) {

		if (getRun_queue().isEmpty_Q() && !getReady_queue().isEmpty_Q()) {
			Process C_P = (Process) getReady_queue().dequeue_Q();

			switch (C_P.getProcessSituation_running()) {
			case FirstTime -> {
				if (C_P.getTime1_burst() == 0) {
					C_P.setProcessSituation_running(ProcessSituation.IO);
					C_P.setTime_is_finish(count + C_P.getTime_I_O());
					C_P.setUsed_is_last(count + C_P.getTime_I_O());
					C_P.setTime_start(count);
					C_P.setTime_response(count - C_P.getTime_arrival());
					C_P.setTime_waiting(count - C_P.getTime_arrival());
					getQueue_I_O().enqueue(C_P);
				} else {
					C_P.setProcessSituation_running(ProcessSituation.CPU1);
					C_P.setTime_start(count);
					C_P.setWorkTime_remaining(C_P.getTime1_burst() - 1);
					C_P.setTime_response(count - C_P.getTime_arrival());
					C_P.setTime_waiting(count - C_P.getTime_arrival());
					C_P.setTimeQuantum_current(1);
					getRun_queue().enqueue(C_P);
				}
				break;
			}

			case IO -> {

				if (C_P.getTime_burst() == 0) {
					C_P.setProcessSituation_running(ProcessSituation.FINISHED);
					C_P.setTime_end(count);
					getFinishedQueue().enqueue(C_P);
				}
				if (C_P.getTime_burst() == 1) {
					C_P.setProcessSituation_running(ProcessSituation.CPU2);
					C_P.setWorkTime_remaining(C_P.getTime_burst() - 1);
					C_P.setTime_waiting(C_P.getTime_waiting() + count - C_P.getUsed_is_last());
					C_P.setTimeQuantum_current(1);
					getRun_queue().enqueue(C_P);
				} else {
					C_P.setProcessSituation_running(ProcessSituation.CPU2);
					C_P.setWorkTime_remaining(C_P.getTime_burst() - 1);
					C_P.setTime_waiting(C_P.getTime_waiting() + count - C_P.getUsed_is_last());
					C_P.setTimeQuantum_current(1);
					getRun_queue().enqueue(C_P);
				}
				break;
			}
			case CPU1 -> {
				C_P.setTime_waiting(C_P.getTime_waiting() + count - C_P.getUsed_is_last());
				C_P.setTimeQuantum_current(1);
				getRun_queue().enqueue(C_P);
				break;
			}
			case CPU2 -> {
				C_P.setTime_waiting(C_P.getTime_waiting() + count - C_P.getUsed_is_last());
				C_P.setTimeQuantum_current(1);
				getRun_queue().enqueue(C_P);
				break;
			}

			default -> throw new IllegalArgumentException("process not in the right situation to run");
			}
		}
	}

	private void ReadyQueue(int c_count) {
		while (true) {

			if (!getQueue_new().isEmpty_Q() && ((Process) getQueue_new().first_Q()).getTime_arrival() == c_count) {
				Process op = (Process) getQueue_new().dequeue_Q();
				op.setWorkTime_remaining(op.getTime1_burst() - 1);
				getReady_queue().enqueue(op);
			} else
				break;
		}
	}
	/***
	 * RR
	 * int n; void SearchStack01(int pnt,int tm); void SearchStack02(int pnt, int
	 * tm); void AddQue(int pnt); int at[50], bt[50], ct[50]={0}, qt, rqi[50]={0},
	 * c=0, st, flg=0, tm=0, noe=0, pnt=0, btm[50]={0}, tt, wt; float att, awt;
	 * 
	 * int main(){
	 * 
	 * printf("Enter the number of processes:"); scanf("%d",&n); for(int
	 * x=0;x<n;x++){ printf("\nProcess[%d]",x+1); printf("\nAT=");
	 * scanf("%d",&at[x]); printf("BT="); scanf("%d",&bt[x]); btm[x]=bt[x];}
	 * 
	 * printf("\nEnter time quantum:"); scanf("%d",&qt);
	 * 
	 * 
	 * 
	 * printf("\nGANTT CHART\n"); printf("%d",at[0]); do{ if(flg==0){ st=at[0];
	 * //---ReduceBT if(btm[0]<=qt){ tm=st+btm[0]; btm[0]=0; SearchStack01(pnt,tm);}
	 * else{ btm[0]=btm[0]-qt; tm=st+qt; SearchStack01(pnt,tm); AddQue(pnt);} }//if
	 * 
	 * else{ pnt=rqi[0]-1; st=tm; //---DeleteQue for(int x=0;x<noe && noe!=1;x++){
	 * rqi[x]=rqi[x+1];} noe--; //---ReduceBT if(btm[pnt]<=qt){ tm=st+btm[pnt];
	 * btm[pnt]=0; SearchStack02(pnt, tm);} else{ btm[pnt]=btm[pnt]-qt; tm=st+qt;
	 * SearchStack02(pnt, tm); AddQue(pnt);} }//else
	 * 
	 * //AssignCTvalue if(btm[pnt]==0){ ct[pnt]=tm; }//if
	 * 
	 * flg++; printf("]-P%d-[%d",pnt+1,tm);
	 * 
	 * }while(noe!=0);
	 * 
	 * printf("\n\nPROCESS\t AT\t BT\t CT\t TT\t WT\n"); for(int x=0;x<n;x++){
	 * tt=ct[x]-at[x]; wt=tt-bt[x];
	 * printf("P%d\t%d\t%d\t%d\t%d\t%d\t\n",x+1,at[x],bt[x],ct[x],tt,wt);
	 * awt=awt+wt; att=att+tt; }//for
	 * 
	 * printf("\nAVERAGE TT: %f \nAVERAGE WT: %f",att/n,awt/n); printf("\n17BCI0048
	 * A.M.SAMARA SIMHA REDDY\n"); return 0; }//main
	 * 
	 * void SearchStack01(int pnt,int tm){ for(int x=pnt+1;x<n;x++){ if(at[x]<=tm){
	 * rqi[noe]=x+1; noe++;} }//for }//void
	 * 
	 * void SearchStack02(int pnt, int tm){ for(int x=pnt+1;x<n;x++){ //---CheckQue
	 * int fl=0; for(int y=0;y<noe;y++){ if(rqi[y]==x+1){ fl++;}} if(at[x]<=tm &&
	 * fl==0 && btm[x]!=0){ rqi[noe]=x+1; noe++;} }//for }//void
	 * 
	 * void AddQue(int pnt){ rqi[noe]=pnt+1; noe++; }//void
	 */
}
