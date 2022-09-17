/* the class is keyword ***/
/* enum is a special "class" that represents ***/
/* constants unchangeable variables, like final var ***/
/*This means "enum" that it is similar to final **/
public enum ProcessSituation {
	/****
	 * Process goes {CPU burst, I/O time, CPU burst, I/O time, CPU burst, I/O time,…….., last CPU burst}
	 * */
	//All processes are activated at time 0
    FirstTime,
    CPU1,
    CPU2,
    CPU3,
    CPU4,
    CPU5,
    CPU6,
    CPU7,
    CPU8,
    IO,
    IO2,
    IO3,
    IO4,
    IO5,
    IO6,
    IO7,
    //After completing an I/O event, a process is transferred to the ready queue
    FINISHED
}
