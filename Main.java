
import java.util.ArrayList;
import methods.*;
import modules.*;

public class Main {
    public static void main(String[] args) {
        CsvParser csv = new CsvParser();
        ArrayList<ProcessData> processes = csv.getData("processes");

        FCFS fcfs = new FCFS(processes);

        fcfs.schedule();
        fcfs.buildGanttChart();
        fcfs.printTable();
        fcfs.printGanttChart();
        fcfs.averageWaitingTime();
        fcfs.averageTurnaroundTime();

        SJF sjf = new SJF(processes);

        System.out.println("\n\n\n");
        sjf.schedule();
        sjf.buildGanttChart();
        sjf.printTable();
        sjf.printGanttChart();
        sjf.averageWaitingTime();
        sjf.averageTurnaroundTime();

        Priority pr = new Priority(processes);

        System.out.println("\n\n\n");
        pr.schedule();
        pr.buildGanttChart();
        pr.printTable();
        pr.printGanttChart();
        pr.averageWaitingTime();
        pr.averageTurnaroundTime();

        RoundRobin rr = new RoundRobin(processes);

        System.out.println("\n\n\n");
        rr.schedule();
        rr.buildGanttChart();
        rr.printTable();
        rr.printGanttChart();
        rr.averageWaitingTime();
        rr.averageTurnaroundTime();

    }
}
