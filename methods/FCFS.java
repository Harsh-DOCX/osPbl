package methods;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import modules.ProcessData;
import modules.Schema;

public class FCFS extends Schema {
    private final ArrayList<ProcessData> processes;
    private final ArrayList<ScheduledProcess> tableRows;
    private final ArrayList<String> ganttLabels;
    private final ArrayList<Integer> ganttTimes;
    private double avgWaitingTime;
    private double avgTurnaroundTime;
    private boolean scheduled;

    private static class ScheduledProcess {
        String name;
        int arrival;
        int burst;
        int start;
        int completion;
        int waiting;
        int turnaround;

        ScheduledProcess(
                String name,
                int arrival,
                int burst,
                int start,
                int completion,
                int waiting,
                int turnaround) {
            this.name = name;
            this.arrival = arrival;
            this.burst = burst;
            this.start = start;
            this.completion = completion;
            this.waiting = waiting;
            this.turnaround = turnaround;
        }
    }

    public FCFS(ArrayList<ProcessData> processes) {
        this.processes = processes == null ? new ArrayList<>() : new ArrayList<>(processes);
        this.tableRows = new ArrayList<>();
        this.ganttLabels = new ArrayList<>();
        this.ganttTimes = new ArrayList<>();
        this.avgWaitingTime = 0.0;
        this.avgTurnaroundTime = 0.0;
        this.scheduled = false;
    }

    public FCFS() {
        this(new ArrayList<>());
    }

    @Override
    public void schedule() {
        tableRows.clear();
        ganttLabels.clear();
        ganttTimes.clear();
        avgWaitingTime = 0.0;
        avgTurnaroundTime = 0.0;
        scheduled = false;

        if (processes.isEmpty()) {
            ganttTimes.add(0);
            scheduled = true;
            return;
        }

        List<ProcessData> ordered = new ArrayList<>(processes);
        ordered.sort(Comparator.comparingInt((ProcessData p) -> p.arrivalTime));

        int currentTime = 0;
        ganttTimes.add(0);

        for (ProcessData process : ordered) {
            if (currentTime < process.arrivalTime) {
                ganttLabels.add("IDLE");
                currentTime = process.arrivalTime;
                ganttTimes.add(currentTime);
            }

            int start = currentTime;
            int completion = start + process.burstTime;
            int waiting = start - process.arrivalTime;
            int turnaround = completion - process.arrivalTime;

            tableRows.add(
                    new ScheduledProcess(
                            process.pName,
                            process.arrivalTime,
                            process.burstTime,
                            start,
                            completion,
                            waiting,
                            turnaround));

            ganttLabels.add(process.pName);
            currentTime = completion;
            ganttTimes.add(currentTime);

            avgWaitingTime += waiting;
            avgTurnaroundTime += turnaround;
        }

        avgWaitingTime /= tableRows.size();
        avgTurnaroundTime /= tableRows.size();
        scheduled = true;
    }

    @Override
    public void buildGanttChart() {
        if (!scheduled) {
            schedule();
        }
    }

    @Override
    public void averageWaitingTime() {
        if (!scheduled) {
            schedule();
        }
        System.out.printf("Average Waiting Time: %.2f%n", avgWaitingTime);
    }

    @Override
    public void averageTurnaroundTime() {
        if (!scheduled) {
            schedule();
        }
        System.out.printf("Average Turnaround Time: %.2f%n", avgTurnaroundTime);
    }

    @Override
    public void printTable() {
        if (!scheduled) {
            schedule();
        }

        if (tableRows.isEmpty()) {
            System.out.println("No process data available.");
            return;
        }

        System.out.println("FCFS Scheduling Table");
        System.out.printf(
                "%-10s%-10s%-10s%-10s%-12s%-10s%-12s%n",
                "Process",
                "Arrival",
                "Burst",
                "Start",
                "Completion",
                "Waiting",
                "Turnaround");

        for (ScheduledProcess row : tableRows) {
            System.out.printf(
                    "%-10s%-10d%-10d%-10d%-12d%-10d%-12d%n",
                    row.name,
                    row.arrival,
                    row.burst,
                    row.start,
                    row.completion,
                    row.waiting,
                    row.turnaround);
        }
    }

    @Override
    public void printGanttChart() {
        if (!scheduled) {
            schedule();
        }

        if (ganttLabels.isEmpty()) {
            System.out.println("No Gantt chart data available.");
            return;
        }

        System.out.println("FCFS Gantt Chart");

        StringBuilder top = new StringBuilder();
        for (String label : ganttLabels) {
            top.append("| ").append(label).append(" ");
        }
        top.append("|");
        System.out.println(top);

        StringBuilder times = new StringBuilder();
        for (int i = 0; i < ganttTimes.size(); i++) {
            times.append(ganttTimes.get(i));
            if (i != ganttTimes.size() - 1) {
                times.append("   ");
            }
        }
        System.out.println(times);
    }
}
